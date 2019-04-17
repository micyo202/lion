package com.lion.upms.repository;

import com.lion.upms.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository
 * TODO
 *
 * @author Yanzheng
 * @date 2019/04/10
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public interface UserRepository extends JpaRepository<SysUser, Integer> {
}
