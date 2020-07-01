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

import org.apache.commons.lang3.StringUtils;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * GeneratorUtil
 * 生成器工具类
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/1/22
 */
public class GeneratorUtil {

    private static int increment;

    /**
     * 线程同步锁生成seqNo
     */
    public synchronized static String getSeqNo() {
        String applicationName = YamlUtil.getBootstrapValue("spring.application.name");
        applicationName = StringUtils.isBlank(applicationName) ? "lion" : applicationName;
        int applicationNameHashCode = applicationName.hashCode() < 0 ? applicationName.hashCode() * -1 : applicationName.hashCode();
        increment = increment >= 9999 ? 1 : increment + 1;
        String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String uuid = String.format("%s%s%04d", applicationNameHashCode, currentDateTime, increment);
        return uuid;
    }

    /**
     * 生成指定长度的随机数
     */
    public static String getRandomKey(int len) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new SecureRandom();
        // 产生16位的强随机数
        for (int i = 0; i < len; i++) {
            // 产生0-2的3位随机数
            int type = random.nextInt(3);
            switch (type) {
                case 0:
                    // 0-9的随机数
                    stringBuilder.append(random.nextInt(10));
                    // int randomInt = ThreadLocalRandom.current().ints(0, 10).distinct().limit(1).findFirst().getAsInt();
                    break;
                case 1:
                    // ASCII在65-90之间为大写,获取大写随机
                    stringBuilder.append((char) (random.nextInt(25) + 65));
                    break;
                case 2:
                    // ASCII在97-122之间为小写，获取小写随机
                    stringBuilder.append((char) (random.nextInt(25) + 97));
                    break;
                default:
                    break;
            }
        }
        return stringBuilder.toString();
    }
}
