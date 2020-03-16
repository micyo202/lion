package com.lion.demo.provider.controller;

import com.lion.common.base.controller.BaseController;
import com.lion.common.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProviderDemoController
 * 服务提供者示例代码
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/09/25
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Api("服务提供者示例代码")
@RestController
@Slf4j
public class ProviderDemoController extends BaseController {

    @ApiOperation("初始化接口")
    @GetMapping("/init")
    public Result init() {
        return Result.success("Provider -> version: " + version + ", port：" + port);
    }

    @ApiOperation("基本示例接口，返回Hi文本内容")
    @ApiParam(name = "name", value = "名称", defaultValue = "lion")
    @GetMapping("/hi")
    public Result hi(@RequestParam(defaultValue = "lion") String name) {
        log.info("hi 服务提供者 Provider");
        return Result.success("Hi \"" + name + "\", I'm Provider, From port: " + port);
    }

}