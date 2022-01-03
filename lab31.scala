package clases

object lab31 {

  def pi:Double={return 3.1415}

  def pi(n:Double):Double={
    return n*3.1415
  }
  def printArg(arg:String*):Unit={
    if(arg.isEmpty)println("-")
    else {
      for (a<- arg)println(a)
    }
  }
  def repN(buff:Int,f:Int=>Int,n:Int): Double ={
    if(n==0)return buff
    else{
      f.apply(buff)*repN(buff,f,n-1 )
    }
  }

  def main(args: Array[String]): Unit = {
    println( pi )
    println( pi(pi) )
    println( pi(pi(pi)) )
    printArg(arg="hello")
    printArg()
    println(repN(1, (x: Int) => 2*x , 5 ))
  }

}
