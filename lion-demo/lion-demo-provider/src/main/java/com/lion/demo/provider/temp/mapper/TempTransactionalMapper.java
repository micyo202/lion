package com.lion.demo.provider.temp.mapper;

import com.lion.demo.provider.temp.model.TempTransactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface TempTransactionalMapper extends Mapper<TempTransactional>, MySqlMapper<TempTransactional> {
}