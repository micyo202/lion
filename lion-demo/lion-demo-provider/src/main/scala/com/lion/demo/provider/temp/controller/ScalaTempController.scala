package com.lion.demo.provider.temp.controller

import java.util

import com.lion.demo.provider.temp.mapper.ScalaTempMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{RequestMapping, RestController}

/**
  * ScalaTempController
  * Scala示例控制器
  *
  * @author Yanzheng https://github.com/micyo202
  * @date 2019/09/02
  * Copyright 2019 Yanzheng. All rights reserved.
  */
@RestController
@RequestMapping(Array("/scala/temp"))
class ScalaTempController {

  @Autowired
  val scalaTempMapper: ScalaTempMapper = null

  @RequestMapping(value = Array("/getAll"))
  def getAll(): java.util.List[util.Map[String, Any]] = {
    val value: util.List[util.Map[String, Any]] = scalaTempMapper.getAll
    value
  }

}
