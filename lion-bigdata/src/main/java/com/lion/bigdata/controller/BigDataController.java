package com.lion.bigdata.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * BigDataController
 * 示例控制器
 *
 * @author Yanzheng
 * @date 2019/01/06
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@RestController
public class BigDataController {

    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    public String hi() {
        return "Hi, I'm BigData from port: " + port;
    }

    @RequestMapping("/test")
    public Map test(@RequestParam(defaultValue = "default") String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", UUID.randomUUID().toString().replaceAll("-", ""));
        map.put("name", name);
        map.put("age", 27);
        map.put("sex", true);
        return map;
    }

}
