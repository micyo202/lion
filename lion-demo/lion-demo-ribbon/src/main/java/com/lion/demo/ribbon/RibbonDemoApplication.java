package com.lion.demo.ribbon;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * RibbonDemoApplication
 * RestTemplate + Ribbon + Sentinel 示例代码
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/01/03
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class RibbonDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonDemoApplication.class, args);
    }

    @Bean
    @LoadBalanced
    @SentinelRestTemplate
    RestTemplate restTemplate() {
        return new RestTemplate();
    }


}