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
package com.lion.common.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * ReflectionUtil
 * TODO
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2021/8/2
 */
class ReflectionUtilTests {

    @Test
    void test() {

        ReflectionObj reflectionTest = new ReflectionObj();
        Object[] args = new Object[]{"张三", true, 29};
        Class<?>[] parameterTypes = new Class<?>[]{String.class, Boolean.class, Integer.class};

        Assertions.assertNotNull(reflectionTest);

        System.out.println(ReflectionUtil.getMethod(reflectionTest, "method", parameterTypes));
        System.out.println(ReflectionUtil.getDeclaredMethod(reflectionTest, "method", parameterTypes));
        System.out.println(ReflectionUtil.invokeMethod(reflectionTest, "method", args));
        System.out.println(ReflectionUtil.invokeGetterMethod(reflectionTest, "name"));
        ReflectionUtil.invokeSetterMethod(reflectionTest, "name", "李四");
        System.out.println(ReflectionUtil.invokeGetterMethod(reflectionTest, "name"));
        System.out.println(ReflectionUtil.getField(reflectionTest, "name"));
        ReflectionUtil.setFieldValue(reflectionTest, "age", 20);
        System.out.println(ReflectionUtil.getFieldValue(reflectionTest, "age"));
        System.out.println(ReflectionUtil.getFields(reflectionTest));
        System.out.println(ReflectionUtil.getClassGenricType(reflectionTest.getClass(), 1));
        System.out.println(ReflectionUtil.newInstance(reflectionTest.getClass()));
    }

}
