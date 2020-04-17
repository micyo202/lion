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
package com.lion.demo.consumer.service.impl;

import com.lion.common.exception.LionException;
import com.lion.demo.consumer.service.IAsyncTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * AsyncTaskServiceImpl
 * 异步线程任务实现类
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/3/31
 */
@Service
@Slf4j
public class AsyncTaskServiceImpl implements IAsyncTaskService {

    /**
     * 异步执行任务
     */
    @Async("asynExecutor")
    @Override
    public void asyncJob(String name) {
        log.info("Async -> 异步任务：'" + name + "'，开始执行...");

        for (int i = 0; i < 10; i++) {
            log.info("Async -> 异步任务：'" + name + "'，循环输出：" + i + "休息2秒");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new LionException("线程sleep异常" + e.getMessage());
            }
        }

        log.info("Async -> 异步任务：'" + name + "'，执行完毕");
    }

}
