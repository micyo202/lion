package com.lion.common.base.mapper;

import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * BaseMapper
 * 通用Mapper接口类
 *
 * @author Yanzheng
 * @date 2019/11/19
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T>, IdsMapper<T> {
}