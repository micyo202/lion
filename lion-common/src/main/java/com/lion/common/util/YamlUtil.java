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
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * YamlUtil
 * Yaml工具类
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/2/12
 */
@Slf4j
public class YamlUtil {

    private static final String BOOTSTRAP_YML = "bootstrap.yml";
    private static final String APPLICATION_YML = "application.yml";

    /**
     * 获取 bootstrap.yml 配置内容
     *
     * @param key 键
     */
    public static String getBootstrapValue(String key) {
        return getValueByYaml(BOOTSTRAP_YML, key);
    }

    /**
     * 获取 application.yml 配置内容
     *
     * @param key 键
     */
    public static String getApplicationValue(String key) {
        return getValueByYaml(APPLICATION_YML, key);
    }

    /**
     * 获取指定 yml 配置内容
     *
     * @param fileName yml文件名
     * @param key 键
     */
    public static String getValueByYaml(String fileName, String key) {

        if (StringUtils.isAnyEmpty(fileName, key)) {
            return null;
        }

        String result = null;

        InputStream inputStream = YamlUtil.class.getClassLoader().getResourceAsStream(fileName);
        if (null == inputStream) {
            return null;
        }
        Yaml yaml = new Yaml();
        Map<String, Object> map = yaml.load(inputStream);

        String[] keys = key.split("\\.");
        for (String k : keys) {
            Object o = map.get(k);
            if (ObjectUtils.isNotEmpty(o)) {
                if (o instanceof Map) {
                    map = (Map<String, Object>) o;
                } else {
                    result = o.toString();
                }
            }
        }

        try {
            inputStream.close();
        } catch (IOException e) {
            log.error("输入流 inputStream 关闭失败", e);
        }

        return result;
    }

}
