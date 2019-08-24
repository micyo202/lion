package com.lion.demo.ribbon.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * RibbonDemoController
 * TODO
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/01/03
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@RestController
@RefreshScope
public class RibbonDemoController {

    @Value("${foo}")
    private String foo;

    @RequestMapping("/foo")
    public String foo() {
        return foo;
    }

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/hi")
    @SentinelResource(value = "hi", fallback = "hiFallback")
    public String hi(String name) {
        return restTemplate.getForObject("http://lion-demo-provider/sayHi", String.class);
    }

    public String hiFallback(String name) {
        return "Hi '" + name + "', fallback sentinel!";
    }

}
