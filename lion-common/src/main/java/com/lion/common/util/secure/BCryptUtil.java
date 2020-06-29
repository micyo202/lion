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

    /**
     * BCrypt 加密
     *
     * @param text 明文
     * @return 密文
     */
    public static String encrypt(String text) {
        if (StringUtils.isBlank(text)) {
            return null;
        }
        return BCrypt.hashpw(text, BCrypt.gensalt());
    }

    /**
     * BCrypt 校验
     *
     * @param text       明文
     * @param ciphertext 密文
     * @return 是否正确
     */
    public static boolean verify(String text, String ciphertext) {
        if (StringUtils.isAnyBlank(text, ciphertext)) {
            return false;
        }
        return BCrypt.checkpw(text, ciphertext);
    }
}
