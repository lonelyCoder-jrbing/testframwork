package com.testframwork.testscala.wordcount

object WC01 {
  def readLogfromTxt(filePath: String): Array[String] = {
    import scala.io._
    val source = Source.fromFile(filePath, "UTF-8")
    val lines = source.getLines().toArray
    source.close()
    return lines
  }

  def main(args: Array[String]): Unit = {
    //读取日志文件
    val lines = readLogfromTxt("D:\\code\\springCloud\\scalaTest\\src\\wordcount\\controller.log")
    //将每一行的单词按照“”进行切分，之后映射成为(word->1)的形式,最后按照键进行分组，
    val groupBykey = lines.flatMap(el => el.split(" ")).map((_, 1)).groupBy(_._1)
    println("groupBykey:  ", groupBykey)
    val wordCount = groupBykey.map(t => (t._1, t._2.length))
    println("wordCount: ",wordCount)

  }


}
