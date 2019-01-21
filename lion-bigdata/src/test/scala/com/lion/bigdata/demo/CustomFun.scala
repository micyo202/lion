package com.lion.bigdata.demo

/**
  * CustomFun
  * 自定义函数
  *
  * @author Yanzheng 严正
  * @date 2019/01/06
  */
object CustomFun {

  // 标准写法
  def fun_1(a: Int, b: Int): Int = a + b

  // 省略返回类型跟return
  def fun_2(a: Int, b: Int) = a + b

  // 省略大括号
  def fun_3(a: Int, b: Int) = a + b

  // spark常用方式
  val fun_4 = (a: Int, b: Int) => a + b

  // 测试方法
  def main(args: Array[String]): Unit = {
    println("fun_1: " + fun_1(1, 6))
    println("fun_2: " + fun_2(2, 7))
    println("fun_3: " + fun_3(3, 8))
    println("fun_4: " + fun_4(4, 9))
  }

}
