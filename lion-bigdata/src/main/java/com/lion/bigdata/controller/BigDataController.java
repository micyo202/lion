package com.lion.bigdata.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * BigDataController
 * 示例控制器
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/01/06
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@RestController
public class BigDataController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/hi")
    public String hi() {
        return "Hi, I'm BigData from port: " + port;
    }

}
