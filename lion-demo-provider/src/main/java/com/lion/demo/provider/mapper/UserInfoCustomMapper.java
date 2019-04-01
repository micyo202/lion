package com.lion.demo.provider.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserInfoCustomMapper
 * TODO
 *
 * @author Yanzheng
 * @date 2019/04/01
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Service("userInfoCustomMapper")
public interface UserInfoCustomMapper {

    @Select("SELECT * FROM user_info")
    List<Object> selectAll();

}
