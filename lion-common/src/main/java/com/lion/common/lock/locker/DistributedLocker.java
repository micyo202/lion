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

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * DistributedLocker
 * 分布式锁接口方法
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/05/08
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