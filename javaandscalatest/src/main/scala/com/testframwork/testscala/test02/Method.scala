package com.testframwork.testscala.test02

object Method {

  val f1 = (x: Int) => x * 2

  def main(args: Array[String]): Unit = {
    var value = f1(5)
    println("value :", value)
  }
}
