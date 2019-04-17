package com.lion.demo.consumer.controller;

import com.lion.demo.consumer.client.ProviderDemoClientFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ConsumerDemoController
 * TODO
 *
 * @author Yanzheng 严正
 * @date 2019/01/05
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@RestController
public class ConsumerDemoController {

    @Value("${server.port}")
    String port;

    @Autowired
    ProviderDemoClientFeign providerDemoClientFeign;

    @RequestMapping("/hello")
    public String hello(@RequestParam(defaultValue = "lion") String name) {
        return providerDemoClientFeign.sayHiFromProvider(name);
    }

}
