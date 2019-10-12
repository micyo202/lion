package com.lion.common.config;

import com.lion.common.constant.AmqpArgs;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DirectRabbitConfig
 * TODO
 *
 * @author Yanzheng
 * @date 2019/10/12
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Configuration
public class DirectRabbitMQConfig {

    /**
     * 队列
     */
    @Bean
    public Queue directQueue() {
        return new Queue(AmqpArgs.DIRECT_QUEUE, true);
    }

    /**
     * Direct交换机
     */
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(AmqpArgs.DIRECT_EXCHANGE);
    }

    /**
     * 绑定
     * 将队列和交换机绑定, 并设置用于匹配键
     */
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with(AmqpArgs.DIRECT_ROUTING);
    }
}
