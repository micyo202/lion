package com.lion.demo.provider.temp.service;

import com.lion.common.base.service.mybatis.BaseService;
import com.lion.demo.provider.temp.entity.TempTransactional;
import com.lion.demo.provider.temp.mapper.TempTransactionalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TempTransactionalService
 * TODO
 *
 * @author Yanzheng
 * @date 2019/11/20
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Service
public class TempTransactionalService extends BaseService<TempTransactional> {

    @Autowired
    private TempTransactionalMapper tempTransactionalMapper;

    public void insert(TempTransactional tempTransactional) {
        tempTransactionalMapper.insertSelective(tempTransactional);
    }

}
