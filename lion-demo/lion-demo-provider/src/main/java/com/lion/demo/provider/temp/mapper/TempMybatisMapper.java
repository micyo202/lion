package com.lion.demo.provider.temp.mapper;

import com.lion.demo.provider.temp.model.TempMybatis;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface TempMybatisMapper extends Mapper<TempMybatis>, MySqlMapper<TempMybatis> {

    List<TempMybatis> selectByCustomSqlForXml();

    @Select("SELECT * FROM temp_mybatis")
    List<TempMybatis> selectByCustomSqlForMapper();

    @Insert("insert into temp_mybatis(id, name, type, status, create_time) values(#{id}, #{name}, #{type}, #{status}, #{createTime})")
    int insertByCustomSqlForMapper(@Param("id") String id, @Param("name") String name, @Param("type") int type, @Param("status") int status, @Param("createTime") String createTime);
}