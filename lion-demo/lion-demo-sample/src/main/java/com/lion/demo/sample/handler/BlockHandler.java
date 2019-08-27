package com.lion.demo.sample.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * BlockHandler
 * sentinel流量控制自定义方法
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/08/19
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public class BlockHandler {

    public static String sentinelBlockHandler(BlockException e) {
        e.printStackTrace();
        return "限流控制（Sentinel is blocked...）";
    }

}
