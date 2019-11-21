package com.lion.common.base.service.mybatis;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lion.common.base.mapper.BaseMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * BaseService
 * 通用 service 层，主要封装 Mybatis 分页方法
 *
 * @author Yanzheng
 * @date 2019/11/20
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public abstract class BaseService<T> {

    @Autowired
    private BaseMapper<T> baseMapper;

    @Autowired
    protected SqlSessionTemplate sqlSessionTemplate;

    protected Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    public PageInfo selectByStatmentPage(String statement, int pageNum, int pageSize) {
        return selectByStatmentPage(statement, null, pageNum, pageSize, null);
    }

    public PageInfo selectByStatmentPage(String statement, int pageNum, int pageSize, String orderBy) {
        return selectByStatmentPage(statement, null, pageNum, pageSize, orderBy);
    }

    public PageInfo selectByStatmentPage(String statement, Object parameter, int pageNum, int pageSize) {
        return selectByStatmentPage(statement, parameter, pageNum, pageSize, null);
    }

    public PageInfo selectByStatmentPage(String statement, Object parameter, int pageNum, int pageSize, String orderBy) {

        if (null == orderBy) {
            PageHelper.startPage(pageNum, pageSize);
        } else {
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }

        List<Object> list;

        if (null == parameter) {
            list = sqlSessionTemplate.selectList(statement);
        } else {
            list = sqlSessionTemplate.selectList(statement, parameter);
        }

        PageInfo pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    public PageInfo<T> selectByExamplePage(Example example, int pageNum, int pageSize) {
        return selectByExamplePage(example, pageNum, pageSize, null);
    }

    public PageInfo<T> selectByExamplePage(Example example, int pageNum, int pageSize, String orderBy) {

        if (null == orderBy) {
            PageHelper.startPage(pageNum, pageSize);
        } else {
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }

        List<T> list = baseMapper.selectByExample(example);
        PageInfo<T> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    public PageInfo<T> selectAllByPage(int pageNum, int pageSize) {
        return selectAllByPage(pageNum, pageSize, null);
    }

    public PageInfo<T> selectAllByPage(int pageNum, int pageSize, String orderBy) {

        if (null == orderBy) {
            PageHelper.startPage(pageNum, pageSize);
        } else {
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }

        List<T> list = baseMapper.selectAll();
        PageInfo<T> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

}