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

    public static String jsonObj2Str(Object jsonObj) {
        return jsonObj2Str(jsonObj, true);
    }

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
                str = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObj); // 格式化输出（默认）
            } else {
                str = objectMapper.writeValueAsString(jsonObj); // 普通输出
            }
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
