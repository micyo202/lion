package com.lion.demo.consumer.client;

import com.lion.demo.consumer.client.fallback.ProviderDemoClientFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ProviderDemoClientFeign
 * 服务熔断处理
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/01/05
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@FeignClient(value = "lion-demo-provider", fallback = ProviderDemoClientFeignFallback.class)
public interface ProviderDemoClientFeign {

    @RequestMapping(value = "/sayHi")
    String sayHiFromProvider(@RequestParam(value = "name") String name);

}
