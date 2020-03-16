package com.lion.common.info;

import java.io.Serializable;

/**
 * JvmInfo
 * Java Virtual Machine Implementation的信息
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/10/24
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public class JvmInfo implements Serializable {

    private final String JAVA_VM_NAME = System.getProperty("java.vm.name");
    private final String JAVA_VM_VERSION = System.getProperty("java.vm.version");
    private final String JAVA_VM_VENDOR = System.getProperty("java.vm.vendor");
    private final String JAVA_VM_INFO = System.getProperty("java.vm.info");

    /**
     * 取得当前JVM的名称
     */
    public final String getName() {
        return JAVA_VM_NAME;
    }

    /**
     * 取得当前JVM的版本
     */
    public final String getVersion() {
        return JAVA_VM_VERSION;
    }

    /**
     * 取得当前JVM的厂商
     */
    public final String getVendor() {
        return JAVA_VM_VENDOR;
    }

    /**
     * 取得当前JVM的信息
     */
    public final String getInfo() {
        return JAVA_VM_INFO;
    }

    /**
     * 将Java Virutal Machine Implementation的信息转换成字符串。
     */
    @Override
    public final String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("JavaVM Name:    ").append(getName())
                .append("\nJavaVM Version: ").append(getVersion())
                .append("\nJavaVM Vendor:  ").append(getVendor())
                .append("\nJavaVM Info:    ").append(getInfo());

        return builder.toString();
    }

}