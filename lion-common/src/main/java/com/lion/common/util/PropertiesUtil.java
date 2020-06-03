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

    /**
     * 获取指定 properties 文件内容
     * 
     * @param fileName 文件名
     * @param key 键
     */
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
