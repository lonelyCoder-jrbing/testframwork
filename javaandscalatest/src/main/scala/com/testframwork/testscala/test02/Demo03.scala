package com.testframwork.testscala.test02

import java.util

object Demo03 {
  def main(args: Array[String]): Unit = {
    val hashMap = new util.HashMap[String, String]()
    hashMap.put("name", "jurongbing");
    hashMap.put("age", "12");
      println(hashMap)

  }


}
