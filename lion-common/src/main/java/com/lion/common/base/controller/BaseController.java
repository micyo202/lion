package com.lion.common.base.controller;

import com.lion.common.amqp.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * BaseController
 * 通用控制器
 *
 * @author Yanzheng
 * @date 2019/11/19
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public abstract class BaseController {

    /**
     * 端口号
     */
    @Value("${server.port}")
    protected String port;

    /**
     * 应用名称（默认：lion-unknown）
     */
    @Value("${spring.application.name:lion-unknown}")
    protected String applicationName;

    /**
     * 版本，从gateway服务发起，用于灰度（如：http://localhost:8400/demo/consumer/gray?version=1.0）
     */
    @Value("${spring.cloud.nacos.discovery.metadata.version:1.0}")
    protected String version;

    /**
     * 认证服务地址（默认：http://localhost:8888/oauth/token）
     */
    @Value("${security.oauth2.client.access-token-uri:http://localhost:8888/oauth/token}")
    protected String authServer;

    /**
     * RabbitMQ 消息发送者
     */
    @Autowired
    protected MessageSender messageSender;

}
