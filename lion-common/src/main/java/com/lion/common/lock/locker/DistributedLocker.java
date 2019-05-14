package com.lion.common.lock.locker;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * Locker
 * 获取锁管理类
 *
 * @author Yanzheng
 * @date 2019/05/08
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public interface DistributedLocker {

    RLock lock(String lockKey);

    RLock lock(String lockKey, long leaseTime);

    RLock lock(String lockKey, TimeUnit unit, long leaseTime);

    boolean tryLock(String lockKey);

    boolean tryLock(String lockKey, long waitTime, long leaseTime);

    boolean tryLock(String lockKey, TimeUnit unit, long waitTime, long leaseTime);

    void unlock(String lockKey);

    void unlock(RLock lock);

}
