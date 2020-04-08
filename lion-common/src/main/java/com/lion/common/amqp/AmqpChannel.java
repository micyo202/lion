package com.lion.common.amqp;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * MessageChannel
 * 消息通道
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2020/3/31
 * Copyright 2020 Yanzheng. All rights reserved.
 */
public interface AmqpChannel {

    String LION_INPUT = "lion-input";
    String LiON_OUTPUT = "lion-output";

    /**
     * 接收消息
     */
    @Input(LION_INPUT)
    SubscribableChannel lionInput();

    /**
     * 发送消息
     */
    @Output(LiON_OUTPUT)
    MessageChannel lionOutput();
}
