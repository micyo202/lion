package com.lion.common.util.secure;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * HmacUtil
 * Hmac工具类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/10/22
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Slf4j
public class HmacUtil {

    /**
     * 加密、解密方式：SHA-1、SHA-224、SHA-256、SHA-384、SHA-512
     */
    private static final String HMAC_MD5 = "HmacMD5";
    private static final String HMAC_SHA1 = "HmacSHA1";
    private static final String HMAC_SHA224 = "HmacSHA224";
    private static final String HMAC_SHA256 = "HmacSHA256";
    private static final String HMAC_SHA384 = "HmacSHA384";
    private static final String HMAC_SHA512 = "HmacSHA512";

    /**
     * 字符编码
     */
    private static final String ENCODEING = "UTF-8";

    /**
     * HMAC MD5 加密
     */
    public static String encryptHmacMD5(String data, String key) {
        return encryptHmac(data, key, HMAC_MD5);
    }

    /**
     * HMAC SHA1 加密
     */
    public static String encryptHmacSHA1(String data, String key) {
        return encryptHmac(data, key, HMAC_SHA1);
    }

    /**
     * HMAC SHA224 加密
     */
    public static String encryptHmacSHA224(String data, String key) {
        return encryptHmac(data, key, HMAC_SHA224);
    }

    /**
     * HMAC SHA256 加密
     */
    public static String encryptHmacSHA256(String data, String key) {
        return encryptHmac(data, key, HMAC_SHA256);
    }

    /**
     * HMAC SHA384 加密
     */
    public static String encryptHmacSHA384(String data, String key) {
        return encryptHmac(data, key, HMAC_SHA384);
    }

    /**
     * HMAC SHA521 加密
     */
    public static String encryptHmacSHA512(String data, String key) {
        return encryptHmac(data, key, HMAC_SHA512);
    }

    /**
     * 基础HMAC算法
     */
    private static String encryptHmac(String data, String key, String type) {
        if (StringUtils.isEmpty(data) || StringUtils.isEmpty(key) || StringUtils.isEmpty(type)) {
            return null;
        }
        try {
            // byte[] key = getHmacKey(type);   // 随机生成秘钥
            byte[] keyBytes = key.getBytes(ENCODEING);
            byte[] dataBytes = data.getBytes(ENCODEING);

            // 1、还原密钥
            SecretKey secretKey = new SecretKeySpec(keyBytes, type);
            // 2、创建MAC对象
            Mac mac = Mac.getInstance(type);
            // 3、设置密钥
            mac.init(secretKey);
            // 4、数据加密
            byte[] bytes = mac.doFinal(dataBytes);
            // 5、生成数据
            String encodeHex = encodeHex(bytes, true);
            return encodeHex;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * HMAC Key
     */
    private static byte[] getHmacKey(String type) {
        try {
            // 1、创建密钥生成器
            KeyGenerator keyGenerator = KeyGenerator.getInstance(type);
            // 2、产生密钥
            SecretKey secretKey = keyGenerator.generateKey();
            // 3、获取密钥
            byte[] encoded = secretKey.getEncoded();
            return encoded;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 数据转16进制编码
     */
    private static String encodeHex(final byte[] data, final boolean toLowerCase) {
        final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        final char[] toDigits = toLowerCase ? DIGITS_LOWER : DIGITS_UPPER;
        final int l = data.length;
        final char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
        return new String(out);
    }

}