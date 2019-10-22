package com.lion.common.util.secure;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * AESUtil
 * AES工具类
 *
 * @author Yanzheng
 * @date 2019/10/16
 * Copyright 2019 Yanzheng. All rights reserved.
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
    private static final String DEFAULT_KEY = "github.com/micyo";

    /**
     * 指定加密的算法、工作模式和填充方式
     */
    private static final String CIPHER = "AES/CBC/PKCS5Padding";

    /**
     * 加密
     */
    public static String encrypt(String data) {
        return encrypt(data, DEFAULT_KEY);
    }

    public static String encrypt(String data, String key) {
        if (StringUtils.isEmpty(data) || StringUtils.isEmpty(key) || 16 != key.length()) {
            return null;
        }
        try {
            byte[] byteContent = data.getBytes(ENCODEING);
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
     * 解密
     */
    public static String decrypt(String data) {
        return decrypt(data, DEFAULT_KEY);
    }

    public static String decrypt(String data, String key) {
        if (StringUtils.isEmpty(data) || StringUtils.isEmpty(key) || 16 != key.length()) {
            return null;
        }
        try {
            byte[] encryptedBytes = Base64.getDecoder().decode(data);
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
