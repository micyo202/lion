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

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.EnumMap;
import java.util.Map;

/**
 * RSAUtil
 * RSA工具类
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/10/16
 */
@Slf4j
public class RSAUtil {

    private RSAUtil() {
    }

    /**
     * 加密、解密方式
     */
    private static final String RSA = "RSA";

    /**
     * 密钥大小为96-1024位
     */
    private static final int KEY_SIZE = 1024;

    /**
     * 随机生成密钥对（公钥、私钥）
     */
    public static KeyPair generateKeyPair() {
        try {
            // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(RSA);
            // 初始化密钥对生成器，密钥大小为 96-1024 位
            keyPairGen.initialize(KEY_SIZE, new SecureRandom());
            // 生成一个密钥对，保存在keyPair中
            KeyPair keyPair = keyPairGen.generateKeyPair();
            return keyPair;
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 随机生成密钥对（公钥、私钥）
     */
    public static Map<RSAKey, String> generateKeyPairMap() {
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = generateKeyPair();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        String publicKeyString = Base64Util.encodeStr(publicKey.getEncoded());
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        String privateKeyString = Base64Util.encodeStr(privateKey.getEncoded());
        // 将随机生成的公钥和私钥保存到Map
        Map<RSAKey, String> pairKeyMap = new EnumMap<>(RSAKey.class);
        // 0、表示公钥
        pairKeyMap.put(RSAKey.PUBLIC, publicKeyString);
        // 1、表示私钥
        pairKeyMap.put(RSAKey.PRIVATE, privateKeyString);
        return pairKeyMap;
    }

    /**
     * RSA 加密
     *
     * @param data      明文
     * @param publicKey 公钥
     * @return 密文
     */
    public static String encrypt(String data, String publicKey) {
        if (StringUtils.isEmpty(data) || StringUtils.isEmpty(publicKey)) {
            return null;
        }
        try {
            // base64编码的公钥
            byte[] decoded = Base64Util.decode(publicKey);
            RSAPublicKey rsaPublicKey = (RSAPublicKey) KeyFactory.getInstance(RSA).generatePublic(new X509EncodedKeySpec(decoded));
            // RSA加密
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
            return Base64Util.encodeStr(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * RSA 解密
     *
     * @param data       密文
     * @param privateKey 私钥
     * @return 明文
     */
    public static String decrypt(String data, String privateKey) {
        if (StringUtils.isEmpty(data) || StringUtils.isEmpty(privateKey)) {
            return null;
        }
        try {
            //64位解码加密后的字符串
            final byte[] bytes = Base64Util.decode(data.getBytes(StandardCharsets.UTF_8));
            //base64编码的私钥
            byte[] decoded = Base64Util.decode(privateKey);
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) KeyFactory.getInstance(RSA).generatePrivate(new PKCS8EncodedKeySpec(decoded));
            //RSA解密
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
            return new String(cipher.doFinal(bytes));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
