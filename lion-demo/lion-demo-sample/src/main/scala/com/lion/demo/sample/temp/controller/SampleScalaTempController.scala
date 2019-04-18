package com.lion.demo.sample.temp.controller

import java.util

import com.lion.demo.sample.temp.mapper.SampleScalaMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{RequestMapping, RestController}

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
