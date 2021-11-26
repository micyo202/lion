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
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.EnumMap;
import java.util.Map;

/**
 * ECDSAUtil
 * ECDSA椭圆曲线签名算法工具类
 * 依赖 org.bouncycastle:bcprov-jdk15on 库
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2021/10/8
 */
@Slf4j
public class ECDSAUtil {

    private static final String ALGORITHM = "ECDSA";
    private static final String PROVIDER = "BC";
    /**
     * secp256k1
     * secp256r1
     * secp384r1
     * prime192v1
     * prime256v1
     */
    private static final String STD_NAME = "secp256k1";

    private ECDSAUtil() {
    }

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * 随机生成密钥对（公钥、私钥）
     */
    public static KeyPair generateKeyPair() {
        try {
            // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM, PROVIDER);
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec ecGenParameterSpec = new ECGenParameterSpec(STD_NAME);
            keyPairGen.initialize(ecGenParameterSpec, secureRandom);
            return keyPairGen.generateKeyPair();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 随机生成密钥对（公钥、私钥）
     */
    public static Map<ECDSAKey, String> generateKeyPairMap() {
        try {
            KeyPair keyPair = generateKeyPair();
            // 获取公钥、私钥
            String publicKey = Base64Util.encodeStr(keyPair.getPublic().getEncoded());
            String privateKey = Base64Util.encodeStr(keyPair.getPrivate().getEncoded());
            // 将随机生成的公钥和私钥保存到Map
            Map<ECDSAKey, String> keyPairMap = new EnumMap<>(ECDSAKey.class);
            // 0、表示公钥
            keyPairMap.put(ECDSAKey.PUBLIC, publicKey);
            // 1、表示私钥
            keyPairMap.put(ECDSAKey.PRIVATE, privateKey);
            return keyPairMap;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 私钥签名
     *
     * @param privateKey 私钥
     * @param data       明文
     */
    public static String sign(PrivateKey privateKey, String data) {
        try {
            Signature ecdsa = Signature.getInstance(ALGORITHM, PROVIDER);
            ecdsa.initSign(privateKey);
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            ecdsa.update(dataBytes);
            byte[] sign = ecdsa.sign();
            return HexUtil.bytes2Hex(sign);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 校验方法
     *
     * @param publicKey 公钥
     * @param data      明文
     * @param signature 私钥签名
     * @return
     */
    public static boolean verify(PublicKey publicKey, String data, String signature) {
        try {
            Signature ecdsaVerify = Signature.getInstance(ALGORITHM, PROVIDER);
            ecdsaVerify.initVerify(publicKey);
            ecdsaVerify.update(data.getBytes(StandardCharsets.UTF_8));
            return ecdsaVerify.verify(HexUtil.hex2Bytes(signature));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }
}
