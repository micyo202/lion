package com.lion.demo.consumer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQConfig
 * RabbitMQ 消息消费者（监听者）队列配置
 *
 * @author Yanzheng
 * @date 2019/09/19
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue lionQueue() {
        return new Queue("lion");
    }

}
