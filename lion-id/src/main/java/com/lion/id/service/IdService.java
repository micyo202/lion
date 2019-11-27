package com.lion.id.service;

import com.lion.common.base.service.jpa.BaseService;
import com.lion.id.entity.SysId;
import com.lion.id.repository.IdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * IdService
 * 自增Id生成服务实现类（异步线程）
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/11/21
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Service
public class IdService extends BaseService<SysId, Integer> {

    @Autowired
    private IdRepository idRepository;

    /**
     * 同步获取SysId（用于初始化）
     */
    public SysId getSynSysId(String code) {
        return getSysId(code);
    }

    /**
     * 异步获取SysId（用于双buffer）
     */
    @Async("asynExecutor")
    public Future<SysId> getAsynSysId(String code) {
        SysId sysId = getSysId(code);
        return new AsyncResult<>(sysId);
    }

    /**
     * 获取SysId
     */
    private SysId getSysId(String code) {
        SysId sysId = idRepository.getSysId(code);

        int increaseRange = sysId.getStep() - sysId.getMaxId();
        int increaseCount = sysId.getStep() / (sysId.getStep() - sysId.getMaxId());

        int maxId = increaseRange * increaseCount;
        int step = increaseRange * (increaseCount + 1);

        idRepository.updateSysId(maxId, step, sysId.getId());

        return sysId;
    }

}