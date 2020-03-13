package com.lion.auth.controller;

import com.lion.common.base.controller.BaseController;
import com.lion.common.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * AuthController
 * 权限认证
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2020/03/12
 * Copyright 2020 Yanzheng. All rights reserved.
 */
@Api("权限认证")
@RestController
public class AuthController extends BaseController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @ApiOperation(value = "获取凭证信息", response = Result.class)
    @GetMapping(value = "/principal")
    public Principal principal(Principal principal) {
        //获取用户凭证信息
        return principal;
    }

    @ApiOperation(value = "注销凭证信息", response = Result.class)
    @DeleteMapping(value = "/revoke")
    public Result revoke(String access_token) {
        if (consumerTokenServices.revokeToken(access_token)) {
            return Result.success();
        } else {
            return Result.failure("注销凭证信息失败");
        }
    }

}
