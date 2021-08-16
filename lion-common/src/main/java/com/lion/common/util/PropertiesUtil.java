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

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.system.ApplicationHome;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * PropertiesUtil
 * Properties 配置文件工具类
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/2/13
 */
@Slf4j
public class PropertiesUtil {

    private PropertiesUtil() {
    }

    /**
     * 获取值
     *
     * @param fileName 文件名
     * @param key      键
     * @return 值
     */
    public static String getValue(String fileName, String key) {

        if (StringUtils.isAnyBlank(fileName, key)) {
            return null;
        }

        /**
         * 按默认优先级读取配置，优先级顺序如下
         *
         * file:./config
         * file:./
         * classpath:config
         * classpath:
         */
        String filePath;
        File file;
        Properties properties = new Properties();
        // file:./config
        filePath = new ApplicationHome().getDir().getPath() + "/config/" + fileName;
        file = new File(filePath);
        if (file.exists()) {
            try (InputStream inputStream = new FileInputStream(file)) {
                properties.load(inputStream);
                return properties.getProperty(key);
            } catch (IOException e) {
                log.error("IO流处理失败", e);
            }
        }

        // file:./
        filePath = new ApplicationHome().getDir().getPath() + "/" + fileName;
        file = new File(filePath);
        if (file.exists()) {
            try (InputStream inputStream = new FileInputStream(file)) {
                properties.load(inputStream);
                return properties.getProperty(key);
            } catch (IOException e) {
                log.error("IO流处理失败", e);
            }
        }

        // classpath:config
        try (InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("config/" + fileName)) {
            if (null == inputStream) {
                return null;
            }
            properties.load(inputStream);
            return properties.getProperty(key);
        } catch (IOException e) {
            log.error("IO流处理失败", e);
        }

        // classpath:
        try (InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName)) {
            if (null == inputStream) {
                return null;
            }
            properties.load(inputStream);
            return properties.getProperty(key);
        } catch (IOException e) {
            log.error("IO流处理失败", e);
        }

        return null;
    }
}
