package com.lion.id.repository;

import com.lion.common.base.repository.BaseRepository;
import com.lion.id.entity.SysId;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * IdRepository
 * 自增Id生成服务数据库操作类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/04/28
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Transactional
public interface IdRepository extends BaseRepository<SysId, Integer> {

    /**
     * 占位符风格
     */
    @Query(value = "select * from sys_id where biz_tag = ?1", nativeQuery = true)
    SysId getSysId(String bizTag);

    @Modifying
    @Query(value = "update sys_id set max_id=:maxId, step=:step where biz_tag=:bizTag", nativeQuery = true)
    int updateSysId(@Param("maxId") int maxId, @Param("step") int step, @Param("bizTag") String bizTag);

}