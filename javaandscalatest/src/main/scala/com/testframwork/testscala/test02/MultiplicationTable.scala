package com.testframwork.testscala.test02

object MultiplicationTable {
  def main(args: Array[String]): Unit = {

    //    for_while_99(9)
    println("=======我是分割线==========")
    //             while_while_99(10)
    println("=======我是分割线==========")
    for_for_99(9)
    println("=======我是分割线==========")
    //             senior_for(9)
    for_for_99_01(9)
    println()

  }


  def for_for_99(arg: Int): Unit = {
    for (i <- 1 to arg) {
      for (j <- 1 to i) {
        print(j + "*" + i + "=" + i * j + " ")
        if (i == j) {
          println()
        }

      }
    }
  }

  def for_for_99_01(arg: Int): Unit = {
    for (i <- 1 to arg; j <- 1 to i) {
      print(j + "*" + i + "=" + i * j + " ")
      if (i == j) {
        println()
      }
    }
  }


}

