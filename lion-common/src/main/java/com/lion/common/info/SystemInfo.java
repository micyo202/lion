package com.lion.common.info;

import java.io.Serializable;

/**
 * SystemInfo
 * 系统信息，包含user、os、jvm等
 *
 * @author Yanzheng
 * @date 2019/11/26
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public class SystemInfo implements Serializable {

    public static String info() {

        UserInfo userInfo = new UserInfo();
        OsInfo osInfo = new OsInfo();
        JavaInfo javaInfo = new JavaInfo();
        JvmInfo jvmInfo = new JvmInfo();
        RuntimeInfo runtimeInfo = new RuntimeInfo();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[UserInfo]\n").append(userInfo.toString()).append("\n---------")
                .append("\n[OsInfo]\n").append(osInfo.toString()).append("\n---------")
                .append("\n[JavaInfo]\n").append(javaInfo.toString()).append("\n---------")
                .append("\n[JvmInfo]\n").append(jvmInfo.toString()).append("\n---------")
                .append("\n[RuntimeInfo]\n").append(runtimeInfo.toString()).append("\n");

        return stringBuilder.toString();
    }

}