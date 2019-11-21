package com.lion.demo.consumer.client.fallback;

import com.lion.common.entity.Result;
import com.lion.demo.consumer.client.ProviderDemoClient;
import org.springframework.stereotype.Service;

/**
 * ProviderDemoClientFeignFallback
 * 服务调用失败处理类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/01/05
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Service
public class ProviderDemoClientFallback implements ProviderDemoClient {

    @Override
    public Result hiFromProvider(String name) {
        return Result.failure("Invoke Method \"hiFromProvider(String name)\" Fallback");
    }

    @Override
    public Result saveTransactionalFromProvider(int num) {
        return Result.failure("Invoke Method \"saveTransactionalFromProvider(int num)\" Fallback");
    }

}