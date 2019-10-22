package com.lion.common.util.secure;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.security.MessageDigest;

/**
 * SHAUtil
 * SHA哈希散列工具类
 *
 * @author Yanzheng
 * @date 2019/10/21
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Slf4j
public class SHAUtil {

    /**
     * 加密、解密方式：SHA-1、SHA-224、SHA-256、SHA-384、SHA-512
     */
    private static final String SHA_1 = "SHA-1";
    private static final String SHA_224 = "SHA-224";
    private static final String SHA_256 = "SHA-256";
    private static final String SHA_384 = "SHA-384";
    private static final String SHA_512 = "SHA-512";

    /**
     * 字符编码
     */
    private static final String ENCODEING = "UTF-8";

    public static String encrypt(String data) {
        return encrypt(data, SHA_1);
    }

    public static String encrypt224(String data) {
        return encrypt(data, SHA_224);
    }

    public static String encrypt256(String data) {
        return encrypt(data, SHA_256);
    }

    public static String encrypt384(String data) {
        return encrypt(data, SHA_384);
    }

    public static String encrypt512(String data) {
        return encrypt(data, SHA_512);
    }

    private static String encrypt(String data, String algorithm) {
        if (StringUtils.isEmpty(data)) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(data.getBytes(ENCODEING));
            byte[] bytes = messageDigest.digest();
            return bytes2Str(bytes);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    private static String bytes2Str(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String str;
        for (int i = 0; i < bytes.length; i++) {
            str = Integer.toHexString(bytes[i] & 0xFF);
            if (str.length() == 1) {
                // 1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

}
