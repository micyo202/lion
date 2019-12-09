package com.lion.demo.consumer.temp.mapper;

import com.lion.common.base.mapper.BaseMapper;
import com.lion.demo.consumer.temp.entity.TempMybatis;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TempMybatisMapper extends BaseMapper<TempMybatis> {

    List<TempMybatis> selectByCustomSqlForXml();

    @Select("SELECT * FROM temp_mybatis")
    List<TempMybatis> selectByCustomSqlForMapper();

    @Insert("insert into temp_mybatis(id, name, valid, create_time, update_time) values(#{id}, #{name}, #{valid}, #{createTime}, #{updateTime})")
    int insertByCustomSqlForMapper(@Param("id") String id, @Param("name") String name, @Param("valid") Boolean valid, @Param("createTime") String createTime, @Param("updateTime") String updateTime);
}