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
package com.lion.common.result;

import com.lion.common.constant.ResponseStatus;
import com.lion.common.tuple.Tuple2;
import com.lion.common.util.DateUtil;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * ResultTests
 * TODO
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/6/17
 */
class ResultTests {

    @Test
    void testResult() {

        Map<String, Object> extra = new HashMap<>();
        extra.put("ext_1", "扩展属性1");
        extra.put("ext_2", "扩展属性2");

        Result<String> result_1 = new Result<>();
        result_1
                .setCode(200)
                .setMsg("提示信息")
                .setTimestamp(DateUtil.getTimestamp())
                .setExtra(extra)
                .setData("成功后的数据Data");

        result_1.addExtra("ext_3", "扩展属性3");
        result_1.addExtra("ext_4", "扩展属性4");

        result_1.addExtra(new Tuple2("ext_5", "扩展属性5"));
        result_1.addExtra(new Tuple2("ext_6", "扩展属性6"));

        System.out.println(result_1);

        System.out.println(Result.status(ResponseStatus.SUCCESS));
        System.out.println(Result.status(ResponseStatus.FAILURE));
        System.out.println(Result.status(ResponseStatus.BAD_REQUEST));
        System.out.println(Result.status(ResponseStatus.UNAUTHORIZED));
        System.out.println(Result.status(ResponseStatus.FORBIDDEN));
        System.out.println(Result.status(ResponseStatus.NOT_FOUND));
        System.out.println(Result.status(ResponseStatus.METHOD_NOT_ALLOWED));
        System.out.println(Result.status(ResponseStatus.REQUEST_TIMEOUT));
        System.out.println(Result.status(ResponseStatus.TOO_MANY_REQUESTS));

        System.out.println(Result.success("成功构建方法数据", extra));
        System.out.println(Result.success("成功构建方法数据"));
        System.out.println(Result.success());

        System.out.println(Result.failure(999, "错误构建提示信息"));
        System.out.println(Result.failure("错误构建提示信息"));
        System.out.println(Result.failure());
    }

}
