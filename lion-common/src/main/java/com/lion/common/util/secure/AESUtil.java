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

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * AESUtil
 * AES工具类
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/10/16
 */
@Slf4j
public class AESUtil {

    private AESUtil() {
    }

    /**
     * 加密、解密方式
     */
    private static final String AES = "AES";

    /**
     * 初始向量值，必须16位
     */
    private static final String IV_STRING = "16-Bytes--String";

    /**
     * 默认秘钥，必须16位
     */
    private static final String DEFAULT_KEY = "70pQxrWV7NWgGRXQ";

    /**
     * 秘钥长度，必须16
     */
    private static final int KEY_LENGTH = 16;

    /**
     * 指定加密的算法/工作模式/填充方式
     * AES/CBC/PKCS5Padding
     * AES/GCM/NoPadding
     */
    private static final String CIPHER = "AES/CBC/PKCS5Padding";

    /**
     * AES 加密
     *
     * @param data 明文
     * @return 密文
     */
    public static String encrypt(String data) {
        return encrypt(data, DEFAULT_KEY);
    }

    /**
     * AES 加密
     *
     * @param data 明文
     * @param key  秘钥（16位）
     * @return 密文
     */
    public static String encrypt(String data, String key) {
        if (StringUtils.isEmpty(data) || StringUtils.isEmpty(key) || KEY_LENGTH != key.length()) {
            return null;
        }
        try {
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] keyBytes = key.getBytes();
            // 注意，为了能与 iOS 统一这里的 key 不可以使用 KeyGenerator、SecureRandom、SecretKey 生成
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, AES);
            byte[] initParam = IV_STRING.getBytes();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
            Cipher cipher = Cipher.getInstance(CIPHER);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] encryptBytes = cipher.doFinal(dataBytes);
            return Base64Util.encodeStr(encryptBytes);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * AES 解密
     *
     * @param data 密文
     * @return 明文
     */
    public static String decrypt(String data) {
        return decrypt(data, DEFAULT_KEY);
    }

    /**
     * AES 解密
     *
     * @param data 密文
     * @param key  秘钥（16位）
     * @return 明文
     */
    public static String decrypt(String data, String key) {
        if (StringUtils.isEmpty(data) || StringUtils.isEmpty(key) || KEY_LENGTH != key.length()) {
            return null;
        }
        try {
            byte[] dataBytes = Base64Util.decode(data);
            byte[] keyBytes = key.getBytes();
            SecretKeySpec secretKey = new SecretKeySpec(keyBytes, AES);
            byte[] initParam = IV_STRING.getBytes();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
            Cipher cipher = Cipher.getInstance(CIPHER);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
            byte[] decryptBytes = cipher.doFinal(dataBytes);
            return new String(decryptBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
