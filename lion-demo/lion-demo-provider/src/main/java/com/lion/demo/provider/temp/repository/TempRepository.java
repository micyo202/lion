package com.lion.demo.provider.temp.repository;

import com.lion.demo.provider.temp.entity.TempJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TempRepository
 * TODO
 *
 * @author Yanzheng
 * @date 2019/04/15
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Transactional  // 在做update、delete时必须声明事物，否则会抛异常
public interface TempRepository extends JpaRepository<TempJpa, String> {

    // 占位符风格
    @Query(value = "SELECT * FROM temp_jpa WHERE status = ?1", nativeQuery = true)
    List<TempJpa> findBySql(Boolean status);

    // 参数风格
    //@Query(value = "SELECT * FROM temp_jpa WHERE status = :status", nativeQuery = true)
    //List<TempJpa> findBySql(@Param("status") Boolean status);

}
