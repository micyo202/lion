package com.lion.demo.gray.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * GrayController
 * TODO
 *
 * @author Yanzheng
 * @date 2019/04/05
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@RestController
public class GrayController {

    @Value("${server.port}")
    String port;

    @Value("${eureka.instance.metadata-map.launcher}")
    String ver;

    @RequestMapping("/hi")
    public String hi() {
        return "灰度版本：" + ver + " 端口：" + port;
    }

}
