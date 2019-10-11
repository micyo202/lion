package com.lion.blockchain.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.security.MessageDigest;

/**
 * StringUtil
 * TODO
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/01/03
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public class StringUtil {

    // Applies Sha256 to a string and returns the result.
    public static String applySha256(String input) {

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            //Applies sha256 to our input,
            byte[] hash = digest.digest(input.getBytes("UTF-8"));

            // This will contain hash as hexidecimal
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Short hand helper to turn Object into a json string
    public static String getJson(Object obj) {
        // 使用 Gson 解析
        // return new GsonBuilder().setPrettyPrinting().create().toJson(obj);
        try {
            // 使用 JackSon 解析
            //return new ObjectMapper().writeValueAsString(obj); // 普通输出
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(obj);// 美化（格式化）输出
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Returns difficulty string target, to compare to hash. eg difficulty of 5 will return "00000"
    public static String getDificultyString(int difficulty) {
        return new String(new char[difficulty]).replace('\0', '0');
    }
}
