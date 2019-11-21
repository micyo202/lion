package com.lion.demo.provider.temp.mapper;

import com.lion.common.base.mapper.BaseMapper;
import com.lion.demo.provider.temp.entity.TempMybatis;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TempMybatisMapper extends BaseMapper<TempMybatis> {

    List<TempMybatis> selectByCustomSqlForXml();

    @Select("SELECT * FROM temp_mybatis")
    List<TempMybatis> selectByCustomSqlForMapper();

    @Insert("insert into temp_mybatis(id, name, type, status, create_time) values(#{id}, #{name}, #{type}, #{status}, #{createTime})")
    int insertByCustomSqlForMapper(@Param("id") String id, @Param("name") String name, @Param("type") int type, @Param("status") int status, @Param("createTime") String createTime);

}