package com.testframwork.testscala.test02

object Demo01 {
  def main(args: Array[String]): Unit = {
    var arr = Array("hello", "yinzhengjie", "hello", "world", "yinzhengjie", "big", "data")
    val result = arr.map((_, 1)).groupBy(_._1).mapValues(_.length).toList.sortBy(-_._2)
    println("result:  " + result)
    val Array(a, b, _*) = Array("A", "B", "C", "D")
    println(s"a = ${a},b = ${b}")
    val sex = "boy"
    val called = if (sex == "boy") "小哥哥" else "小姐姐"
    println("called:  ", called)


  }


}
