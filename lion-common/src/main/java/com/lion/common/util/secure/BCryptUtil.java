package com.lion.common.util.secure;

import jodd.crypt.BCrypt;
import org.apache.commons.lang3.StringUtils;

/**
 * BCryptUtil
 * BCrypt工具类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/10/21
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public class BCryptUtil {

    public static String encrypt(String password) {
        if (StringUtils.isEmpty(password)) {
            return null;
        }
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean verify(String candidate, String encrypt) {
        if (StringUtils.isEmpty(candidate) || StringUtils.isEmpty(encrypt)) {
            return false;
        }
        return BCrypt.checkpw(candidate, encrypt);
    }

    public static void main(String[] args) {
        String password = "Yanzheng -> github.com/micyo202";
        String encrypt = BCryptUtil.encrypt(password);
        boolean verify = BCryptUtil.verify(password, encrypt);
        System.out.println("原文：" + password);
        System.out.println("加密后：" + encrypt);
        System.out.println("验证结果：" + verify);
    }

}
