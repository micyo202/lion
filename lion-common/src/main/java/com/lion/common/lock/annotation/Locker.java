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
package com.lion.common.lock.annotation;

import java.lang.annotation.*;

/**
 * Locker
 * 分布式锁注解类
 *
 * @author Yanzheng https://github.com/micyo202
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