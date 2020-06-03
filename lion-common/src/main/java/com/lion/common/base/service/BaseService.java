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
package com.lion.common.base.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * IBaseService
 * 通用 service 接口，主要封装 Mybatis 分页方法
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/2/12
 */
public interface BaseService<T> extends IService<T> {

    /**
     * 分页查询
     *
     * @param pageNum  页码值
     * @param pageSize 每页大小
     * @return PageInfo 分页对象
     */
    PageInfo<T> page(int pageNum, int pageSize);

    /**
     * 分页查询
     *
     * @param pageNum  页码值
     * @param pageSize 每页大小
     * @param orderBy  排序字段
     * @return PageInfo 分页对象
     */
    PageInfo<T> page(int pageNum, int pageSize, String orderBy);

    /**
     * 分页查询
     *
     * @param queryWrapper 条件构造器
     * @param pageNum      页码值
     * @param pageSize     每页大小
     * @return PageInfo 分页对象
     */
    PageInfo<T> page(Wrapper<T> queryWrapper, int pageNum, int pageSize);

    /**
     * 分页查询
     *
     * @param queryWrapper 条件构造器
     * @param pageNum      页码值
     * @param pageSize     每页大小
     * @param orderBy      排序字段
     * @return PageInfo 分页对象
     */
    PageInfo<T> page(Wrapper<T> queryWrapper, int pageNum, int pageSize, String orderBy);

    /**
     * 分页查询
     *
     * @param statement Mapper声明
     * @param pageNum   页码值
     * @param pageSize  每页大小
     * @return PageInfo 分页对象
     */
    PageInfo page(String statement, int pageNum, int pageSize);

    /**
     * 分页查询
     *
     * @param statement Mapper声明
     * @param pageNum   页码值
     * @param pageSize  每页大小
     * @param orderBy   排序字段
     * @return PageInfo 分页对象
     */
    PageInfo page(String statement, int pageNum, int pageSize, String orderBy);

    /**
     * 分页查询
     *
     * @param statement Mapper声明
     * @param parameter 参数
     * @param pageNum   页码值
     * @param pageSize  每页大小
     * @return PageInfo 分页对象
     */
    PageInfo page(String statement, Object parameter, int pageNum, int pageSize);

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
    PageInfo page(String statement, Object parameter, int pageNum, int pageSize, String orderBy);

    /**
     * 查询单个对象
     *
     * @param statement Mapper声明
     * @return T 对象
     */
    T getByStatement(String statement);

    /**
     * 查询单个对象
     *
     * @param statement Mapper声明
     * @param parameter 参数
     * @return T 对象
     */
    T getByStatement(String statement, Object parameter);

    /**
     * 查询对象集合
     *
     * @param statement Mapper声明
     * @return List 对象集合
     */
    List<T> listByStatement(String statement);

    /**
     * 查询对象集合
     *
     * @param statement Mapper声明
     * @param parameter 参数
     * @return List 对象集合
     */
    List<T> listByStatement(String statement, Object parameter);

    /**
     * 保存
     *
     * @param statement Mapper声明
     * @return int 保存条数
     */
    int saveByStatement(String statement);

    /**
     * 保存
     *
     * @param statement Mapper声明
     * @param parameter 参数
     * @return int 保存条数
     */
    int saveByStatement(String statement, Object parameter);

    /**
     * 修改
     *
     * @param statement Mapper声明
     * @return int 更新条数
     */
    int updateByStatement(String statement);

    /**
     * 修改
     *
     * @param statement Mapper声明
     * @param parameter 参数
     * @return int 更新条数
     */
    int updateByStatement(String statement, Object parameter);
}