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
package com.lion.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * HexUtil
 * 16进制字符串转换工具
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2021/10/8
 */
public class HexUtil {

    private HexUtil() {
    }


    /**
     * 字节转16进制字符串
     *
     * @param bytes 字节
     */
    public static String bytes2Hex(byte[] bytes) {

        if (null == bytes || bytes.length <= 0) {
            return null;
        }

        // 方法一：把 byte[] 转为 char[]，再转为字符串
        /*
        char[] chars = new char[bytes.length * 2];// 每个byte对应两个字符
        final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        for (int i = 0, j = 0; i < bytes.length; i++) {
            // 先存byte的高4位
            chars[j++] = hexDigits[bytes[i] >> 4 & 0x0f];
            // 再存byte的低4位
            chars[j++] = hexDigits[bytes[i] & 0x0f];
        }
        return new String(chars);
        */

        // 方法二：把 byte[] 转为 int 类型，再转为字符串
        StringBuilder stringBuilder = new StringBuilder();
        String str;
        for (int i = 0; i < bytes.length; i++) {
            str = Integer.toHexString(bytes[i] & 0xFF);
            if (str.length() == 1) {
                // 1得到一位的进行补0操作
                stringBuilder.append("0");
            }
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

    /**
     * 16进制字符串转字节
     *
     * @param hexStr 16进制字符串
     */
    public static byte[] hex2Bytes(String hexStr) {

        if (StringUtils.isBlank(hexStr)) {
            return null;
        }

        int length = hexStr.length() / 2;
        char[] hexChars = hexStr.toCharArray();
        byte[] bytes = new byte[length];
        String hexDigits = "0123456789abcdef";
        for (int i = 0; i < length; i++) {
            // 两个字符对应一个byte
            int pos = i * 2;
            int h = hexDigits.indexOf(hexChars[pos]) << 4;
            int l = hexDigits.indexOf(hexChars[pos + 1]);
            // 非16进制字符
            if (l == -1) {
                return null;
            }
            bytes[i] = (byte) (h | l);
        }
        return bytes;
    }

}
