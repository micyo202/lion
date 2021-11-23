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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * JsonUtil
 * Json处理工具类
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/07/17
 */
@Slf4j
public class JsonUtil {

    private JsonUtil() {}

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * json对象转json字符串
     *
     * @param obj json对象
     * @return json字符串
     */
    public static String obj2Json(Object obj) {
        return obj2Json(obj, true);
    }

    /**
     * json对象转json字符串
     *
     * @param obj    json对象
     * @param pretty 是否格式化
     * @return json字符串
     */
    public static String obj2Json(Object obj, boolean pretty) {
        if (ObjectUtils.isEmpty(obj)) {
            return null;
        }
        try {
            // 使用 Gson 解析
            // final String jsonStr =  new GsonBuilder().setPrettyPrinting().create().toJson(object);

            // 使用 JackSon 解析
            String jsonStr;
            if (pretty) {
                // 格式化输出（默认）
                jsonStr = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            } else {
                // 普通输出
                jsonStr = objectMapper.writeValueAsString(obj);
            }
            return jsonStr;
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * json字符串转json对象
     *
     * @param jsonStr  json字符串
     * @param clazz json对象类型
     * @return json对象
     */
    public static <T> T json2Obj(String jsonStr, Class<T> clazz) {
        if (StringUtils.isBlank(jsonStr) || null == clazz) {
            return null;
        }
        try {
            T obj = objectMapper.readValue(jsonStr, clazz);
            return obj;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * json字符串 转 List集合泛型对象
     *
     * @param jsonStr      json字符串
     * @param elementClazz List元素对象类型
     * @return List集合泛型对象
     */
    public static <E> List<E> json2List(String jsonStr, Class<E> elementClazz) {
        return json2Obj(jsonStr, List.class, elementClazz);
    }

    /**
     * json字符串 转 Set集合泛型对象
     *
     * @param jsonStr      json字符串
     * @param elementClazz Set元素对象类型
     * @return Set集合泛型对象
     */
    public static <E> Set<E> json2Set(String jsonStr, Class<E> elementClazz) {
        return json2Obj(jsonStr, Set.class, elementClazz);
    }

    /**
     * json字符串 转 Map集合泛型对象
     *
     * @param jsonStr    json字符串
     * @param keyClazz   key对象类型
     * @param valueClazz value对象类型
     * @return Map集合泛型对象
     */
    public static <K, V> Map<K, V> json2Map(String jsonStr, Class<K> keyClazz, Class<V> valueClazz) {
        return json2Obj(jsonStr, Map.class, keyClazz, valueClazz);
    }

    /**
     * json字符串转json集合泛型对象
     *
     * @param jsonStr            json字符串
     * @param collectionClazz json集合类型
     * @param elementClazz    json集合元素类型（集合泛型）
     */
    public static <T> T json2Obj(String jsonStr, Class<T> collectionClazz, Class<?>... elementClazz) {
        if (StringUtils.isBlank(jsonStr) || null == collectionClazz || null == elementClazz) {
            return null;
        }
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClazz, elementClazz);
            T obj = objectMapper.readValue(jsonStr, javaType);
            return obj;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 根据key获取json字符串中的值
     *
     * @param jsonStr json字符串
     * @param key  键
     * @return 值
     */
    public static String getJsonValueByKey(String jsonStr, String key) {
        if (StringUtils.isAnyBlank(jsonStr, key)) {
            return null;
        }
        try {
            final JsonNode jsonNode = objectMapper.readTree(jsonStr);
            return getJsonValueByKey(jsonNode, key);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 根据key获取jsonNode对象中的值
     *
     * @param jsonNode jsonNode对象
     * @param key  键
     * @return 值
     */
    private static String getJsonValueByKey(JsonNode jsonNode, String key) {
        if (null == jsonNode || StringUtils.isBlank(key)) {
            return null;
        }
        int index = key.indexOf('.');
        if (index == -1) {
            if (null != jsonNode.get(key)) {
                return jsonNode.get(key).toString();
            }
            return null;
        } else {
            String s1 = key.substring(0, index);
            String s2 = key.substring(index + 1);
            return getJsonValueByKey(jsonNode.get(s1), s2);
        }
    }
}
