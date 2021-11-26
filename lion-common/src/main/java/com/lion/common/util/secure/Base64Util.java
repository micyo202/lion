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

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Base64Util
 * Base64工具类
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/10/16
 */
@Slf4j
public class Base64Util {

    private Base64Util() {
    }

    /**
     * Base64 编码
     *
     * @param data 字符串
     * @return base64编码字符串
     */
    public static String encodeStr(String data) {
        if (StringUtils.isEmpty(data)) {
            return null;
        }
        return encodeStr(data.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Base64 编码
     *
     * @param bytes 字节
     * @return base64编码字符串
     */
    public static String encodeStr(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * Base64 编码
     *
     * @param data 字符串
     * @return base64编码字节
     */
    public static byte[] encode(String data) {
        if (StringUtils.isEmpty(data)) {
            return null;
        }
        return encode(data.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Base64 编码
     *
     * @param bytes 字节
     * @return base64编码字节
     */
    public static byte[] encode(byte[] bytes) {
        return Base64.getEncoder().encode(bytes);
    }

    /**
     * Base64 解码
     *
     * @param base64 base64编码字符串
     * @return 字符串
     */
    public static String decodeStr(String base64) {
        if (StringUtils.isEmpty(base64)) {
            return null;
        }
        return new String(Base64.getDecoder().decode(base64), StandardCharsets.UTF_8);
    }

    /**
     * Base64 解码
     *
     * @param bytes base64编码字节
     * @return 字符串
     */
    public static String decodeStr(byte[] bytes) {
        return new String(Base64.getDecoder().decode(bytes), StandardCharsets.UTF_8);
    }

    /**
     * Base64 解码
     *
     * @param base64 base64编码字符串
     * @return 字节
     */
    public static byte[] decode(String base64) {
        return Base64.getDecoder().decode(base64);
    }

    /**
     * Base64 解码
     *
     * @param bytes base64编码字节
     * @return 字节
     */
    public static byte[] decode(byte[] bytes) {
        return Base64.getDecoder().decode(bytes);
    }
}
