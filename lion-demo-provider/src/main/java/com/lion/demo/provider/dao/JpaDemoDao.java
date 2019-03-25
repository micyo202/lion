package com.lion.demo.provider.dao;

import com.lion.demo.provider.model.JpaDemo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * JpaDemoDao
 * Jpa方式CRUD操作接口
 *
 * @author Yanzheng 严正
 * @date 2019/01/08
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public interface JpaDemoDao extends JpaRepository<JpaDemo, String> {

    //占位符风格
    @Query(value = "SELECT * FROM demo WHERE valid = ?1", nativeQuery = true)
    List<JpaDemo> findBySql(boolean valid);

}
