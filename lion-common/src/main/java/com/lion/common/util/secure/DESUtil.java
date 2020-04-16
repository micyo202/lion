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
import java.security.SecureRandom;
import java.util.Base64;

/**
 * DESUtil
 * DES工具类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/10/16
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Slf4j
public class DESUtil {

    /**
     * 加密解密方式
     */
    private final static String DES = "DES";

    /**
     * 字符编码
     */
    private final static String ENCODEING = "UTF-8";

    /**
     * 秘钥
     */
    private final static String DEFAULT_KEY = "github.com/micyo";

    /**
     * 加密方法
     */
    public static String encrypt(String data) {
        return encrypt(data, DEFAULT_KEY);
    }

    public static String encrypt(String data, String key) {
        if (StringUtils.isEmpty(data) || StringUtils.isEmpty(key)) {
            return null;
        }
        try {
            // 生成一个可信任的随机数源
            SecureRandom secureRandom = new SecureRandom();
            // 从原始密钥数据创建DESKeySpec对象
            DESKeySpec keySpec = new DESKeySpec(key.getBytes(ENCODEING));
            // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
            SecretKey securekey = keyFactory.generateSecret(keySpec);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance(DES);
            // 用密钥初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, secureRandom);
            byte[] bytes = cipher.doFinal(data.getBytes(ENCODEING));
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }


    /**
     * 解密 后并用base64解码
     */
    public static String decrypt(String data) {
        return decrypt(data, DEFAULT_KEY);
    }

    public static String decrypt(String data, String key) {
        if (StringUtils.isEmpty(data) || StringUtils.isEmpty(key)) {
            return null;
        }
        try {

            // 生成一个可信任的随机数源
            SecureRandom secureRandom = new SecureRandom();
            // 从原始密钥数据创建DESKeySpec对象
            DESKeySpec keySpec = new DESKeySpec(key.getBytes(ENCODEING));
            // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
            SecretKey securekey = keyFactory.generateSecret(keySpec);
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance(DES);
            // 用密钥初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, secureRandom);
            byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(data.getBytes(ENCODEING)));
            return new String(bytes, ENCODEING);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

}
