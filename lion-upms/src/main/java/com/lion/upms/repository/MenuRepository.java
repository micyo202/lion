package com.lion.upms.repository;

import com.lion.upms.entity.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * MenuRepository
 * TODO
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/04/10
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public interface MenuRepository extends JpaRepository<SysMenu, Integer> {

    /**
     * SPEL表达式风格
     */
    @Query(value = "select m.* from sys_menu m,sys_role_menu rm where m.id=rm.menu_id and rm.role_id=:roleId", nativeQuery = true)
    List<SysMenu> getMenuByRoleId(@Param("roleId") Integer roleId);

}
