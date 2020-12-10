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
import java.util.Base64;
import java.util.HashMap;
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

    private RSAUtil() {}

    /**
     * 加密、解密方式
     */
    private static final String RSA = "RSA";

    /**
     * 密钥大小为96-1024位
     */
    private static final int KEY_SIZE = 1024;

    /**
     * 用于封装随机产生的公钥与私钥
     */
    public static final Map<RSAKey, String> KEY_MAP = new HashMap<>();

    /**
     * 随机生成密钥对（公钥、私钥）
     */
    public static void generatorPairKey() {
        try {
            // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(RSA);
            // 初始化密钥对生成器，密钥大小为96-1024位
            keyPairGen.initialize(KEY_SIZE, new SecureRandom());
            // 生成一个密钥对，保存在keyPair中
            KeyPair keyPair = keyPairGen.generateKeyPair();
            // 得到公钥
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            // 得到私钥
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

            String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            // 得到私钥字符串
            String privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());
            // 将公钥和私钥保存到Map
            // 0表示公钥
            KEY_MAP.put(RSAKey.PUBLIC, publicKeyString);
            // 1表示私钥
            KEY_MAP.put(RSAKey.PRIVATE, privateKeyString);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(), e);
        }

    }

    /**
     * RSA 加密
     *
     * @param text      明文
     * @param publicKey 公钥
     * @return 密文
     */
    public static String encrypt(String text, String publicKey) {
        if (StringUtils.isAnyBlank(text, publicKey)) {
            return null;
        }
        try {
            // base64编码的公钥
            byte[] decoded = Base64.getDecoder().decode(publicKey);
            RSAPublicKey rsaPublicKey = (RSAPublicKey) KeyFactory.getInstance(RSA).generatePublic(new X509EncodedKeySpec(decoded));
            // RSA加密
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(text.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * RSA 解密
     *
     * @param ciphertext 密文
     * @param privateKey 私钥
     * @return 明文
     */
    public static String decrypt(String ciphertext, String privateKey) {
        if (StringUtils.isAnyBlank(ciphertext, privateKey)) {
            return null;
        }
        try {
            //64位解码加密后的字符串
            final byte[] bytes = Base64.getDecoder().decode(ciphertext.getBytes(StandardCharsets.UTF_8));
            //base64编码的私钥
            byte[] decoded = Base64.getDecoder().decode(privateKey);
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
