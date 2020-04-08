package com.lion.common.amqp;

import com.lion.common.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Repository;

/**
 * MessageSender
 * 消息发送器
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2020/3/31
 * Copyright 2020 Yanzheng. All rights reserved.
 */
@Repository
public class AmqpSender {

    @Autowired
    private AmqpChannel amqpChannel;

    /**
     * 发送消息
     */
    public void send(Result result) {
        amqpChannel.lionOutput().send(MessageBuilder.withPayload(result).build());
    }

}
