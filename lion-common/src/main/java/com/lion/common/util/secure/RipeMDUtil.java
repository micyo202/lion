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
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.Security;

/**
 * RipeMDUtil
 * RipeMD工具类
 * 依赖 org.bouncycastle:bcprov-jdk15on 库
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2021/9/30
 */
@Slf4j
public class RipeMDUtil {

    private RipeMDUtil() {
    }

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * 加密方式
     */
    private static final String RIPEMD_128 = "RipeMD128";
    private static final String RIPEMD_160 = "RipeMD160";
    private static final String RIPEMD_256 = "RipeMD256";
    private static final String RIPEMD_320 = "RipeMD320";

    /**
     * Ripemd128 通用加密
     *
     * @param data 明文
     * @return 密文
     */
    public static String encrypt128(String data) {
        return encrypt(data, RIPEMD_128);
    }

    /**
     * Ripemd160 通用加密
     *
     * @param data 明文
     * @return 密文
     */
    public static String encrypt160(String data) {
        return encrypt(data, RIPEMD_160);
    }

    /**
     * Ripemd256 通用加密
     *
     * @param data 明文
     * @return 密文
     */
    public static String encrypt256(String data) {
        return encrypt(data, RIPEMD_256);
    }

    /**
     * Ripemd320 通用加密
     *
     * @param data 明文
     * @return 密文
     */
    public static String encrypt320(String data) {
        return encrypt(data, RIPEMD_320);
    }

    /**
     * Ripemd 通用加密
     *
     * @param data 明文
     * @return 密文
     */
    private static String encrypt(String data, String algorithm) {
        if (StringUtils.isBlank(data)) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(data.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = messageDigest.digest();
            return HexUtil.bytes2Hex(bytes);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
