package com.lion.demo.provider.function

/**
  * JunitFunction
  * 函数定义的方式
  *
  * @author Yanzheng https://github.com/micyo202
  * @date 2019/03/29
  * Copyright 2019 Yanzheng. All rights reserved.
  */
class JunitFunction {

  def test: Unit = {

    // 方法一：完整写法
    def sumFun1(a: Int, b: Int): Int = { a + b }
    def sumFun1x: (Int, Int) => Int = (a, b) => { a + b }
    val sumFun1v = (a: Int, b: Int) => a + b

    // 方法二：省略写法（省略返回类型，花括号）
    def sumFun2(a: Int, b: Int) = a + b
    def sumFun2x = (a: Int, b: Int) => a + b
    val sumFun2v = (a: Int, b: Int) => a + b

  }

}
