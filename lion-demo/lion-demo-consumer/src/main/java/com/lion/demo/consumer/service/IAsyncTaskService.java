package com.lion.demo.consumer.service;

/**
 * IAsyncTaskService
 * 异步线程任务接口
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2020/3/31
 * Copyright 2020 Yanzheng. All rights reserved.
 */
public interface IAsyncTaskService {

    /**
     * 异步执行任务
     */
    void asyncJob(String name);
}
