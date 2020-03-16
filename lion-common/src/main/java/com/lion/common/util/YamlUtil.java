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
 * @author Yanzheng https://github.com/micyo202
 * @date 2020/2/12
 * Copyright 2020 Yanzheng. All rights reserved.
 */
@Slf4j
public class YamlUtil {

    private static final String BOOTSTRAP_YML = "bootstrap.yml";
    private static final String APPLICATION_YML = "application.yml";

    public static String getBootstrapValue(String key) {
        return getValueByYaml(BOOTSTRAP_YML, key);
    }

    public static String getApplicationValue(String key) {
        return getValueByYaml(APPLICATION_YML, key);
    }

    public static String getValueByYaml(String fileName, String key) {

        if (StringUtils.isAnyEmpty(fileName, key)) {
            return null;
        }

        String result = null;

        InputStream inputStream = YamlUtil.class.getClassLoader().getResourceAsStream(fileName);
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
