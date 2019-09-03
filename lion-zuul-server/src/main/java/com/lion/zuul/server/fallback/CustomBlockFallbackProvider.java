package com.lion.zuul.server.fallback;

import com.alibaba.csp.sentinel.adapter.gateway.zuul.fallback.BlockResponse;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.fallback.ZuulBlockFallbackProvider;
import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * CustomBlockFallbackProvider
 * 自定义 Sentinel 错误回滚提示信息
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/08/16
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public class CustomBlockFallbackProvider implements ZuulBlockFallbackProvider {

    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public BlockResponse fallbackResponse(String route, Throwable cause) {
        if (cause instanceof BlockException) {
            return new BlockResponse(429, "前方拥挤，请稍后再试！（流量控制）", route);
        } else {
            return new BlockResponse(500, "调用服务失败！", route);
        }
    }

}
