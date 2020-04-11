package com.testframwork.testscala.test02

object OperatorReloadingMethod {

  def main(args: Array[String]): Unit = {

    var res1 = 1 + 2
    println(res1)
    //上面的运算符“+”其实是运算符重载成方法，即".+"
    var res2 = 1.+(2)
    println(res2)
    val res3 = 1 to 10
    println(res3)
    //上面的运算符“to”其实也是运算符重载成方法，即".to"
    val res4 = 1.to(10)

    println(res4)
     val res5 = 2.to(5)
    println(res5)

  }


}
