package com.lion.demo.provider.temp.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TempMybatisCustomMapper
 * TODO
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/04/15
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Service("tempMybatisCustomMapper")
public interface TempMybatisCustomMapper {

    @Select("SELECT * FROM temp_mybatis")
    List<Object> selectAll();

}
