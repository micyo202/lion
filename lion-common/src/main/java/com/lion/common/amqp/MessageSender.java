package com.lion.common.amqp;

import com.lion.common.constant.AmqpArgs;
import com.lion.common.entity.Result;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * MessageSender
 * RabbitMQ 消息生产者（发送者）
 *
 * @author Yanzheng
 * @date 2019/10/12
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Component
public class MessageSender {

    @Autowired
    AmqpTemplate amqpTemplate;

    public void send(Object object) {
        amqpTemplate.convertAndSend(AmqpArgs.DIRECT_EXCHANGE, AmqpArgs.DIRECT_ROUTING, Result.success(object));
    }

}
