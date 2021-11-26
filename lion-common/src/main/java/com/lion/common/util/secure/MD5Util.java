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

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * MD5Util
 * MD5工具类
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/10/16
 */
@Slf4j
public class MD5Util {

    private MD5Util() {
    }

    /**
     * 加密、解密方式
     */
    private static final String MD5 = "MD5";

    /**
     * MD5 加密
     *
     * @param data 明文
     * @return 16位字符串密文
     */
    public static String encryptHex16(String data) {
        return encryptHex(data).substring(8, 24);
    }

    /**
     * MD5 加密
     *
     * @param data 明文
     * @return 32位字符串密文
     */
    public static String encryptHex(String data) {
        byte[] encrypt = encrypt(data);
        return HexUtil.bytes2Hex(encrypt);
        // 首位0会忽略
        //return new BigInteger(1, encrypt).toString(16);
    }

    /**
     * MD5 加密
     *
     * @param data 明文
     * @return 字节密文
     */
    public static byte[] encrypt(String data) {
        if (StringUtils.isEmpty(data)) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MD5);
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            messageDigest.update(dataBytes);
            byte[] digest = messageDigest.digest();
            /*
            char[] hexs = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            int j = digest.length;
            char[] chars = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = digest[i];
                chars[k++] = hexs[byte0 >>> 4 & 0xf];
                chars[k++] = hexs[byte0 & 0xf];
            }
            return String.valueOf(chars);
            */
            return digest;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
