package com.lion.demo.consumer.amqp;

import com.lion.common.constant.AmqpArgs;
import com.lion.common.entity.Result;
import com.lion.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * DirectReceiver
 * RabbitMQ 消息消费者（监听者）
 *
 * @author Yanzheng
 * @date 2019/10/12
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Component
@Slf4j
public class MessageReceiver {

    @RabbitListener(queues = AmqpArgs.DIRECT_QUEUE)
    public void receive(Result result) {
        log.info("消息消费者接收到消息:{}", JsonUtil.jsonObj2Str(result));
    }

}
