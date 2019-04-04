package com.lion.bigdata.demo

/**
  * HelloWorld
  * 自定义函数
  *
  * @author Yanzheng
  * @date 2019/01/06
  *       Copyright 2019 Yanzheng. All rights reserved.
  */
class HelloWorld {

  def add(x: Int, y: Int): Int = x + y

  def subtract(x: Int, y: Int) = x - y

  def multiply(x: Int, y: Int) = x * y

  val divide = (x: Int, y: Int) => x / y

}