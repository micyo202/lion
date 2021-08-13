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

/**
 * DemoTest
 * TODO
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2021/8/2
 */
public class ReflectionObj {

    private String name;

    public int age = 0;

    static {
        System.out.println("static 静态方法");
    }

    public ReflectionObj() {
        System.out.println("Constructor 无参构造方法");
    }

    public ReflectionObj(String name) {
        System.out.println("Constructor 有参构造方法，参数 name = " + name);
        this.name = name;
    }

    public static void staticMethod() {
        System.out.println("我是无参静态方法");
    }

    public void method() {
        System.out.println("我是无参动态方法");
    }

    public void method(String name, boolean sex, int age) {
        System.out.println("参数方法 name = " + name + ", sex = " + sex + ", age = " + age);
    }

    public String getName() {
        System.out.println("Getter方法");
        return name;
    }

    public void setName(String name) {
        System.out.println("Setter方法，name = " + name);
        this.name = name;
    }


}
