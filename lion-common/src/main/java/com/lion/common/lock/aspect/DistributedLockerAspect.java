package com.lion.common.lock.aspect;

import com.lion.common.lock.annotation.Locker;
import com.lion.common.lock.locker.DistributedLocker;
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
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/05/08
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Aspect
@Component
public class DistributedLockerAspect {

    @Autowired
    private DistributedLocker distributedLocker;

    @Pointcut("@annotation(com.lion.common.lock.annotation.Locker)")
    public void lockerPointCut() {
    }

    /**
     * 环绕通知
     *
     * @param proceedingJoinPoint
     * @return
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

        Object result = null;
        if (isLocked) {
            try {
                result = proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                result = throwable.getMessage();
                throwable.printStackTrace();
            } finally {
                distributedLocker.unlock(lockKey);  // 释放锁
            }
        }

        return result;
    }
}
