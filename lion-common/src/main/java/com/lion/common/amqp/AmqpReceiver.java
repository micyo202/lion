package com.lion.common.amqp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * MessageReceiver
 * 消息接收器
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2020/3/31
 * Copyright 2020 Yanzheng. All rights reserved.
 */
@EnableBinding(AmqpChannel.class)
@Slf4j
public class AmqpReceiver {

    /**
     * 接收消息
     */
    @StreamListener(AmqpChannel.LION_INPUT)
    public void receive(Object payload) {
        log.info("AMQP -> 接收到消息：" + payload);
    }

}
