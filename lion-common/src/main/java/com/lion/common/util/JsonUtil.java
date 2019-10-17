package com.lion.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * JsonUtil
 * Json处理工具类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/07/17
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Component
@Slf4j
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String jsonObj2Str(Object jsonObj) {
        if (null == jsonObj) {
            return null;
        }
        try {
            final String str = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObj);
            return str;
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static <T> T jsonStr2Obj(String jsonStr, Class<T> objType) {
        if (StringUtils.isEmpty(jsonStr) || null == objType) {
            return null;
        }
        try {
            final T obj = objectMapper.readValue(jsonStr, objType);
            return obj;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static String getJsonStr(String json, String key) {
        if (StringUtils.isEmpty(json) || StringUtils.isEmpty(key)) {
            return null;
        }
        try {
            final JsonNode jsonNode = objectMapper.readTree(json);
            return getJsonNodeValue(jsonNode, key);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    private static String getJsonNodeValue(JsonNode jsonNode, String key) {
        if (null == jsonNode || StringUtils.isEmpty(key)) {
            return null;
        }
        int index = key.indexOf('.');
        if (index == -1) {
            if (null != jsonNode && null != jsonNode.get(key)) {
                return jsonNode.get(key).toString();
            }
            return null;
        } else {
            String s1 = key.substring(0, index);
            String s2 = key.substring(index + 1);
            return getJsonNodeValue(jsonNode.get(s1), s2);
        }
    }

}
