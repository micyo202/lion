package com.lion.demo.provider.collection

import org.junit.Test

import scala.collection.mutable.ListBuffer

/**
  * JunitList
  * List集合操作方法
  *
  * @author Yanzheng https://github.com/micyo202
  * @date 2019/03/29
  * Copyright 2019 Yanzheng. All rights reserved.
  */
class JunitList {

  @Test
  def test: Unit = {
    // 第一个不可变list
    val l = List("Java", "Scala", "Objective-C")
    // 不可变list追加元素
    val l1 = l :+ "666"
    // 第二个不可变list
    val l2 = List("Shell", "Python")
    // 两个list合并
    val l3 = l1 ::: l2 // 也可使用 l ++ l2
    println(l3)

    /*==================================================*/

    // 第一个可变list
    val mutableList = new ListBuffer[Int]
    // 循环体
    for (i <- 1 to 10) {
      // 可变list添加数据
      mutableList += i * 100
    }
    // 第二个可变list
    val mutableList1 = new ListBuffer[Int]
    // 可变list追加数据
    mutableList1.append(3, 6, 9)
    // 两个可变list合并
    mutableList ++= mutableList1
    // 排序
    val sorted = mutableList.sorted.reverse
    println(sorted)

  }

}
