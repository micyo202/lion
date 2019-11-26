package com.lion.demo.consumer.temp.mapper

import org.apache.ibatis.annotations.Select

/**
  * ScalaTempMapper
  * Scala示例Mapper接口类
  *
  * @author Yanzheng https://github.com/micyo202
  * @date 2019/09/02
  * Copyright 2019 Yanzheng. All rights reserved.
  */
trait ScalaTempMapper {

  @Select(Array("SELECT * FROM temp_mybatis"))
  def getAll: java.util.List[java.util.Map[String, Any]]

}