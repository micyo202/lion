package com.lion.demo.consumer.amqp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * MessageReceiver
 * RabbitMQ 消息消费者（监听者）
 *
 * @author Yanzheng
 * @date 2019/09/19
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Component
@Slf4j
public class MessageReceiver {

    @RabbitListener(queues = "lion")
    public void receiver(Message message) {
        log.info("Receiver: {}", message.getPayload());
    }

}
