package InClass

object lab101 {
  def stream(list:List[Any]):String={
    list.map(matching).mkString(" ")
  }
  def matching(value: Any):String={
    value match{
      case x: String => "String(" + x + ")"
      case x: Int    => "Int(" + x + ")"
      case x: Double => "Double(" + x + ")"
    }
  }

  val l = List(1, "hello", 2.56, 0x45, "key")
  val s = stream(l)
  println(s)

}
