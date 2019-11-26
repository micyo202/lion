package com.lion.demo.consumer.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.{RequestMapping, RequestParam, RestController}

import scala.collection.JavaConverters._
import scala.collection.mutable._

/**
  * ConsumerScalaDemoController
  * Scala示例代码Mybatis
  *
  * @author Yanzheng https://github.com/micyo202
  * @date 2019/09/02 Copyright 2019 Yanzheng. All rights reserved.
  */
@RestController
@RequestMapping(Array("/scala"))
class ConsumerScalaDemoController {

  @Value("${server.port}")
  val port: String = null

  @RequestMapping(Array("/init"))
  def init(): String = "Initialize Success."

  @RequestMapping(Array("/hi"))
  def hi(@RequestParam(value = "name", defaultValue = "lion") name: String): String = "Hi: " + name + ", I'm from port " + port;

  @RequestMapping(value = Array("/map"))
  def map(): java.util.Map[String, Any] = {
    val res = Map("Java" -> "1.8.0_172", ("Scala", 2.11), "Objective-C" -> "iOS Develop", "Python" -> 3, "Shell" -> "Linux")
    res.put("XXX", 666)
    res("Key") = "Value"
    res.asJava
  }

  @RequestMapping(value = Array("/list"))
  def list(): java.util.List[String] = {
    val res = ListBuffer("Java", "Scala", "Objective-C", "Shell")
    val resEx = ListBuffer("Python", "R")
    res ++= resEx
    res.asJava
  }

}
