package com.lion.auth.client.fallback;

import com.lion.auth.client.UpmsClient;
import com.lion.common.entity.Result;
import org.springframework.stereotype.Service;

/**
 * UpmsClientFallback
 * Fegin服务调用失败处理类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/04/10
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Service
public class UpmsClientFallback implements UpmsClient {

    @Override
    public Result getUserByUsernameFromUpms(String username) {
        return Result.failure("Invoke Method \"getUserByUsernameFromUpms(String username)\" Fallback");
    }

    @Override
    public Result getRoleByUserIdFromUpms(Integer userId) {
        return Result.failure("Invoke Method \"getRoleByUserIdFromUpms(Integer userId)\" Fallback");
    }

    @Override
    public Result getMenuByRoleIdFromUpms(Integer roleId) {
        return Result.failure("Invoke Method \"getMenuByRoleIdFromUpms(Integer roleId)\" Fallback");
    }
}
