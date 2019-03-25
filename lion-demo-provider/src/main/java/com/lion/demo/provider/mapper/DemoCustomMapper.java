package com.lion.demo.provider.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DemoCustomMapper
 * TODO
 *
 * @author Yanzheng 严正
 * @date 2019/01/09
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Service("demoCustomMapper")
public interface DemoCustomMapper {

    @Select("SELECT * FROM demo")
    List<Object> selectAll();

}
