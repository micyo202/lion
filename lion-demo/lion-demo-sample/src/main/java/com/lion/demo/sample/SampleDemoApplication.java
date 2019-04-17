package com.lion.demo.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * SampleApplication
 * TODO
 *
 * @author Yanzheng
 * @date 2019/03/29
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@SpringBootApplication
@EnableEurekaClient
public class SampleDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleDemoApplication.class, args);
    }

}