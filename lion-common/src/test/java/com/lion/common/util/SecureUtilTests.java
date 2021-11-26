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

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.util.Map;

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
        String data = "https://github.com/micyo202";
        System.out.println("原文：" + data);

        String encryptAES = AESUtil.encrypt(data);
        String decryptAES = AESUtil.decrypt(encryptAES);
        System.out.println("AES加密：" + encryptAES);
        System.out.println("AES解密：" + decryptAES);

        byte[] encodeBase64 = Base64Util.encode(data);
        byte[] decodeBase64 = Base64Util.decode(encodeBase64);
        System.out.println("Base64加密byte：" + encodeBase64);
        System.out.println("Base64解密byte：" + decodeBase64);
        String encodeBase64Str = Base64Util.encodeStr(data);
        String decodeBase64Str = Base64Util.decodeStr(encodeBase64Str);
        System.out.println("Base64加密str：" + encodeBase64Str);
        System.out.println("Base64解密str：" + decodeBase64Str);

        String encryptBCrypt = BCryptUtil.encrypt(data);
        boolean verifyBCrypt = BCryptUtil.verify(data, encryptBCrypt);
        System.out.println("BCrypt加密：" + encryptBCrypt);
        System.out.println("BCrypt验证：" + verifyBCrypt);

        String encryptDES = DESUtil.encrypt(data);
        String decryptDES = DESUtil.decrypt(encryptDES);
        System.out.println("DES加密：" + encryptDES);
        System.out.println("DES解密：" + decryptDES);

        Map<ECDSAKey, String> ecdsaKeyStringMap = ECDSAUtil.generateKeyPairMap();
        System.out.println("ECDSA 公钥（public）：" + ecdsaKeyStringMap.get(ECDSAKey.PUBLIC));
        System.out.println("ECDSA 私钥（private）：" + ecdsaKeyStringMap.get(ECDSAKey.PRIVATE));
        KeyPair ecdsaKeyPair = ECDSAUtil.generateKeyPair();
        System.out.println("ECDSA keyPair：" + ecdsaKeyPair);
        String sign = ECDSAUtil.sign(ecdsaKeyPair.getPrivate(), data);
        System.out.println("ECDSA签名：" + sign);
        boolean ecdsaVerify = ECDSAUtil.verify(ecdsaKeyPair.getPublic(), data, sign);
        System.out.println("ECDSA验签：" + ecdsaVerify);

        String hexStr = HexUtil.bytes2Hex(data.getBytes(StandardCharsets.UTF_8));
        System.out.println("Hex str：" + hexStr);
        byte[] hexBytes = HexUtil.hex2Bytes(hexStr);
        System.out.println("Hex bytes：" + hexBytes);

        String encryptHmacMD5 = HmacUtil.encryptHmacMD5(data);
        String encryptHmacSHA1 = HmacUtil.encryptHmacSHA1(data);
        String encryptHmacSHA224 = HmacUtil.encryptHmacSHA224(data);
        String encryptHmacSHA256 = HmacUtil.encryptHmacSHA256(data, GeneratorUtil.getRandomKey(16));
        String encryptHmacSHA384 = HmacUtil.encryptHmacSHA384(data, GeneratorUtil.getRandomKey(16));
        String encryptHmacSHA512 = HmacUtil.encryptHmacSHA512(data, GeneratorUtil.getRandomKey(16));
        System.out.println("HmacMD5加密：" + encryptHmacMD5);
        System.out.println("HmacSHA1加密：" + encryptHmacSHA1);
        System.out.println("HmacSHA224加密：" + encryptHmacSHA224);
        System.out.println("HmacSHA256加密：" + encryptHmacSHA256);
        System.out.println("HmacSHA384加密：" + encryptHmacSHA384);
        System.out.println("HmacSHA512加密：" + encryptHmacSHA512);

        byte[] encryptMD5 = MD5Util.encrypt(data);
        System.out.println("MD5加密：" + encryptMD5);
        String encryptMD5Hex = MD5Util.encryptHex(data);
        System.out.println("MD5加密Hex：" + encryptMD5Hex);
        String encryptMD5Hex16 = MD5Util.encryptHex16(data);
        System.out.println("MD5加密Hex16：" + encryptMD5Hex16);

        // 生成 RSA 公钥、私钥
        Map<RSAKey, String> pairKeyMap = RSAUtil.generateKeyPairMap();
        System.out.println("RSA 公钥（public）为：" + pairKeyMap.get(RSAKey.PUBLIC));
        System.out.println("RSA 私钥（private）为：" + pairKeyMap.get(RSAKey.PRIVATE));
        KeyPair keyPair = RSAUtil.generateKeyPair();
        String encryptData = RSAUtil.encrypt(data, pairKeyMap.get(RSAKey.PUBLIC));
        System.out.println("RSA 公钥加密：" + encryptData);
        String decryptData = RSAUtil.decrypt(encryptData, pairKeyMap.get(RSAKey.PRIVATE));
        System.out.println("RSA 私钥解密：" + decryptData);

        String encryptRipeMD128 = RipeMDUtil.encrypt128(data);
        String encryptRipeMD160 = RipeMDUtil.encrypt160(data);
        String encryptRipeMD256 = RipeMDUtil.encrypt256(data);
        String encryptRipeMD320 = RipeMDUtil.encrypt320(data);
        System.out.println("RipeMD128加密：" + encryptRipeMD128);
        System.out.println("RipeMD160加密：" + encryptRipeMD160);
        System.out.println("RipeMD256加密：" + encryptRipeMD256);
        System.out.println("RipeMD320加密：" + encryptRipeMD320);

        String encryptSHA = SHAUtil.encrypt(data);
        String encryptSHA224 = SHAUtil.encrypt224(data);
        String encryptSHA256 = SHAUtil.encrypt256(data);
        String encryptSHA384 = SHAUtil.encrypt384(data);
        String encryptSHA512 = SHAUtil.encrypt512(data);
        System.out.println("SHA加密：" + encryptSHA);
        System.out.println("SHA224加密：" + encryptSHA224);
        System.out.println("SHA256加密：" + encryptSHA256);
        System.out.println("SHA384加密：" + encryptSHA384);
        System.out.println("SHA512加密：" + encryptSHA512);
    }

}
