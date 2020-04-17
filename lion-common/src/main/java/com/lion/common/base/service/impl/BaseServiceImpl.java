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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lion.common.base.service.IBaseService;
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
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements IBaseService<T> {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @Override
    public PageInfo<T> selectAllByPage(int pageNum, int pageSize) {
        return selectAllByPage(pageNum, pageSize, null);
    }

    @Override
    public PageInfo<T> selectAllByPage(int pageNum, int pageSize, String orderBy) {
        if (null == orderBy) {
            PageHelper.startPage(pageNum, pageSize);
        } else {
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }

        List<T> list = list();
        PageInfo<T> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public PageInfo<T> selectByWrapperPage(Wrapper<T> queryWrapper, int pageNum, int pageSize) {
        return selectByWrapperPage(queryWrapper, pageNum, pageSize, null);
    }

    @Override
    public PageInfo<T> selectByWrapperPage(Wrapper<T> queryWrapper, int pageNum, int pageSize, String orderBy) {
        if (null == orderBy) {
            PageHelper.startPage(pageNum, pageSize);
        } else {
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }

        List<T> list = list(queryWrapper);
        PageInfo<T> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public PageInfo selectByStatmentPage(String statement, int pageNum, int pageSize) {
        return selectByStatmentPage(statement, null, pageNum, pageSize, null);
    }

    @Override
    public PageInfo selectByStatmentPage(String statement, int pageNum, int pageSize, String orderBy) {
        return selectByStatmentPage(statement, null, pageNum, pageSize, orderBy);
    }

    @Override
    public PageInfo selectByStatmentPage(String statement, Object parameter, int pageNum, int pageSize) {
        return selectByStatmentPage(statement, parameter, pageNum, pageSize, null);
    }

    @Override
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

}
