package com.testframwork.testscala.mapTest

object Demo02 {
  val map = Map(
    "1" -> "bj",
    2 -> "jurongbing",
    3 -> "gz"


  )

  def main(args: Array[String]): Unit = {
    val keys = map.keys
    println("keys:  ", keys)
    val it = keys.iterator
    while (it.hasNext) {
      val value = it.next()
      println("value:  ", value)


      val v = map.get(value).get
      println("mapV:  ", v)
    }
    //map遍历
    for(k<- map) println(k._1+ "\t"+ k._2)

    //filter过滤
    map.filter(el=>{
      Integer.parseInt(el._1+"")>2
    }).foreach(println)

  }


}
