package com.lion.demo.consumer;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * ConsumerDemoApplication
 * 服务消费者demo
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/01/05
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
//@EnableTransactionManagement
//@MapperScan("com.lion.**.mapper")
@MapperScan(basePackages = {"com.lion.common.mapper", "com.lion.demo.consumer.**.mapper"})
//@ComponentScan("com.lion")
@ComponentScan(basePackages = {"com.lion.common", "com.lion.demo.consumer"})
public class ConsumerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerDemoApplication.class, args);
    }

    @Bean
    @LoadBalanced
    @SentinelRestTemplate
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
