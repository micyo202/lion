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

import com.lion.common.util.secure.*;
import org.junit.jupiter.api.Test;

/**
 * SecureUtil
 * TODO
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/5/19
 */
class SecureUtilTests {

    @Test
    void testSecure() {
        String text = "https://github.com/micyo202";
        System.out.println("原文：" + text);
        String encryptAES = AESUtil.encrypt(text);
        String decryptAES = AESUtil.decrypt(encryptAES);
        System.out.println("AES加密：" + encryptAES);
        System.out.println("AES解密：" + decryptAES);

        String encodeBase64 = Base64Util.encode(text);
        String decodeBase64 = Base64Util.decode(encodeBase64);
        System.out.println("Base64加密：" + encodeBase64);
        System.out.println("Base64解密：" + decodeBase64);

        String encrypt = BCryptUtil.encrypt(text);
        Boolean verify = BCryptUtil.verify(text, encrypt);
        System.out.println("BCrypt加密后：" + encrypt);
        System.out.println("BCrypt验证结果：" + verify);

        String encryptDES = DESUtil.encrypt(text);
        String decryptDES = DESUtil.decrypt(encryptDES);
        System.out.println("DES加密：" + encryptDES);
        System.out.println("DES解密：" + decryptDES);

        String encryptHmacMD5 = HmacUtil.encryptHmacMD5(text);
        String encryptHmacSHA1 = HmacUtil.encryptHmacSHA1(text);
        String encryptHmacSHA224 = HmacUtil.encryptHmacSHA224(text);
        String encryptHmacSHA256 = HmacUtil.encryptHmacSHA256(text, GeneratorUtil.getRandomKey(16));
        String encryptHmacSHA384 = HmacUtil.encryptHmacSHA384(text, GeneratorUtil.getRandomKey(16));
        String encryptHmacSHA512 = HmacUtil.encryptHmacSHA512(text, GeneratorUtil.getRandomKey(16));
        System.out.println("HmacMD5加密：" + encryptHmacMD5);
        System.out.println("HmacSHA1加密：" + encryptHmacSHA1);
        System.out.println("HmacSHA224加密：" + encryptHmacSHA224);
        System.out.println("HmacSHA256加密：" + encryptHmacSHA256);
        System.out.println("HmacSHA384加密：" + encryptHmacSHA384);
        System.out.println("HmacSHA512加密：" + encryptHmacSHA512);

        String encryptMD5 = MD5Util.encrypt(text);
        System.out.println("MD5加密：" + encryptMD5);

        // 生成 RSA 公钥、私钥
        RSAUtil.generatorPairKey();
        System.out.println("随机生成的 RSA 公钥（public）为：" + RSAUtil.KEY_MAP.get(RSAKey.PUBLIC));
        System.out.println("随机生成的 RSA 私钥（private）为：" + RSAUtil.KEY_MAP.get(RSAKey.PRIVATE));
        String encryptData = RSAUtil.encrypt(text, RSAUtil.KEY_MAP.get(RSAKey.PUBLIC));
        System.out.println("RSA 公钥加密：" + encryptData);
        String decryptData = RSAUtil.decrypt(encryptData, RSAUtil.KEY_MAP.get(RSAKey.PRIVATE));
        System.out.println("RSA 私钥解密：" + decryptData);

        String encryptSHA = SHAUtil.encrypt(text);
        String encryptSHA224 = SHAUtil.encrypt224(text);
        String encryptSHA256 = SHAUtil.encrypt256(text);
        String encryptSHA384 = SHAUtil.encrypt384(text);
        String encryptSHA512 = SHAUtil.encrypt512(text);
        System.out.println("SHA加密：" + encryptSHA);
        System.out.println("SHA224加密：" + encryptSHA224);
        System.out.println("SHA256加密：" + encryptSHA256);
        System.out.println("SHA384加密：" + encryptSHA384);
        System.out.println("SHA512加密：" + encryptSHA512);
    }

}
