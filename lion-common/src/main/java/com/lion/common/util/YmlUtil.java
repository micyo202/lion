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
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.system.ApplicationHome;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * YmlUtil
 * Yml工具类
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/2/12
 */
@Slf4j
public class YmlUtil {

    private YmlUtil() {
    }

    private static final String BOOTSTRAP_YML = "bootstrap.yml";
    private static final String APPLICATION_YML = "application.yml";

    /**
     * 获取bootstrap.yml配置内容
     *
     * @param key 键
     * @return 值
     */
    public static Object getBootstrapValue(String key) {
        return getValueByYml(BOOTSTRAP_YML, key);
    }

    /**
     * 获取application.yml配置内容
     *
     * @param key 键
     * @return 值
     */
    public static Object getApplicationValue(String key) {
        return getValueByYml(APPLICATION_YML, key);
    }

    /**
     * 获取yml配置内容
     *
     * @param fileName yml文件名
     * @param key      键
     * @return 值
     */
    public static Object getValueByYml(String fileName, String key) {
        Map<String, Object> map = getYml(fileName);

        if (MapUtils.isEmpty(map)) {
            return null;
        }

        Object result = null;
        String[] keys = key.split("\\.");
        for (String k : keys) {
            Object o = map.get(k);
            if (ObjectUtils.isNotEmpty(o)) {
                if (o instanceof Map && !k.equals(key) && !key.endsWith("." + k)) {
                    map = (Map<String, Object>) o;
                } else {
                    result = o;
                }
            } else {
                result = null;
            }
        }

        return result;
    }

    /**
     * 获取 yml 文件内容
     *
     * @param fileName yml文件名
     * @return
     */
    public static Map<String, Object> getYml(String fileName) {
        if (StringUtils.isBlank(fileName)) {
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
        Yaml yaml = new Yaml();
        // file:./config
        filePath = new ApplicationHome().getDir().getPath() + "/config/" + fileName;
        file = new File(filePath);
        if (file.exists()) {
            try (InputStream inputStream = new FileInputStream(file)) {
                return yaml.loadAs(inputStream, Map.class);
            } catch (IOException e) {
                log.error("IO流处理失败", e);
            }
        }

        // file:./
        filePath = new ApplicationHome().getDir().getPath() + "/" + fileName;
        file = new File(filePath);
        if (file.exists()) {
            try (InputStream inputStream = new FileInputStream(file)) {
                return yaml.loadAs(inputStream, Map.class);
            } catch (IOException e) {
                log.error("IO流处理失败", e);
            }
        }

        // classpath:config
        try (InputStream inputStream = YmlUtil.class.getClassLoader().getResourceAsStream("config/" + fileName)) {
            if (null != inputStream) {
                return yaml.loadAs(inputStream, Map.class);
            }
        } catch (IOException e) {
            log.error("IO流处理失败", e);
        }

        // classpath:
        try (InputStream inputStream = YmlUtil.class.getClassLoader().getResourceAsStream(fileName)) {
            if (null != inputStream) {
                return yaml.loadAs(inputStream, Map.class);
            }
        } catch (IOException e) {
            log.error("IO流处理失败", e);
        }

        return null;
    }
}
