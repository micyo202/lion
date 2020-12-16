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

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * JsonUtilTests
 * TODO
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/4/9
 */
class JsonUtilTests {

    @Test
    void testJson() {
        Map<String, Object> object = new HashMap<>();
        object.put("id", "1001");
        object.put("name", "张三");
        object.put("sex", true);
        object.put("age", 20);

        String obj2Json = JsonUtil.obj2Json(object);
        System.out.println("obj2Json: " + obj2Json);

        String json = "{\"sex\":true,\"name\":\"张三\",\"id\":\"1001\",\"age\":20}";
        Map<String, Object> json2Obj = JsonUtil.json2Obj(json, Map.class);
        System.out.println("json2Obj: " + json2Obj);

        String getJsonValueByKey = JsonUtil.getJsonValueByKey(obj2Json, "name");
        System.out.println("getJsonValueByKey: " + getJsonValueByKey);
    }
}
