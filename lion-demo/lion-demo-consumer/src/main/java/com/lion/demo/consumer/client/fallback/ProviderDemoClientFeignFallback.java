package com.lion.demo.consumer.client.fallback;

import com.lion.demo.consumer.client.ProviderDemoClientFeign;
import org.springframework.stereotype.Component;

/**
 * ProviderDemoClientFeignFallback
 * 服务熔断处理
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/01/05
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Component
public class ProviderDemoClientFeignFallback implements ProviderDemoClientFeign {
    @Override
    public String sayHiFromProvider(String name) {
        return "Sorry '" + name + "', fallback sentinel!";
    }
}