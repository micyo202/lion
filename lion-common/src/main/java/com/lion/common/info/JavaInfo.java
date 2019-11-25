package com.lion.common.info;

import jodd.system.SystemUtil;

import java.io.Serializable;

/**
 * JavaInfo
 * Java Implementation的信息
 *
 * @author Yanzheng
 * @date 2019/11/21
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public class JavaInfo implements Serializable {

    private final String JAVA_VERSION = SystemUtil.get("java.version", null);
    private final String JAVA_VENDOR = SystemUtil.get("java.vendor", null);
    private final String JAVA_VENDOR_URL = SystemUtil.get("java.vendor.url", null);

    /**
     * 取得当前Java impl.的版本
     *
     * @return 属性值，如果不能取得（因为Java安全限制）或值不存在，则返回null
     */
    public final String getVersion() {
        return JAVA_VERSION;
    }

    /**
     * 取得当前Java impl.的厂商
     *
     * @return 属性值，如果不能取得（因为Java安全限制）或值不存在，则返回null
     */
    public final String getVendor() {
        return JAVA_VENDOR;
    }

    /**
     * 取得当前Java impl.的厂商网站的URL
     *
     * @return 属性值，如果不能取得（因为Java安全限制）或值不存在，则返回null
     */
    public final String getVendorURL() {
        return JAVA_VENDOR_URL;
    }

    /**
     * 将Java Implementation的信息转换成字符串
     */
    @Override
    public final String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Java Version:    ").append(getVersion())
                .append("\nJava Vendor:     ").append(getVendor())
                .append("\nJava Vendor URL: ").append(getVendorURL());

        return builder.toString();
    }

}
