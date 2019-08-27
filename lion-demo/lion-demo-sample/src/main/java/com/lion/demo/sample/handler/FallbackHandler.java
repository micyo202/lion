package com.lion.demo.sample.handler;

/**
 * FallbackHandler
 * sentinel服务熔断、降级方法
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/08/19
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public class FallbackHandler {

    public static String sentinelFallback() {
        return "服务熔断、降级（Sentinel is fallback...）";
    }

}
