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

import java.io.Serializable;

/**
 * SystemInfo
 * 系统信息，包含user、os、jvm等
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/11/26
 */
public class SystemInfo implements Serializable {

    public static String info() {

        UserInfo userInfo = new UserInfo();
        OsInfo osInfo = new OsInfo();
        JavaInfo javaInfo = new JavaInfo();
        JvmInfo jvmInfo = new JvmInfo();
        RuntimeInfo runtimeInfo = new RuntimeInfo();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[UserInfo]\n").append(userInfo.toString()).append("\n")
                .append("\n[OsInfo]\n").append(osInfo.toString()).append("\n")
                .append("\n[JavaInfo]\n").append(javaInfo.toString()).append("\n")
                .append("\n[JvmInfo]\n").append(jvmInfo.toString()).append("\n")
                .append("\n[RuntimeInfo]\n").append(runtimeInfo.toString()).append("\n");

        return stringBuilder.toString();
    }

}