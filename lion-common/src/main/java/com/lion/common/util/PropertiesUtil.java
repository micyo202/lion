package com.lion.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * PropertiesUtil
 * Properties 配置文件工具类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2020/2/13
 * Copyright 2020 Yanzheng. All rights reserved.
 */
@Slf4j
public class PropertiesUtil {

    public static String getValue(String fileName, String key) {

        if (StringUtils.isAnyEmpty(fileName, key)) {
            return null;
        }

        InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
        Properties properties = new Properties();
        String result = null;
        try {
            properties.load(inputStream);
            result = properties.getProperty(key);
        } catch (IOException e) {
            log.error("获取 properties 配置文件内容失败", e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                log.error("输入流 inputStream 关闭失败", e);
            }
        }

        return result;
    }

}
