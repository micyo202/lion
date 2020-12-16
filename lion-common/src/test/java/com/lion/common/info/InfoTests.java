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
package com.lion.common.info;

import org.junit.jupiter.api.Test;

/**
 * InfoTests
 * TODO
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/4/3
 */
class InfoTests {

    @Test
    void testUserInfo() {
        UserInfo userInfo = new UserInfo();

        System.out.println(userInfo.getName());
        System.out.println(userInfo.getHomeDir());
        System.out.println(userInfo.getCurrentDir());
        System.out.println(userInfo.getTempDir());
        System.out.println(userInfo.getLanguage());
        System.out.println(userInfo.getCountry());

        System.out.println(userInfo);
    }

    @Test
    void testOsInfo() {
        OsInfo osInfo = new OsInfo();

        System.out.println(osInfo.getArch());
        System.out.println(osInfo.getName());
        System.out.println(osInfo.getVersion());
        System.out.println(osInfo.getFileSeparator());
        System.out.println(osInfo.getLineSeparator());
        System.out.println(osInfo.getPathSeparator());

        System.out.println(osInfo);
    }

    @Test
    void testJavaInfo() {
        JavaInfo javaInfo = new JavaInfo();

        System.out.println(javaInfo.getVersion());
        System.out.println(javaInfo.getVendor());
        System.out.println(javaInfo.getVendorURL());

        System.out.println(javaInfo);
    }

    @Test
    void testJvmInfo() {
        JvmInfo jvmInfo = new JvmInfo();

        System.out.println(jvmInfo.getName());
        System.out.println(jvmInfo.getVendor());
        System.out.println(jvmInfo.getVersion());
        System.out.println(jvmInfo.getInfo());

        System.out.println(jvmInfo);
    }

    @Test
    void testRuntimeInfo() {
        RuntimeInfo runtimeInfo = new RuntimeInfo();

        System.out.println(runtimeInfo.getRuntime());
        System.out.println(runtimeInfo.getMaxMemory());
        System.out.println(runtimeInfo.getTotalMemory());
        System.out.println(runtimeInfo.getFreeMemory());
        System.out.println(runtimeInfo.getUsableMemory());

        System.out.println(runtimeInfo);
    }

    @Test
    void testSystemInfo() {
        SystemInfo systemInfo = new SystemInfo();
        System.out.println(systemInfo);
    }

}
