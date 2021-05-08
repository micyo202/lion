/**
 *   Copyright 2019 Yanzheng (https://github.com/micyo202). All rights reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.lion.common.config;

import com.lion.common.executor.MonitorThreadPoolTaskExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ThreadPoolConfig
 * 线程池配置
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2021/5/1
 */
@ConditionalOnProperty(prefix = "thread-pool", name = "core-pool-size")
@Configuration
@EnableAsync    // 开启异步任务
@Slf4j
public class ThreadPoolConfig {

    /**
     * 核心线程数
     * 线程池创建时候初始化的线程数
     */
    @Value("${thread-pool.core-pool-size:10}")
    private int corePoolSize;

    /**
     * 最大线程数
     * 线程池最大的线程数，只有在缓冲队列满了之后，才会申请超过核心线程数的线程
     */
    @Value("${thread-pool.max-pool-size:100}")
    private int maxPoolSize;

    /**
     * 缓冲队列
     * 用来缓冲执行任务的队列
     */
    @Value("${thread-pool.queue-capacity:200}")
    private int queueCapacity;

    /**
     * 允许线程的空闲时间（秒）
     * 当超过了核心线程之外的线程，在空闲时间到达之后会被销毁
     */
    @Value("${thread-pool.keep-alive-seconds:60}")
    private int keepAliveSeconds;

    /**
     * 线程名前缀
     * 用于定位处理任务所在的线程
     */
    @Value("${thread-pool.thread-name-prefix:threadPool-}")
    private String threadNamePrefix;

    /**
     * 等待所有任务结束后再关闭线程池
     */
    @Value("${thread-pool.wait_tasks_complete_shutdown:true}")
    private boolean waitForTasksToCompleteOnShutdown;

    /**
     * 设置线程池中任务的等待时间，如果超过这个时间还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
     */
    @Value("${thread-pool.await_termination_seconds:60}")
    private int awaitTerminationSeconds;

    @Bean("executor")
    public Executor executor() {
        log.info("初始化加载 MonitorThreadPool 带监控的线程池");
        //ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        ThreadPoolTaskExecutor executor = new MonitorThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.setWaitForTasksToCompleteOnShutdown(waitForTasksToCompleteOnShutdown);
        executor.setAwaitTerminationSeconds(awaitTerminationSeconds);
        /**
         * setRejectedExecutionHandler线程池对拒绝任务的处理策略（目前只支持AbortPolicy、CallerRunsPolicy，默认为后者）
         *
         * AbortPolicy：直接抛出 java.util.concurrent.RejectedExecutionException 异常
         * CallerRunsPolicy：当线程池没有处理能力的时候，该策略会直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务
         * DiscardOldestPolicy：抛弃旧的任务、暂不支持；会导致被丢弃的任务无法再次被执行
         * DiscardPolicy：抛弃当前任务、暂不支持；会导致被丢弃的任务无法再次被执行
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        // 执行初始化
        executor.initialize();

        return executor;
    }

}
