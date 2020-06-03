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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * JsonUtil
 * Json处理工具类
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/07/17
 */
@Slf4j
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * json对象转json字符串
     *
     * @param jsonObj json对象
     * @return json字符串
     */
    public static String jsonObj2Str(Object jsonObj) {
        return jsonObj2Str(jsonObj, true);
    }

    /**
     * json对象转json字符串
     *
     * @param jsonObj json对象
     * @param pretty  是否格式化
     * @return json字符串
     */
    public static String jsonObj2Str(Object jsonObj, boolean pretty) {

        if (null == jsonObj) {
            return null;
        }
        try {
            // 使用 Gson 解析
            // final String str =  new GsonBuilder().setPrettyPrinting().create().toJson(obj);

            // 使用 JackSon 解析
            String str;
            if (pretty) {
                // 格式化输出（默认）
                str = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObj);
            } else {
                // 普通输出
                str = objectMapper.writeValueAsString(jsonObj);
            }
            return str;
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * json字符串转json对象
     *
     * @param jsonStr json字符串
     * @param objType json对象类型
     * @return json对象
     */
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

    /**
     * 根据key获取json字符串中的值
     *
     * @param jsonStr json字符串
     * @param key     键
     * @return 值
     */
    public static String getJsonStr(String jsonStr, String key) {
        if (StringUtils.isEmpty(jsonStr) || StringUtils.isEmpty(key)) {
            return null;
        }
        try {
            final JsonNode jsonNode = objectMapper.readTree(jsonStr);
            return getJsonNodeValue(jsonNode, key);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 根据key获取jsonNode对象中的值
     *
     * @param jsonNode jsonNode对象
     * @param key      键
     * @return 值
     */
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
