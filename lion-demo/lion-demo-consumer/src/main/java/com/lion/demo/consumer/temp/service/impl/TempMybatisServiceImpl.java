package com.lion.demo.consumer.temp.service.impl;

import com.lion.common.base.service.impl.BaseServiceImpl;
import com.lion.demo.consumer.temp.entity.TempMybatis;
import com.lion.demo.consumer.temp.mapper.TempMybatisMapper;
import com.lion.demo.consumer.temp.service.ITempMybatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Yanzheng https://github.com/micyo202
 * @since 2020-02-15
 */
@Service
public class TempMybatisServiceImpl extends BaseServiceImpl<TempMybatisMapper, TempMybatis> implements ITempMybatisService {

    @Autowired
    private TempMybatisMapper tempMybatisMapper;

    public List<TempMybatis> customSqlForMapper() {
        return tempMybatisMapper.selectByCustomSqlForMapper();
    }

    public List<TempMybatis> customSqlForXml() {
        return tempMybatisMapper.selectByCustomSqlForXml();
    }
}
