import java.awt
import scala.swing._
import scala.swing.event._
import java.awt.event._

object main extends SimpleSwingApplication{
  def top: MainFrame = new MainFrame{//Main Frame
    title = "String Application"
    contents = new BoxPanel(Orientation.Vertical) {

      border = Swing.EmptyBorder(30,30,30,30)
      contents += Swing.VStrut(5)
      contents += new TextField() {//Adds a text field to introduce a text
        Mediator.panel = this
        name = "myTextfield"
        background = new Color(0xddffdd);
        border = Swing.EtchedBorder
        focusable = true
        listenTo(keys)
        //reactions += { case KeyPressed(_, kval, _ , _) => text += kval }
      }

      contents += Swing.VStrut(5)
      contents += Swing.Glue
      contents += new GridPanel(2,2) {//Adds the buttons
        for ( c <- "c Count_Letter toUpper findWord".split(" ") )
          contents += new Button(c.toString) { Mediator.buttons += this }
      }
    }
    Mediator.programInteractions()
  }
}
object Mediator extends Reactor {//the reactors to the first buttons
  var panel:TextComponent = null
  var buttons = Set[Button]()
  // wires interactions
  def programInteractions() {
    buttons.foreach(listenTo(_))
    reactions += { case ButtonClicked(b) if b.text == "Count_Letter" => {
      actuador.reset()
      actuador.addStrings(panel.text.split(" "))
      actuador.countLetters()
      panel.text = "" }
    }
    reactions += { case ButtonClicked(b) if b.text == "c" => {
      actuador.reset()
      panel.text="" }
    }
    reactions += { case ButtonClicked(b) if b.text == "toUpper" => {
      actuador.reset()
      actuador.addStrings(panel.text.split(" "))
      actuador.upperCase()
      panel.text="" }
    }
    reactions += { case ButtonClicked(b) if b.text == "findWord" => {
      actuador.reset()
      actuador.addStrings(panel.text.split(" "))
      actuador.findWord()
      panel.text="" }
    }
  }
}



object actuador{//Logistical calculus
  var nstack = List[String]()

  def addStrings(s: Array[String]): Unit ={
    nstack = s.toList
  }
  def reset() { nstack = List[String]() }

  def countLetters(): Unit ={
    new myFrame(nstack).top.visible = true
  }
  def findWord():Unit={
    new myFrame1(nstack).top.visible = true
  }
  def upperCase(): Unit ={
    new myFrame2(nstack).top.visible = true
  }
}

class myFrame (l:List[String]) extends SimpleSwingApplication{//Frame for the first button (other than the "c")
  def top = new MainFrame{
    title ="Counter of letters"

    contents = new BoxPanel(Orientation.Vertical) {

      contents += new TextField("select a letter"){
        Mediator2.panel1 = this
        focusable = true
      }

      contents += new Button("Count!") {
        Mediator2.buttons += this
      }

      contents += new TextField("The number of times it appears is:"){
        Mediator2.panel = this
        focusable = false
      }
      contents += new Button(Action("Close Me") {dispose()})

      border = Swing.EmptyBorder(30,30,30,30)
    }
    Mediator2.moreInteractions(l)
  }
}

object Mediator2 extends Reactor{//Mediator for the frame of counting letters
  var panel:TextComponent = null
  var panel1:TextComponent = null
  var buttons = Set[Button]()
  var count =0
  def moreInteractions(l:List[String]): Unit = {
    buttons.foreach(listenTo(_))
    reactions += { case ButtonClicked(b) if b.text == "Count!" => {
      if(panel1.text.length ==1 ){
        val ch = panel1.text.charAt(0)
        for(word <- l){
          for(a <- 0 to word.length-1)
            if(word(a)== ch)  count += 1
        }
        panel.text = "The number of times it appears is: " + count

        count =0
      }
      else{
        panel.text = "Please introduce a good value"
      }

    }}
  }
}

class myFrame1 (l:List[String]) extends SimpleSwingApplication{// Frame to find the number of times a word appears
  def top = new MainFrame{
    title ="Word Finder"


    val boton = new Button("Search")
    val count = new Label("The times it appears is: ")

    contents = new BoxPanel(Orientation.Vertical){
      contents += new TextField("Write a Word"){
        background = new Color(0xddffdd);
        border = Swing.EtchedBorder
        focusable = true
        listenTo(keys)
        Mediator1.panel = this
      }
      contents += new Button("Search"){ Mediator1.buttons += this}
      contents += new TextField("The times it appears is: "){
        focusable = false
        Mediator1.panel1 = this
      }
      contents += new Button(Action("Close Me") {dispose()})
    }
    Mediator1.doThings(l)
  }
}
object Mediator1 extends Reactor {//Mediator for the word finder
  var panel:TextComponent = null
  var panel1:TextComponent = null
  var a =0
  var buttons = Set[Button]()
  def doThings(l:List[String]): Unit ={
    buttons.foreach(listenTo(_))
    reactions += { case ButtonClicked(b) if b.text == "Search" => {
      for(word<- l){
        if(word == panel.text) a+=1
      }
      panel1.text = "The times it appears is: "+a
      a=0
    }
    }
  }
}

class myFrame2 (l:List[String]) extends SimpleSwingApplication{//Frame for the UpperCase
  def top = new MainFrame{frame =>
    title ="to upper"
    var s = ""
    for(word <- l){s+= word.toUpperCase(); s+=" "}
    val text = new Label(s)


    contents = new BoxPanel(Orientation.Vertical) {

      contents += Swing.VStrut(5)
      contents += text
      contents += new Button(Action("Close Me") {dispose()})

      border = Swing.EmptyBorder(30,30,30,30)

    }
    peer.setLocationRelativeTo(null)

  }
}

