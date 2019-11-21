package com.lion.auth.controller;

import com.lion.common.base.controller.BaseController;
import com.lion.common.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * UserController
 * 用户认证模块
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/04/08
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Api("用户认证")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @ApiOperation(value = "获取用户凭证信息", response = Result.class)
    @GetMapping(value = "/principal")
    public Result principal(Principal principal) {
        //获取用户凭证信息
        return Result.success(principal);
    }

    @ApiOperation(value = "移除用户凭证信息", response = Result.class)
    @DeleteMapping(value = "/revoke/{access_token}")
    public Result revoke(@PathVariable String access_token) {
        if (consumerTokenServices.revokeToken(access_token)) {
            return Result.success();
        } else {
            return Result.failure("移除用户凭证信息失败");
        }
    }

}
