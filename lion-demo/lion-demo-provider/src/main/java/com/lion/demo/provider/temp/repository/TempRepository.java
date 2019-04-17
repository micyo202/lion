package com.lion.demo.provider.temp.repository;

import com.lion.demo.provider.temp.entity.TempJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * TempRepository
 * TODO
 *
 * @author Yanzheng
 * @date 2019/04/15
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public interface TempRepository extends JpaRepository<TempJpa, String> {

    // 占位符风格
    @Query(value = "SELECT * FROM temp_jpa WHERE status = ?1", nativeQuery = true)
    List<TempJpa> findBySql(Boolean status);

    // 参数风格
    //@Query(value = "SELECT * FROM temp_jpa WHERE status = :status", nativeQuery = true)
    //List<TempJpa> findBySql(@Param("status") Boolean status);

}
