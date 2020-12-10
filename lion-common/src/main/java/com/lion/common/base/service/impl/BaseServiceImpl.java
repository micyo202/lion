/**
 *   Copyright 2019 Yanzheng (https://github.com/micyo202). All rights reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.lion.common.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.lion.common.base.service.BaseService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * BaseServiceImpl
 * 通用 service 实现类，主要封装 Mybatis 分页方法
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/2/12
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {

    @Autowired
    protected SqlSessionTemplate sqlSessionTemplate;

    /**
     * 分页查询
     *
     * @param pageNum  页码值
     * @param pageSize 每页大小
     * @return PageInfo 分页对象
     */
    @Override
    public PageInfo<T> page(int pageNum, int pageSize) {
        return page(pageNum, pageSize, null);
    }

    /**
     * 分页查询
     *
     * @param pageNum  页码值
     * @param pageSize 每页大小
     * @param orderBy  排序字段
     * @return PageInfo 分页对象
     */
    @Override
    public PageInfo<T> page(int pageNum, int pageSize, String orderBy) {
        if (StringUtils.isBlank(orderBy)) {
            PageMethod.startPage(pageNum, pageSize);
        } else {
            PageMethod.startPage(pageNum, pageSize, orderBy);
        }

        List<T> list = list();
        return new PageInfo<>(list);
    }

    /**
     * 分页查询
     *
     * @param queryWrapper 条件构造器
     * @param pageNum      页码值
     * @param pageSize     每页大小
     * @return PageInfo 分页对象
     */
    @Override
    public PageInfo<T> page(Wrapper<T> queryWrapper, int pageNum, int pageSize) {
        return page(queryWrapper, pageNum, pageSize, null);
    }

    /**
     * 分页查询
     *
     * @param queryWrapper 条件构造器
     * @param pageNum      页码值
     * @param pageSize     每页大小
     * @param orderBy      排序字段
     * @return PageInfo 分页对象
     */
    @Override
    public PageInfo<T> page(Wrapper<T> queryWrapper, int pageNum, int pageSize, String orderBy) {
        if (StringUtils.isBlank(orderBy)) {
            PageMethod.startPage(pageNum, pageSize);
        } else {
            PageMethod.startPage(pageNum, pageSize, orderBy);
        }

        List<T> list = list(queryWrapper);

        return new PageInfo<>(list);
    }

    /**
     * 分页查询
     *
     * @param statement Mapper声明
     * @param pageNum   页码值
     * @param pageSize  每页大小
     * @return PageInfo 分页对象
     */
    @Override
    public PageInfo<T> page(String statement, int pageNum, int pageSize) {
        return page(statement, null, pageNum, pageSize, null);
    }

    /**
     * 分页查询
     *
     * @param statement Mapper声明
     * @param pageNum   页码值
     * @param pageSize  每页大小
     * @param orderBy   排序字段
     * @return PageInfo 分页对象
     */
    @Override
    public PageInfo<T> page(String statement, int pageNum, int pageSize, String orderBy) {
        return page(statement, null, pageNum, pageSize, orderBy);
    }

    /**
     * 分页查询
     *
     * @param statement Mapper声明
     * @param parameter 参数
     * @param pageNum   页码值
     * @param pageSize  每页大小
     * @return PageInfo 分页对象
     */
    @Override
    public PageInfo<T> page(String statement, Object parameter, int pageNum, int pageSize) {
        return page(statement, parameter, pageNum, pageSize, null);
    }

    /**
     * 分页查询
     *
     * @param statement Mapper声明
     * @param parameter 参数
     * @param pageNum   页码值
     * @param pageSize  每页大小
     * @param orderBy   排序字段
     * @return PageInfo 分页对象
     */
    @Override
    public PageInfo<T> page(String statement, Object parameter, int pageNum, int pageSize, String orderBy) {
        if (StringUtils.isBlank(orderBy)) {
            PageMethod.startPage(pageNum, pageSize);
        } else {
            PageMethod.startPage(pageNum, pageSize, orderBy);
        }

        List<T> list;

        if (ObjectUtils.isEmpty(parameter)) {
            list = sqlSessionTemplate.selectList(statement);
        } else {
            list = sqlSessionTemplate.selectList(statement, parameter);
        }

        return new PageInfo<>(list);
    }

    /**
     * 查询单个对象
     *
     * @param statement Mapper声明
     * @return T 对象
     */
    @Override
    public T getByStatement(String statement) {
        return getByStatement(statement, null);
    }

    /**
     * 查询单个对象
     *
     * @param statement Mapper声明
     * @param parameter 参数
     * @return T 对象
     */
    @Override
    public T getByStatement(String statement, Object parameter) {
        if (ObjectUtils.isEmpty(parameter)) {
            return sqlSessionTemplate.selectOne(statement);
        } else {
            return sqlSessionTemplate.selectOne(statement, parameter);
        }
    }

    /**
     * 查询对象集合
     *
     * @param statement Mapper声明
     * @return List 对象集合
     */
    @Override
    public List<T> listByStatement(String statement) {
        return listByStatement(statement, null);
    }

    /**
     * 查询对象集合
     *
     * @param statement Mapper声明
     * @param parameter 参数
     * @return List 对象集合
     */
    @Override
    public List<T> listByStatement(String statement, Object parameter) {
        if (ObjectUtils.isEmpty(parameter)) {
            return sqlSessionTemplate.selectList(statement);
        } else {
            return sqlSessionTemplate.selectList(statement, parameter);
        }
    }

    /**
     * 保存
     *
     * @param statement Mapper声明
     * @return int 保存条数
     */
    @Override
    public int saveByStatement(String statement) {
        return saveByStatement(statement, null);
    }

    /**
     * 保存
     *
     * @param statement Mapper声明
     * @param parameter 参数
     * @return int 保存条数
     */
    @Override
    public int saveByStatement(String statement, Object parameter) {
        if (ObjectUtils.isEmpty(parameter)) {
            return sqlSessionTemplate.insert(statement);
        } else {
            return sqlSessionTemplate.insert(statement, parameter);
        }
    }

    /**
     * 修改
     *
     * @param statement Mapper声明
     * @return int 更新条数
     */
    @Override
    public int updateByStatement(String statement) {
        return updateByStatement(statement, null);
    }

    /**
     * 修改
     *
     * @param statement Mapper声明
     * @param parameter 参数
     * @return int 更新条数
     */
    @Override
    public int updateByStatement(String statement, Object parameter) {
        if (ObjectUtils.isEmpty(parameter)) {
            return sqlSessionTemplate.update(statement);
        } else {
            return sqlSessionTemplate.update(statement, parameter);
        }
    }

}
