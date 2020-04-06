package com.testframwork.testscala.objectTest

class Demo01 {
  val name = "张三"
  val age = 18

  def sayName() = {
    "my name is " + name
  }
}

object Lessonclass {
  def main(args: Array[String]): Unit = {

    val person = new Demo01()
    val somthing = person.sayName()
    println(person.age)
    print("something: " + somthing)
    val v1 = fun1(5)
    println("value:  ", v1)

    /**
      * 创建二维数组
      */
    val secArray = new Array[Array[Int]](10)
    for (index <- 0 until secArray.length) {
      secArray(index) = new Array[Int](10)
      for(jIndex<- 0 until 10){
        secArray(index)(jIndex) = jIndex
      }
    }

    secArray.foreach(i=>{
      i.foreach(j=>{
        println()
        print(j)

      })

    })


  }

  def fun1(num: Int): Int = {
    if (num == 1 || num == 0) return 1
    else num * fun1(num - 1)
  }


}