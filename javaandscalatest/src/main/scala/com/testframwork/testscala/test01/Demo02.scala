package com.testframwork.testscala.test01

object Demo02 {

  implicit def strToInt(str: String) = str.toInt

  //在使用隐式转换之前，需要使用import将将隐式转换引用到当前作用域，或者就在作用域中定义隐式转换。

  def main(args: Array[String]): Unit = {
    val writeCode = Map("Spark" -> "Scala", "Hadoop" -> "Java")
    val language = writeCode.get("Spark").get
    println("language:   ", language)

    val words = writeCode.get("kafka").getOrElse("noneSuchElement")
    println("words:  ", words)
    var c = Option("jrbing")
    c.map(_.length).map("length:  " + _).foreach(println)
    var value1 = Option(("jrbing", "zhang"))
    //value1.foreach(x=>Option(x).map(".."+_).foreach(println))

    value1.foreach(println)
    val x: Int = "00"
    println("x:  ", x)

  }


}
