package com.lion.blockchain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * BlockChainApplication
 * 区块链模块
 *
 * @author Yanzheng
 * @date 2019/04/01
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@SpringBootApplication
@EnableEurekaClient
public class BlockChainApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlockChainApplication.class, args);
    }

}
