package com.lion.common.blockchain;

import com.lion.common.util.DateUtil;
import com.lion.common.util.secure.SHAUtil;

/**
 * Block
 * 块
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2020/04/08
 * Copyright 2020 Yanzheng. All rights reserved.
 */
public class Block {

    public String hash;
    public String previousHash;
    private String data; // 区块链数据，基本的数据
    private long timestamp; // 时间戳
    private int nonce;

    // 区块链构造方法
    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timestamp = DateUtil.getTimestamp();
        this.hash = calculateHash(); // 确保hash值的来源
    }

    // 使用 sha256 算法让一个输入转变成256位的hash值
    public String calculateHash() {
        String calculatedhash = SHAUtil.encrypt256(previousHash +
                Long.toString(timestamp) +
                Integer.toString(nonce) +
                data);
        return calculatedhash;
    }

    // Increases nonce value until hash target is reached
    public String mineBlock(int difficulty) {
        String target = getDificultyString(difficulty); // Create a string with difficulty * "0"
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
        return hash;
    }

    // Returns difficulty string target, to compare to hash. eg difficulty of 5 will return "00000"
    private static String getDificultyString(int difficulty) {
        return new String(new char[difficulty]).replace('\0', '0');
    }

    public String getData() {
        return data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getNonce() {
        return nonce;
    }
}
