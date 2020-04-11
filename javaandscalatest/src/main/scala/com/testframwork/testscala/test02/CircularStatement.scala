package com.testframwork.testscala.test02

object CircularStatement {

  def main(args: Array[String]): Unit = {

    for (i <- 1 to 3) {
      for (j <- 1 to 3) {
        if (i != j) {
          println("i :  ", i)
        }
      }
    }
    //也可以使用一行搞定
    for (i <- 1 to 3; j <- 1 to 3 if i != j) {
     println("i:  ",i)
    }
    val arr = Array(1,2,3,4,5)
    var res = for(item <- arr if item % 2 == 0) yield item

    for(item <- 0 until res.length){
      println("res[i]:  ",res(item))

    }


  }
}
