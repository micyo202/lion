package com.lion.demo.gray;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * GrayDemoApplication
 * TODO
 *
 * @author Yanzheng
 * @date 2019/04/05
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@SpringBootApplication
@EnableEurekaClient
public class GrayDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrayDemoApplication.class, args);
    }

}
