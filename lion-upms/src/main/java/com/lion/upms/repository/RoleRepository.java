package com.lion.upms.repository;

import com.lion.upms.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * RoleRepository
 * TODO
 *
 * @author Yanzheng
 * @date 2019/04/10
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public interface RoleRepository extends JpaRepository<SysRole, Integer> {

    //占位符风格
    @Query(value = "select r.* from sys_role r, sys_user_role ur where r.id=ur.role_id and ur.user_id=?1", nativeQuery = true)
    List<SysRole> getRoleByUserId(Integer userId);

}
