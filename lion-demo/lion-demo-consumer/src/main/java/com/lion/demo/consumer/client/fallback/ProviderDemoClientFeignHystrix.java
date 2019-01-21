package com.lion.demo.consumer.client.fallback;

import com.lion.demo.consumer.client.ProviderDemoClientFeign;
import org.springframework.stereotype.Component;

/**
 * ProviderDemoClientFeignHystrix
 * TODO
 *
 * @author Yanzheng 严正
 * @date 2019/01/05
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Component
public class ProviderDemoClientFeignHystrix implements ProviderDemoClientFeign {
    @Override
    public String sayHiFromProvider(String name) {
        return "Sorry '" + name + "', fallback hystrix!";
    }
}