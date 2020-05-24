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
package com.lion.demo.consumer.lambda;

import org.assertj.core.util.Maps;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * LambdaTests
 * lambda表达式示例代码
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/03/29
 */
public class LambdaTests {

    @Test
    public void test() {
        // 使用 stream、parallelStream 对集合进行操作
        List<String> strings = Arrays.asList("hello tom", "hello jerry", "hello tom", "hello tom", "hello world", "hello jerry");
        List<Integer> integers = Arrays.asList(6, 3, 2, 4, 5, 9, 8, 1, 7);

        integers.stream()
                .filter(x -> x % 2 == 0)
                .map(x -> x * 10)
                .sorted((x, y) -> x > y ? -1 : 1)
                .forEach(System.out::println);

        strings.parallelStream()
                .flatMap(x -> Arrays.stream(x.split(" ")))
                .map(x -> Maps.newHashMap(x, 1))
                //.map(x -> new Tuple2(x, 1))
                //.collect(Collectors.groupingBy(Tuple2::_1));
                .forEach(System.out::println);

    }

}
