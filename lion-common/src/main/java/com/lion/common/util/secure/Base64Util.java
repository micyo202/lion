package com.lion.common.util.secure;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Base64Util
 * Base64工具类
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/10/16
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Slf4j
public class Base64Util {

    /**
     * 字符编码
     */
    private static final String ENCODEING = "UTF-8";

    public static String encode(String data) {
        if (StringUtils.isEmpty(data)) {
            return null;
        }
        try {
            return Base64.getEncoder().encodeToString(data.getBytes(ENCODEING));
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static String decode(String data) {
        if (StringUtils.isEmpty(data)) {
            return null;
        }
        try {
            final byte[] decode = Base64.getDecoder().decode(data);
            return new String(decode, ENCODEING);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

}
