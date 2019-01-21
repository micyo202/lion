package com.lion.demo.consumer.client;

import com.lion.demo.consumer.client.fallback.ProviderDemoClientFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ProviderDemoClientFeign
 * TODO
 *
 * @author Yanzheng 严正
 * @date 2019/01/05
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@FeignClient(value = "lion-demo-provider", fallback = ProviderDemoClientFeignHystrix.class)
public interface ProviderDemoClientFeign {

    @RequestMapping(value = "/hi")
    String sayHiFromProvider(@RequestParam(value = "name") String name);

}
