package InClass

import scala.math.{pow, sqrt}

object Eje61 {
  val pi=3.1415

  class Angle( m_angle: Double){

    val a= m_angle
    def printValue()={
      println(a)
    }
    def -(an1: Angle):Angle={
      new Angle(this.a-an1.a)
    }

    def +(an1: Angle):Angle={
      new Angle(this.a+an1.a)
    }
    def *(v:Double):Angle={
      new Angle(this.a*v)
    }
    def /(v:Double):Angle={
      new Angle(this.a/v)
    }
  }
  object Angle{
    def apply(an: Double): Angle ={
      new Angle(wrapping(an))
    }
    def halfPi():Angle={
      new Angle(pi/2)
    }
    def Pi():Angle={
      new Angle(pi)
    }
    def zero():Angle={
      new Angle(0)
    }
    def wrapping(buff: Double):Double={
      if(buff >= pi)wrapping(buff-(2*pi))
      else if (buff < (pi*(-1))) wrapping(buff+(2*pi))
      else return buff
    }
    def DR(an1: Angle,an2: Angle, an3: Angle, an4: Angle):Angle={
      val new_angle = sqrt(pow((an1.a-an2.a),2) + pow((an3.a-an4.a),2))
      new Angle(new_angle)
    }


  }

  def main(args: Array[String]): Unit = {
    val a= Angle(20)
    val b = Angle(3)
    val c = Angle (2)
    val d = Angle(1)

    /*val e = Angle.DR(a,b,c,d)
    a.printValue()
    e.printValue()
    val f = Angle.Pi()
    val g = Angle.zero()
    val h = Angle.halfPi()
    f.printValue()
    g.printValue()
    h.printValue()
    val i = c-d*/
    val j= b+c
    /*val k = d*1.5
    val l = c/2
    i.printValue()
    j.printValue()
    k.printValue()
    l.printValue()*/
    j.printValue()
  }

}
