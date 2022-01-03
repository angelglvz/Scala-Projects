package InClass

object lab71 {
  abstract class Topping{

    def getTopping():String
    def getPrice():Double
  }
  trait TomatoSauce extends Topping {

    abstract override def getTopping():String={return super.getTopping() +"+ "+ "Tomato sauce "}
    abstract override def getPrice():Double={return 2.0 + super.getPrice()}
  }
  trait Mozzarella extends Topping {

    abstract override def getTopping():String={return super.getTopping() +"+ "+ "Mozzarella "}
    abstract override def getPrice():Double={return 3.0 + super.getPrice()}
  }
  trait Ham extends Topping{

    abstract override def getTopping():String={return super.getTopping() +"+ "+ "Ham "}
    abstract override def getPrice():Double={return 3.5+super.getPrice()}
  }
  trait Mushrooms extends Topping {

    abstract override def getTopping():String={return super.getTopping() + "+ " +"Mushrooms "}
    abstract override def getPrice():Double={return 5.0 + super.getPrice()}
  }

  class ThinDough extends Topping{

    def name: String ={
      return (getTopping())
    }
    def price: Double={
      return ( getPrice())
    }

    override def getTopping(): String = "Thin Dough "

    override def getPrice(): Double = 4.0
  }

  def main(args: Array[String]): Unit = {
    val myPizza = new ThinDough with TomatoSauce with Mozzarella with Ham
    println(myPizza.name + myPizza.price)

    val yourPizza = new ThinDough with TomatoSauce with Mozzarella with Mushrooms
    println(yourPizza.name + yourPizza.price)
  }
}
