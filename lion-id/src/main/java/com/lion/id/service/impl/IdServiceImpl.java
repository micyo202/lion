package com.lion.id.service.impl;

import com.lion.id.entity.SysId;
import com.lion.id.repository.IdRepository;
import com.lion.id.service.IdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * IdService
 * TODO
 *
 * @author Yanzheng
 * @date 2019/04/29
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Service
public class IdServiceImpl implements IdService {

    @Autowired
    private IdRepository idRepository;

    @Override
    public SysId getSynSysId(String bizTag) {
        return getSysId(bizTag);
    }

    @Async("asynExecutor")
    @Override
    public Future<SysId> getAsynSysId(String bizTag) {
        SysId sysId = getSysId(bizTag);
        return new AsyncResult<>(sysId);
    }

    // 获取SysId
    private SysId getSysId(String bizTag) {
        SysId sysId = idRepository.getSysId(bizTag);

        int increaseRange = sysId.getStep() - sysId.getMaxId();
        int increaseCount = sysId.getStep() / (sysId.getStep() - sysId.getMaxId());

        int maxId = increaseRange * increaseCount;
        int step = increaseRange * (increaseCount + 1);

        idRepository.updateSysId(maxId, step, bizTag);

        return sysId;
    }

}
