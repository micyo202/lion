package com.lion.demo.sample.temp.mapper

import org.apache.ibatis.annotations.Select

trait SampleScalaMapper {

  @Select(Array("SELECT * FROM temp_mybatis"))
  def getAll: java.util.List[java.util.Map[String, Any]]

}
