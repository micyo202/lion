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

import java.io.UnsupportedEncodingException;
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

    /**
     * 字符编码
     */
    private static final String ENCODEING = "UTF-8";

    public static String encode(String text) {
        if (StringUtils.isEmpty(text)) {
            return null;
        }
        try {
            return Base64.getEncoder().encodeToString(text.getBytes(ENCODEING));
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static String decode(String ciphertext) {
        if (StringUtils.isEmpty(ciphertext)) {
            return null;
        }
        try {
            final byte[] decode = Base64.getDecoder().decode(ciphertext);
            return new String(decode, ENCODEING);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

}
