package com.lion.demo.provider.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProviderDemoController
 * TODO
 *
 * @author Yanzheng 严正
 * @date 2019/01/05
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Api("基本示例代码类说明文档")
@RestController
public class ProviderDemoController {

    @Value("${server.port}")
    String port;

    @ApiOperation("hi基本示例接口，返回hi文本内容")
    @ApiParam(name = "name", value = "名字", defaultValue = "lion")
    @RequestMapping(value = "/sayHi", method = RequestMethod.GET)
    public String sayHi(String name) {
        return "Hi: '" + name + "', i am from port: " + port;
    }

}