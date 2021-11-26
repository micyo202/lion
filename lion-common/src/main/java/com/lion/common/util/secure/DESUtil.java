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
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

/**
 * DESUtil
 * DES工具类
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/10/16
 */
@Slf4j
public class DESUtil {

    private DESUtil() {
    }

    /**
     * 加密解密方式
     */
    private static final String DES = "DES";

    /**
     * 秘钥
     */
    private static final String DEFAULT_KEY = "https://github.com/micyo202";

    /**
     * DES 加密
     *
     * @param data 明文
     * @return 密文
     */
    public static String encrypt(String data) {
        return encrypt(data, DEFAULT_KEY);
    }

    /**
     * DES 加密
     *
     * @param data 明文
     * @param key  秘钥
     * @return 密文
     */
    public static String encrypt(String data, String key) {
        if (StringUtils.isEmpty(data) || StringUtils.isEmpty(key)) {
            return null;
        }
        try {
            // 生成一个可信任的随机数源
            SecureRandom secureRandom = new SecureRandom();
            // 从原始密钥数据创建DESKeySpec对象
            DESKeySpec keySpec = new DESKeySpec(key.getBytes(StandardCharsets.UTF_8));
            // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
            SecretKey securekey = keyFactory.generateSecret(keySpec);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance(DES);
            // 用密钥初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, secureRandom);
            byte[] bytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64Util.encodeStr(bytes);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }


    /**
     * DES 解密
     *
     * @param data 密文
     * @return 明文
     */
    public static String decrypt(String data) {
        return decrypt(data, DEFAULT_KEY);
    }

    /**
     * DES 解密
     *
     * @param data 密文
     * @param key  秘钥
     * @return 明文
     */
    public static String decrypt(String data, String key) {
        if (StringUtils.isEmpty(data) || StringUtils.isEmpty(key)) {
            return null;
        }
        try {
            // 生成一个可信任的随机数源
            SecureRandom secureRandom = new SecureRandom();
            // 从原始密钥数据创建DESKeySpec对象
            DESKeySpec keySpec = new DESKeySpec(key.getBytes(StandardCharsets.UTF_8));
            // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
            SecretKey securekey = keyFactory.generateSecret(keySpec);
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance(DES);
            // 用密钥初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, secureRandom);
            byte[] bytes = cipher.doFinal(Base64Util.decode(data));
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
