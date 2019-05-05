package com.lion.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * AsynExecutorConfig
 * 异步线程池
 *
 * @author Yanzheng
 * @date 2019/05/05
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Configuration
@EnableAsync    // 开启异步任务
public class AsynExecutorConfig {

    @Bean("asynExecutor")
    public Executor AsynExecutor() {
        /**
         * 核心线程数10：线程池创建时候初始化的线程数
         * 最大线程数20：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
         * 缓冲队列200：用来缓冲执行任务的队列
         * 允许线程的空闲时间60秒：当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
         * 线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
         * 线程池对拒绝任务的处理策略：这里采用了CallerRunsPolicy策略，当线程池没有处理能力的时候，该策略会直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务
         */
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(200);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("AsynTaskExecutor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

}