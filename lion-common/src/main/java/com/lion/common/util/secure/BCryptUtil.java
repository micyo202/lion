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
package com.lion.common.util.secure;

import jodd.crypt.BCrypt;
import org.apache.commons.lang3.StringUtils;

/**
 * BCryptUtil
 * BCrypt工具类
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/10/21
 */
public class BCryptUtil {

    public static String encrypt(String password) {
        if (StringUtils.isEmpty(password)) {
            return null;
        }
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean verify(String candidate, String encrypt) {
        if (StringUtils.isEmpty(candidate) || StringUtils.isEmpty(encrypt)) {
            return false;
        }
        return BCrypt.checkpw(candidate, encrypt);
    }

    public static void main(String[] args) {
        String password = "Yanzheng -> github.com/micyo202";
        String encrypt = BCryptUtil.encrypt(password);
        boolean verify = BCryptUtil.verify(password, encrypt);
        System.out.println("原文：" + password);
        System.out.println("加密后：" + encrypt);
        System.out.println("验证结果：" + verify);
    }

}
