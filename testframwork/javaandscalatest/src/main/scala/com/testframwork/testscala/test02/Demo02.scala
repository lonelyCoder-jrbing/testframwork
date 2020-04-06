package com.testframwork.testscala.test02

object Demo02 {
  def main(args: Array[String]): Unit = {

    //定义一个数组
    val arr = Array(1, 2, 3, 4, 5)
    for (item <- arr) {

      println(item + " ")
    }

    //取出数组中的偶数元素
    for (item <- arr) {
      if (item % 2 == 0) {
        println("item:   ",item)
      }
    }
    //另外一种方法
    for(item<- arr if (item %2 ==0)){
      print("偶数：  ",item)
    }


  }


}
