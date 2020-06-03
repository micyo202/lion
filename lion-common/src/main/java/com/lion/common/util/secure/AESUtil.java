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
import java.util.Base64;

/**
 * AESUtil
 * AES工具类
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/10/16
 */
@Slf4j
public class AESUtil {

    /**
     * 加密、解密方式
     */
    private static final String AES = "AES";

    /**
     * 字符编码
     */
    private static final String ENCODEING = "UTF-8";

    /**
     * 初始向量值，必须16位
     */
    private static final String IV_STRING = "16-Bytes--String";

    /**
     * 默认秘钥，必须16位
     */
    private static final String DEFAULT_KEY = "70pQxrWV7NWgGRXQ";

    /**
     * 指定加密的算法、工作模式和填充方式
     */
    private static final String CIPHER = "AES/CBC/PKCS5Padding";

    /**
     * AES 使用默认秘钥加密
     *
     * @param text 明文
     * @return 密文
     */
    public static String encrypt(String text) {
        return encrypt(text, DEFAULT_KEY);
    }

    /**
     * AES 自定义秘钥加密
     *
     * @param text 明文
     * @param key  秘钥（必须16位）
     * @return 密文
     */
    public static String encrypt(String text, String key) {
        if (StringUtils.isEmpty(text) || StringUtils.isEmpty(key) || 16 != key.length()) {
            return null;
        }
        try {
            byte[] byteContent = text.getBytes(ENCODEING);
            byte[] enCodeFormat = key.getBytes();
            // 注意，为了能与 iOS 统一这里的 key 不可以使用 KeyGenerator、SecureRandom、SecretKey 生成
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, AES);
            byte[] initParam = IV_STRING.getBytes();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
            Cipher cipher = Cipher.getInstance(CIPHER);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] encryptedBytes = cipher.doFinal(byteContent);
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * AES 默认秘钥解密
     *
     * @param ciphertext 密文
     * @return 明文
     */
    public static String decrypt(String ciphertext) {
        return decrypt(ciphertext, DEFAULT_KEY);
    }

    /**
     * AES 自定义秘钥解密
     *
     * @param ciphertext 密文
     * @param key        秘钥（必须16位）
     * @return 明文
     */
    public static String decrypt(String ciphertext, String key) {
        if (StringUtils.isEmpty(ciphertext) || StringUtils.isEmpty(key) || 16 != key.length()) {
            return null;
        }
        try {
            byte[] encryptedBytes = Base64.getDecoder().decode(ciphertext);
            byte[] enCodeFormat = key.getBytes();
            SecretKeySpec secretKey = new SecretKeySpec(enCodeFormat, AES);
            byte[] initParam = IV_STRING.getBytes();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);
            Cipher cipher = Cipher.getInstance(CIPHER);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
            byte[] result = cipher.doFinal(encryptedBytes);
            return new String(result, ENCODEING);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

}
