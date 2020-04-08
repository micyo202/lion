package com.lion.auth.controller;

import com.lion.common.base.controller.BaseController;
import com.lion.common.constant.SecurityConstant;
import com.lion.common.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
@AllArgsConstructor
public class AuthController extends BaseController {

    @ApiOperation(value = "初始化", response = Result.class)
    @GetMapping("/init")
    public Result init() {
        return Result.success(applicationName + " -> port: " + port + ", version: " + version);
    }

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @ApiOperation(value = "注销凭证信息", response = Result.class)
    @DeleteMapping(value = "/revoke")
    public Result revoke() {
        String accessToken = getRequest().getHeader(SecurityConstant.ACCESS_TOKEN);
        String formatToken = accessToken.replace(SecurityConstant.BEARER_PREFIX, "");
        if (consumerTokenServices.revokeToken(formatToken)) {
            return Result.success();
        } else {
            return Result.failure("注销凭证信息失败");
        }
    }

}
