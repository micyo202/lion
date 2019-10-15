package com.lion.id.service;

import com.lion.id.entity.SysId;

import java.util.concurrent.Future;

/**
 * IdService
 * 自增Id生成服务接口（异步线程）
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/04/30
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public interface IdService {

    /**
     * 同步获取SysId（用于初始化）
     */
    SysId getSynSysId(String bizTag);

    /**
     * 异步获取SysId（用于双buffer）
     */
    Future<SysId> getAsynSysId(String bizTag);

}
