package com.testframwork.testscala.mapTest

object Demo01 {
  val nameList = List(
    "hello list",
    "hello world",
    "hello shanghai"
  )

  def main(args: Array[String]): Unit = {
    var result: List[String] = nameList.flatMap(el => el.split(" "))
    println(result)

    var mapResult:List[Array[String]] =  nameList.map(el=>el.split(" "))
    println("mapReuslt",mapResult)

  }


}
