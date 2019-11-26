package com.lion.demo.consumer.temp.service;

import com.lion.common.base.service.jpa.BaseService;
import com.lion.demo.consumer.temp.entity.TempJpa;
import com.lion.demo.consumer.temp.repository.TempJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TempJpaService
 * TODO
 *
 * @author Yanzheng
 * @date 2019/11/20
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Service
public class TempJpaService extends BaseService<TempJpa, String> {

    @Autowired
    private TempJpaRepository tempJpaRepository;

    public void insert(TempJpa tempJpa) {
        tempJpaRepository.save(tempJpa);
    }

    public List<TempJpa> customSql(Boolean status) {
        return tempJpaRepository.selectByCustomSql(status);
    }

}
