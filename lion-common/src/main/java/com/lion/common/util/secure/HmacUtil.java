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

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * HmacUtil
 * Hmac工具类
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/10/22
 */
@Slf4j
public class HmacUtil {

    private HmacUtil() {
    }

    /**
     * 加密、解密方式：MD5
     */
    private static final String HMAC_MD5 = "HmacMD5";

    /**
     * 加密、解密方式：SHA-1
     */
    private static final String HMAC_SHA1 = "HmacSHA1";

    /**
     * 加密、解密方式：SHA-224
     */
    private static final String HMAC_SHA224 = "HmacSHA224";

    /**
     * 加密、解密方式：SHA-256
     */
    private static final String HMAC_SHA256 = "HmacSHA256";

    /**
     * 加密、解密方式：SHA-384
     */
    private static final String HMAC_SHA384 = "HmacSHA384";

    /**
     * 加密、解密方式：SHA-512
     */
    private static final String HMAC_SHA512 = "HmacSHA512";

    /**
     * 默认秘钥，必须16位
     */
    private static final String DEFAULT_KEY = "MkI3I1YlFOFr57YL";

    /**
     * MD5 加密
     *
     * @param data 明文
     * @return 密文
     */
    public static String encryptHmacMD5(String data) {
        return encryptHmac(data, DEFAULT_KEY, HMAC_MD5);
    }

    /**
     * SHA1 加密
     *
     * @param data 明文
     * @return 密文
     */
    public static String encryptHmacSHA1(String data) {
        return encryptHmacSHA1(data, DEFAULT_KEY);
    }

    /**
     * SHA1 加密
     *
     * @param data 明文
     * @param key  秘钥
     * @return 密文
     */
    public static String encryptHmacSHA1(String data, String key) {
        return encryptHmac(data, key, HMAC_SHA1);
    }

    /**
     * SHA224 加密
     *
     * @param data 明文
     * @return 密文
     */
    public static String encryptHmacSHA224(String data) {
        return encryptHmacSHA224(data, DEFAULT_KEY);
    }

    /**
     * SHA224 加密
     *
     * @param data 明文
     * @param key  秘钥
     * @return 密文
     */
    public static String encryptHmacSHA224(String data, String key) {
        return encryptHmac(data, key, HMAC_SHA224);
    }

    /**
     * SHA256 加密
     *
     * @param data 明文
     * @return 密文
     */
    public static String encryptHmacSHA256(String data) {
        return encryptHmacSHA256(data, DEFAULT_KEY);
    }

    /**
     * SHA256 加密
     *
     * @param data 明文
     * @param key  秘钥
     * @return 密文
     */
    public static String encryptHmacSHA256(String data, String key) {
        return encryptHmac(data, key, HMAC_SHA256);
    }

    /**
     * SHA384 加密
     *
     * @param data 明文
     * @return 密文
     */
    public static String encryptHmacSHA384(String data) {
        return encryptHmacSHA384(data, DEFAULT_KEY);
    }

    /**
     * SHA384 加密
     *
     * @param data 明文
     * @param key  秘钥
     * @return 密文
     */
    public static String encryptHmacSHA384(String data, String key) {
        return encryptHmac(data, key, HMAC_SHA384);
    }

    /**
     * SHA521 加密
     *
     * @param data 明文
     * @return 密文
     */
    public static String encryptHmacSHA512(String data) {
        return encryptHmacSHA512(data, DEFAULT_KEY);
    }

    /**
     * SHA521 加密
     *
     * @param data 明文
     * @param key  秘钥
     * @return 密文
     */
    public static String encryptHmacSHA512(String data, String key) {
        return encryptHmac(data, key, HMAC_SHA512);
    }

    /**
     * 私有方法 - HMAC 基础算法
     *
     * @param data 明文
     * @param key  秘钥
     * @param type 类型
     * @return 密文
     */
    private static String encryptHmac(String data, String key, String type) {
        if (StringUtils.isEmpty(data) || StringUtils.isEmpty(key) || StringUtils.isEmpty(type)) {
            return null;
        }
        try {
            // byte[] key = getHmacKey(type);   // 随机生成秘钥
            byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);

            // 1、还原密钥
            SecretKey secretKey = new SecretKeySpec(keyBytes, type);
            // 2、创建MAC对象
            Mac mac = Mac.getInstance(type);
            // 3、设置密钥
            mac.init(secretKey);
            // 4、数据加密
            byte[] bytes = mac.doFinal(dataBytes);
            // 5、生成数据
            return HexUtil.bytes2Hex(bytes);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 私有方法 - HMAC 秘钥生成
     *
     * @param type 类型
     * @return 秘钥
     */
    public static byte[] getHmacKey(String type) {
        byte[] bytes = new byte[0];
        try {
            // 1、创建密钥生成器
            KeyGenerator keyGenerator = KeyGenerator.getInstance(type);
            // 2、产生密钥
            SecretKey secretKey = keyGenerator.generateKey();
            // 3、获取密钥
            bytes = secretKey.getEncoded();
            return bytes;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return bytes;
    }

    /**
     * 私有方法 - 数据转16进制编码
     *
     * @param bytes       数据
     * @param toUpperCase 是否转大写
     * @return 16进制编码
     */
//    private static String encodeHex(final byte[] bytes, final boolean toUpperCase) {
//        StringBuilder stringBuilder = new StringBuilder();
//        for (byte b : bytes) {
//            stringBuilder.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
//        }
//        if (toUpperCase) {
//            return stringBuilder.toString().toUpperCase();
//        }
//        return stringBuilder.toString();
//
//        /**
//         * final char[] digitsUpper = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
//         * final char[] digitsLower = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
//         * final char[] toDigits = toUpperCase ? digitsLower : digitsUpper;
//         * final int len = bytes.length;
//         * final char[] out = new char[len << 1];
//         * int k = 0;
//         * for (int i = 0; i < len; i++) {
//         *    out[k++] = toDigits[(0xF0 & bytes[i]) >>> 4];
//         *    out[k++] = toDigits[0x0F & bytes[i]];
//         * }
//         * return new String(out);
//         */
//    }
}