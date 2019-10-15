package com.lion.demo.consumer.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * TempMybatisCustomMapper
 * TODO
 *
 * @author Yanzheng
 * @date 2019/09/24
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public interface TempMybatisCustomMapper {

    @Insert("insert into temp_mybatis(id, name, type, status, create_time) values(#{id}, #{name}, #{type}, #{status}, #{createTime})")
    int insertTempMybatis(@Param("id") String id, @Param("name") String name, @Param("type") int type, @Param("status") int status, @Param("createTime") String createTime);

}
