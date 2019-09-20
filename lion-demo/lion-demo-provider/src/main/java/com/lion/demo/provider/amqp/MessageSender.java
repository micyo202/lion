package com.lion.demo.provider.amqp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * MessageSender
 * RabbitMQ 消息生产者（发送者）
 *
 * @author Yanzheng
 * @date 2019/09/19
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Component
@Slf4j
public class MessageSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sender(Object message) {
        log.info("RabbitMQ消息发送：{}", message);
        amqpTemplate.convertAndSend("lion", message);
    }

}
