package com.lion.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * JsonUtil
 * json处理工具类
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
        try {
            final String str = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObj);
            return str;
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static <T> T jsonStr2Obj(String jsonStr, Class<T> objType) {
        try {
            final T obj = objectMapper.readValue(jsonStr, objType);
            return obj;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static String getJsonStr(String json, String key) {
        try {
            final JsonNode jsonNode = objectMapper.readTree(json);
            return getJsonNodeValue(jsonNode, key);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static String getJsonNodeValue(JsonNode jsonNode, String key) {
        int index = key.indexOf('.');
        if (index == -1) {
            if (jsonNode != null && jsonNode.get(key) != null) {
                return jsonNode.get(key).textValue();
            }
            return null;
        } else {
            String s1 = key.substring(0, index);
            String s2 = key.substring(index + 1);
            return getJsonNodeValue(jsonNode.get(s1), s2);
        }
    }

    public static String getJsonString(String json, String key) {
        try {
            return objectMapper.readValue(json, Map.class).get(key).toString();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static Map getJsonMap(String json, String key) {
        try {
            return (Map) objectMapper.readValue(json, Map.class).get(key);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static List getJsonList(String json, String key) {
        try {
            return (List) objectMapper.readValue(json, Map.class).get(key);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

}
