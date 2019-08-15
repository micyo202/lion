package com.lion.blockchain.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * BlockChainController
 * TODO
 *
 * @author Yanzheng
 * @date 2019/04/01
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@RestController
public class BlockChainController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/hi")
    public String hi() {
        return "Hi, I'm BlockChain from port: " + port;
    }

}
