package com.lion.demo.provider.temp.service;

import com.lion.common.base.service.mybatis.BaseService;
import com.lion.demo.provider.temp.entity.TempMybatis;
import com.lion.demo.provider.temp.mapper.TempMybatisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TempMybatisService
 * TODO
 *
 * @author Yanzheng
 * @date 2019/11/19
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Service
public class TempMybatisService extends BaseService<TempMybatis> {

    @Autowired
    private TempMybatisMapper tempMybatisMapper;

    public void insert(TempMybatis tempMybatis) {
        // 若使用 Try Catch 需要手动回滚事务：TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        tempMybatisMapper.insertSelective(tempMybatis);
    }

    public List<TempMybatis> customSqlForMapper() {
        return tempMybatisMapper.selectByCustomSqlForMapper();
    }

    public List<TempMybatis> customSqlForXml() {
        return tempMybatisMapper.selectByCustomSqlForXml();
    }

}
