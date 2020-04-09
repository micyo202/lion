package com.lion.common.util;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * GeneratorUtil
 * 生成器工具类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2020/1/22
 * Copyright 2020 Yanzheng. All rights reserved.
 */
public class GeneratorUtil {

    private static int increment;

    public synchronized static String getUUID() {
        String applicationName = YamlUtil.getBootstrapValue("spring.application.name");
        applicationName = StringUtils.isEmpty(applicationName) ? "lion" : applicationName;
        int applicationNameHashCode = applicationName.hashCode();
        increment = increment >= 9999 ? 1 : increment + 1;
        String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String uuid = String.format("%s%s%04d", applicationNameHashCode, currentDateTime, increment);
        return uuid;
    }

}
