/**
 *   Copyright 2019 Yanzheng (https://github.com/micyo202). All rights reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.lion.common.lock.locker;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * RedissonDistributedLocker
 * 分布式锁方法实现
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/05/08
 */
@Component
@Slf4j
public class RedissonDistributedLocker implements DistributedLocker {

    /**
     * 锁key值前缀
     */
    private final static String KEY_PREFIX = "LOCK:";

    /**
     * 默认秒数单位，毫秒
     */
    private final static TimeUnit TIME_UNIT = TimeUnit.MILLISECONDS;

    /**
     * RedissonClient已经由配置类生成，这里自动装配即可
     */
    @Autowired
    private RedissonClient redissonClient;

    /**
     * 强制加锁
     *
     * 拿不到lock就不罢休，不然线程就一直block
     *
     * @param lockKey 锁的key值
     * @return 锁信息
     */
    @Override
    public RLock lock(String lockKey) {
        RLock lock = redissonClient.getLock(KEY_PREFIX + lockKey);
        lock.lock();
        return lock;
    }

    /**
     * 强制加锁
     *
     * @param lockKey   锁的key值
     * @param leaseTime 加锁时间（默认单位毫秒）
     * @return 锁信息
     */
    @Override
    public RLock lock(String lockKey, long leaseTime) {
        RLock lock = redissonClient.getLock(KEY_PREFIX + lockKey);
        lock.lock(leaseTime, TIME_UNIT);
        return lock;
    }

    /**
     * 强制加锁
     *
     * @param lockKey   锁的key值
     * @param unit      时间单位
     * @param leaseTime 加锁时间
     * @return 锁信息
     */
    @Override
    public RLock lock(String lockKey, TimeUnit unit, long leaseTime) {
        RLock lock = redissonClient.getLock(KEY_PREFIX + lockKey);
        lock.lock(leaseTime, unit);
        return lock;
    }

    /**
     * 尝试加锁
     *
     * 马上返回，拿到lock就返回true，不然返回false
     * 带时间限制的tryLock()，拿不到lock，就等一段时间，超时返回false
     *
     * @param lockKey 锁的key值
     * @return 是否拿到
     */
    @Override
    public boolean tryLock(String lockKey) {
        RLock lock = redissonClient.getLock(KEY_PREFIX + lockKey);
        return lock.tryLock();
    }

    /**
     * 尝试加锁
     *
     * @param lockKey   锁的key值
     * @param waitTime  等待时间
     * @param leaseTime 加锁时间
     * @return 是否拿到标志
     */
    @Override
    public boolean tryLock(String lockKey, long waitTime, long leaseTime) {
        RLock lock = redissonClient.getLock(KEY_PREFIX + lockKey);
        try {
            return lock.tryLock(waitTime, leaseTime, TIME_UNIT);
        } catch (InterruptedException e) {
            return false;
        }
    }

    /**
     * 尝试加锁
     *
     * @param lockKey   锁的key值
     * @param unit      时间单位
     * @param waitTime  等待时间
     * @param leaseTime 加锁时间
     * @return 是否拿到标志
     */
    @Override
    public boolean tryLock(String lockKey, TimeUnit unit, long waitTime, long leaseTime) {
        RLock lock = redissonClient.getLock(KEY_PREFIX + lockKey);
        try {
            return lock.tryLock(waitTime, leaseTime, unit);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 解锁
     *
     * @param lockKey 锁的key值
     */
    @Override
    public void unlock(String lockKey) {
        RLock lock = redissonClient.getLock(KEY_PREFIX + lockKey);
        lock.unlock();
    }

    /**
     * 解锁
     *
     * @param lock 锁信息
     */
    @Override
    public void unlock(RLock lock) {
        lock.unlock();
    }
}
