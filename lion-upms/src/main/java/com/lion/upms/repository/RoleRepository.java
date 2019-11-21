package com.lion.upms.repository;

import com.lion.common.base.repository.BaseRepository;
import com.lion.upms.entity.SysRole;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * RoleRepository
 * 角色 Jpa 类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/04/10
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public interface RoleRepository extends BaseRepository<SysRole, Integer> {

    /**
     * 占位符风格
     */
    @Query(value = "select r.* from sys_role r, sys_user_role ur where r.id=ur.role_id and ur.user_id=?1", nativeQuery = true)
    List<SysRole> getRoleByUserId(Integer userId);

}
