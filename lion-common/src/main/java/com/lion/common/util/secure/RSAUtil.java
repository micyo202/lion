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
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/10/16
 * Copyright 2019 Yanzheng. All rights reserved.
 */
@Slf4j
public class RSAUtil {

    /**
     * 加密、解密方式
     */
    private static final String RSA = "RSA";

    /**
     * 字符编码
     */
    private static final String ENCODEING = "UTF-8";

    /**
     * 密钥大小为96-1024位
     */
    private static final int KEY_SIZE = 1024;

    /**
     * 用于封装随机产生的公钥与私钥
     */
    public static Map<RSAKey, String> keyMap = new HashMap<>();

    /**
     * 随机生成密钥对
     *
     * @throws NoSuchAlgorithmException
     */
    public static void generatorPairKey() {
        try {
            // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(RSA);
            // 初始化密钥对生成器，密钥大小为96-1024位
            keyPairGen.initialize(KEY_SIZE, new SecureRandom());
            // 生成一个密钥对，保存在keyPair中
            KeyPair keyPair = keyPairGen.generateKeyPair();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥

            String publicKeyString = java.util.Base64.getEncoder().encodeToString(publicKey.getEncoded());
            // 得到私钥字符串
            String privateKeyString = java.util.Base64.getEncoder().encodeToString(privateKey.getEncoded());
            // 将公钥和私钥保存到Map
            keyMap.put(RSAKey.PUBLIC, publicKeyString);     // 0表示公钥
            keyMap.put(RSAKey.PRIVATE, privateKeyString);    // 1表示私钥
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(), e);
        }

    }

    public static String encrypt(String data, String publicKey) {
        if (StringUtils.isEmpty(data) || StringUtils.isEmpty(publicKey)) {
            return null;
        }
        try {
            // base64编码的公钥
            byte[] decoded = java.util.Base64.getDecoder().decode(publicKey);
            RSAPublicKey rsaPublicKey = (RSAPublicKey) KeyFactory.getInstance(RSA).generatePublic(new X509EncodedKeySpec(decoded));
            // RSA加密
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);
            final String result = java.util.Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes(ENCODEING)));
            return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static String decrypt(String data, String privateKey) {
        if (StringUtils.isEmpty(data) || StringUtils.isEmpty(privateKey)) {
            return null;
        }
        try {
            //64位解码加密后的字符串
            final byte[] bytes = java.util.Base64.getDecoder().decode(data.getBytes(ENCODEING));
            //base64编码的私钥
            byte[] decoded = Base64.getDecoder().decode(privateKey);
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) KeyFactory.getInstance(RSA).generatePrivate(new PKCS8EncodedKeySpec(decoded));
            //RSA解密
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey);
            String result = new String(cipher.doFinal(bytes));
            return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static void main(String[] args) {
        //生成公钥和私钥（公钥加密、私钥解密）
        generatorPairKey();
        //加密字符串
        String data = "Yanzheng https://github.com/micyo202";
        System.out.println("随机生成的公钥（public）为：" + keyMap.get(RSAKey.PUBLIC));
        System.out.println("随机生成的私钥（private）为：" + keyMap.get(RSAKey.PRIVATE));
        System.out.println("原字符串内容:" + data);
        String encryptData = encrypt(data, keyMap.get(RSAKey.PUBLIC));
        System.out.println("公钥加密（encrypt）后的字符串为：" + encryptData);
        String decryptData = decrypt(encryptData, keyMap.get(RSAKey.PRIVATE));
        System.out.println("私钥解密（decrypt）后的字符串为：" + decryptData);
    }

}
