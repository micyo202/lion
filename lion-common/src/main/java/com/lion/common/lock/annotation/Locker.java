package com.lion.common.lock.annotation;

import java.lang.annotation.*;

/**
 * Locker
 * 分布式锁注解类
 *
 * @author Yanzheng
 * @date 2019/05/08
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Locker {

    long waitTime() default 5000;   // 等待时间

    long leaseTime() default 10000; // 单笔业务锁持有时长，防止死锁

}