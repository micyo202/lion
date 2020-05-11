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
import com.lion.common.base.service.BaseService;
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
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @Override
    public PageInfo<T> page(int pageNum, int pageSize) {
        return page(pageNum, pageSize, null);
    }

    @Override
    public PageInfo<T> page(int pageNum, int pageSize, String orderBy) {
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
    public PageInfo<T> page(Wrapper<T> queryWrapper, int pageNum, int pageSize) {
        return page(queryWrapper, pageNum, pageSize, null);
    }

    @Override
    public PageInfo<T> page(Wrapper<T> queryWrapper, int pageNum, int pageSize, String orderBy) {
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
    public PageInfo page(String statement, int pageNum, int pageSize) {
        return page(statement, null, pageNum, pageSize, null);
    }

    @Override
    public PageInfo page(String statement, int pageNum, int pageSize, String orderBy) {
        return page(statement, null, pageNum, pageSize, orderBy);
    }

    @Override
    public PageInfo page(String statement, Object parameter, int pageNum, int pageSize) {
        return page(statement, parameter, pageNum, pageSize, null);
    }

    @Override
    public PageInfo page(String statement, Object parameter, int pageNum, int pageSize, String orderBy) {
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

    @Override
    public T getByStatement(String statement) {
        return getByStatement(statement, null);
    }

    @Override
    public T getByStatement(String statement, Object parameter) {
        if (null == parameter) {
            return sqlSessionTemplate.selectOne(statement);
        } else {
            return sqlSessionTemplate.selectOne(statement, parameter);
        }
    }

    @Override
    public List<T> listByStatement(String statement) {
        return listByStatement(statement, null);
    }

    @Override
    public List<T> listByStatement(String statement, Object parameter) {
        if (null == parameter) {
            return sqlSessionTemplate.selectList(statement);
        } else {
            return sqlSessionTemplate.selectList(statement, parameter);
        }
    }

    @Override
    public int saveByStatement(String statement) {
        return saveByStatement(statement, null);
    }

    @Override
    public int saveByStatement(String statement, Object parameter) {
        if (null == parameter) {
            return sqlSessionTemplate.insert(statement);
        } else {
            return sqlSessionTemplate.insert(statement, parameter);
        }
    }

    @Override
    public int updateByStatement(String statement) {
        return updateByStatement(statement, null);
    }

    @Override
    public int updateByStatement(String statement, Object parameter) {
        if (null == parameter) {
            return sqlSessionTemplate.update(statement);
        } else {
            return sqlSessionTemplate.update(statement, parameter);
        }
    }

}
