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

import java.util.Random;

/**
 * RandomUtilTests
 * TODO
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/4/9
 */
public class RandomUtilTests {

    @Test
    public void testRandom() {
        System.out.println(RandomUtil.randomInt());
        System.out.println(RandomUtil.randomLong());
        System.out.println(RandomUtil.randomDouble());
        System.out.println(RandomUtil.randomBoolean());
        System.out.println(RandomUtil.randomChar());
        System.out.println(RandomUtil.randomString(6));
        System.out.println(RandomUtil.randomName());
        System.out.println(RandomUtil.randomIdCard());
        System.out.println(RandomUtil.randomNumber());
        System.out.println(RandomUtil.randomPhone());
        System.out.println(RandomUtil.randomAddress());
    }
}
