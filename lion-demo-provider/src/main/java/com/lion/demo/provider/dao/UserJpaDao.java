package com.lion.demo.provider.dao;

import com.lion.demo.provider.model.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * UserJpaDao
 * Jpa方式CRUD操作接口
 *
 * @author Yanzheng
 * @date 2019/03/29
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public interface UserJpaDao extends JpaRepository<UserJpa, String> {

    //占位符风格
    @Query(value = "SELECT * FROM user_jpa WHERE status = ?1", nativeQuery = true)
    List<UserJpa> findBySql(boolean status);

}
