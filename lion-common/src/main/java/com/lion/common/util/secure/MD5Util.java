package com.lion.common.util.secure;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * MD5Util
 * MD5工具类
 *
 * @author Yanzheng
 * @date 2019/10/16
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Slf4j
public class MD5Util {

    /**
     * 加密、解密方式
     */
    private static final String MD5 = "MD5";

    /**
     * 字符编码
     */
    private static final String ENCODEING = "UTF-8";

    /**
     * 加密方法
     */
    public static String encrypt(String data) {
        if (StringUtils.isEmpty(data)) {
            return null;
        }
        /*
        char hexs[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] bytes = data.getBytes(ENCODEING);
            MessageDigest messageDigest = MessageDigest.getInstance(MD5);
            messageDigest.update(bytes);
            byte[] md = messageDigest.digest();
            int j = md.length;
            char[] chars = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                chars[k++] = hexs[byte0 >>> 4 & 0xf];
                chars[k++] = hexs[byte0 & 0xf];
            }
            return String.valueOf(chars);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
        */
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] dataBytes = data.getBytes(ENCODEING);
            messageDigest.update(dataBytes);
            return new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

}
