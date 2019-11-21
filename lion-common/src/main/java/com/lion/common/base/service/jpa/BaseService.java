package com.lion.common.base.service.jpa;

import com.lion.common.base.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import java.lang.reflect.ParameterizedType;

/**
 * BaseService
 * 通用 service 层，主要封装 Jpa 分页方法
 *
 * @author Yanzheng
 * @date 2019/11/20
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public abstract class BaseService<T, ID> {

    @Autowired
    private BaseRepository<T, ID> baseRepository;

    protected Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    public Page<T> findByPage(int pageNum, int pageSize) {
        return findByPage(null, pageNum, pageSize, null);
    }

    public Page<T> findByPage(int pageNum, int pageSize, String orderBy) {
        return findByPage(null, pageNum, pageSize, orderBy);
    }

    public Page<T> findByPage(Example example, int pageNum, int pageSize) {
        return findByPage(example, pageNum, pageSize, null);
    }

    public Page<T> findByPage(Example example, int pageNum, int pageSize, String orderBy) {

        Pageable pageable;

        if (null == orderBy) {
            pageable = PageRequest.of(pageNum - 1, pageSize);
        } else {
            Sort sort = null;
            String[] splitOrderBy = orderBy.trim().split(",");

            for (String rule : splitOrderBy) {
                String[] splitRule = rule.trim().split(" ");

                if (null == sort) {
                    sort = new Sort(splitRule[1].trim().equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC, splitRule[0]);
                } else {
                    sort = sort.and(new Sort(splitRule[1].trim().equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC, splitRule[0]));
                }
            }

            pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        }

        Page<T> page;

        if (null == example) {
            page = baseRepository.findAll(pageable);
        } else {
            page = baseRepository.findAll(example, pageable);
        }

        return page;
    }

}
