package com.lion.common.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * BaseRepository
 * Jpa通用Repository类
 *
 * @author Yanzheng
 * @date 2019/11/20
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {
}