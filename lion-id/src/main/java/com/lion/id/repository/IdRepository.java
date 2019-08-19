package com.lion.id.repository;

import com.lion.id.entity.SysId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * IdRepository
 * TODO
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/04/28
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Transactional
public interface IdRepository extends JpaRepository<SysId, Integer> {

    // 占位符风格
    @Query(value = "select * from sys_id where biz_tag = ?1", nativeQuery = true)
    SysId getSysId(String bizTag);

    @Modifying
    @Query(value = "update sys_id set max_id=:maxId, step=:step where biz_tag=:bizTag", nativeQuery = true)
    int updateSysId(@Param("maxId") int maxId, @Param("step") int step, @Param("bizTag") String bizTag);

}