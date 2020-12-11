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
package com.lion.common.lock.aspect;

import com.lion.common.lock.annotation.Locker;
import com.lion.common.lock.locker.DistributedLocker;
import com.lion.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * DistributedLockerAspect
 * 分布式锁切面方法
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/05/08
 */
@Aspect
@Component
@Slf4j
public class DistributedLockerAspect {

    @Autowired
    private DistributedLocker distributedLocker;

    /**
     * 切面表达式
     */
    @Pointcut("@annotation(com.lion.common.lock.annotation.Locker)")
    public void lockerPointCut() {}

    /**
     * 环绕通知
     */
    @Around("lockerPointCut()")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) {

        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        // 执行的类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        // 执行方法名
        String methodName = methodSignature.getName();
        // 传入参数
        //List<Object> parameters = Arrays.asList(joinPoint.getArgs());

        Locker locker = methodSignature.getMethod().getAnnotation(Locker.class);
        String lockKey = className + methodName;
        boolean isLocked = distributedLocker.tryLock(lockKey, locker.waitTime(), locker.leaseTime());

        Object result;
        if (isLocked) {
            try {
                result = proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                log.error(throwable.getMessage(), throwable);
                result = throwable.getMessage();
            } finally {
                // 释放锁
                distributedLocker.unlock(lockKey);
            }
        } else {
            String msg = "分布式锁获取失败/超时";
            log.error(msg);
            result = Result.failure(msg);
        }

        return result;
    }
}
