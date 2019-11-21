package com.lion.demo.provider.temp.repository;

import com.lion.common.base.repository.BaseRepository;
import com.lion.demo.provider.temp.entity.TempJpa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TempJpaRepository
 * TempJpa 的 Repository 类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/04/15
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Transactional  // 在做update、delete时必须声明事物，否则会抛异常
public interface TempJpaRepository extends BaseRepository<TempJpa, String> {

    // 占位符风格
    @Query(value = "select * from temp_jpa where status = ?1", nativeQuery = true)
    List<TempJpa> selectByCustomSql(Boolean status);

    // SPEL表达式风格
    //@Query(value = "select * from temp_jpa where status = :status", nativeQuery = true)
    //List<TempJpa> selectByCustomSql(@Param("status") Boolean status);

}
