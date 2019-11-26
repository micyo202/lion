package com.lion.demo.consumer.collection

import org.junit.Test

import scala.collection.mutable

/**
  * JunitMap
  * Map集合操作方法
  *
  * @author Yanzheng https://github.com/micyo202
  * @date 2019/03/29
  * Copyright 2019 Yanzheng. All rights reserved.
  */
class JunitMap {

  @Test
  def test: Unit = {

    // 第一个不可变map
    val m = Map(("Java", 1.8), ("Scala", 2.11))
    // 不可变map添加数据
    val m1 = m + ("Shell" -> 7)
    // 第二个不可变map
    val m2 = Map(("Objective-C" -> 11), ("Python" -> 3))
    // 两个不可变map合并
    val m3 = m1 ++ m2
    println(m3)

    /*==================================================*/

    // 第一个可变map
    val mutableMap = new mutable.HashMap[String, Int]()
    // 可变map添加数据
    mutableMap += ("Hello" -> 2)
    mutableMap.put("Name", 666)
    // 第二个可变map
    val mutableMap1 = new mutable.HashMap[String, Int]()
    mutableMap1.put("XX", 999)
    // 两个可变map合并
    mutableMap ++= mutableMap1
    println(mutableMap)

    // 循环体
    for (i <- 1 until 10) {
      // 可变Map追加数据
      mutableMap += (i.toString -> i * 100)
    }
    // 排序（必须转成list才能排序）
    val sorted: List[(String, Int)] = mutableMap.toList.sortBy(_._1)
    sorted.map(println(_))
  }

}