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
public class JsonUtilTests {

    @Test
    public void testJson() {
        Map<String, Object> jsonObj = new HashMap<>();
        jsonObj.put("id", "1001");
        jsonObj.put("name", "张三");
        jsonObj.put("sex", true);
        jsonObj.put("age", 20);

        String jsonObj2Str = JsonUtil.jsonObj2Str(jsonObj);
        System.out.println("jsonObj2Str: " + jsonObj2Str);

        String jsonStr = "{\"sex\":true,\"name\":\"张三\",\"id\":\"1001\",\"age\":20}";
        Map jsonStr2Obj = JsonUtil.jsonStr2Obj(jsonStr, Map.class);
        System.out.println("jsonStr2Obj: " + jsonStr2Obj);

        String getJsonStr = JsonUtil.getJsonStr(jsonObj2Str, "name");
        System.out.println("getJsonStr: " + getJsonStr);
    }
}
