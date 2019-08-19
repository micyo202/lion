package com.lion.bigdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * BigDataApplication
 * 大数据模块
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/01/06
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class BigDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(BigDataApplication.class, args);
    }

}