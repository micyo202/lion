package com.lion.demo.sample.temp.controller

import java.util

import com.lion.demo.sample.temp.mapper.SampleScalaMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{RequestMapping, RestController}

/**
  * SampleScalaTempController
  * Scala示例控制器
  *
  * @author Yanzheng https://github.com/micyo202
  * @date 2019/09/02
  * Copyright 2019 Yanzheng. All rights reserved.
  */
@RestController
@RequestMapping(Array("/scala/temp"))
class SampleScalaTempController {

  @Autowired
  val sampleScalaMapper: SampleScalaMapper = null

  @RequestMapping(value = Array("/getAll"))
  def getAll(): java.util.List[util.Map[String, Any]] = {
    val value: util.List[util.Map[String, Any]] = sampleScalaMapper.getAll
    value
  }

}
