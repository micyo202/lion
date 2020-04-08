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
    public Result initFromProvider() {
        return Result.failure("FeignClient -> 调用服务端提供者：'lion-demo-provider'请求'/init'失败");
    }

    @Override
    public Result hiFromProvider(String name) {
        return Result.failure("FeignClient -> 调用服务端提供者：'lion-demo-provider'请求'/hi'失败");
    }

    @Override
    public Result deductFromProvider(String productCode, int count) {
        return Result.failure("FeignClient -> 调用服务端提供者：'lion-demo-provider'请求'/temp/product/deduct'失败");
    }
}