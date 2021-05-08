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
package com.lion.common.executor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * MonitorThreadPoolTaskExecutor
 * 线程池监控
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2021/5/1
 */
@Slf4j
public class MonitorThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

    @Override
    public void execute(Runnable task) {
        showThreadPoolInfo("execute(Runnable task)");
        super.execute(task);
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
        showThreadPoolInfo("execute(Runnable task, long startTimeout)");
        super.execute(task, startTimeout);
    }

    @Override
    public Future<?> submit(Runnable task) {
        showThreadPoolInfo("submit(Runnable task)");
        return super.submit(task);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        showThreadPoolInfo("submit(Callable<T> task)");
        return super.submit(task);
    }

    @Override
    public ListenableFuture<?> submitListenable(Runnable task) {
        showThreadPoolInfo("submitListenable(Runnable task)");
        return super.submitListenable(task);
    }

    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        showThreadPoolInfo("submitListenable(Callable<T> task)");
        return super.submitListenable(task);
    }

    /**
     * 每次执行任务时输出当前线程池状态
     */
    private void showThreadPoolInfo(String method) {
        ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();
        if (null == threadPoolExecutor) {
            return;
        }
        log.info("threadNamePrefix[{}], method[{}], taskCount[{}], completedTaskCount[{}], activeCount[{}], queueSize[{}]",
                this.getThreadNamePrefix(),
                method,
                threadPoolExecutor.getTaskCount(),
                threadPoolExecutor.getCompletedTaskCount(),
                threadPoolExecutor.getActiveCount(),
                threadPoolExecutor.getQueue().size());
    }

}
