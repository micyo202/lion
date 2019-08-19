package com.lion.blockchain.block;

import com.lion.blockchain.util.StringUtil;

/**
 * Block
 * TODO
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/01/03
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public class Block {
    public String hash;
    public String previousHash;
    private String data; // 区块链数据，基本的数据，只包含了msg
    private long timeStamp; // 时间戳
    private int nonce;

    // 区块链构造方法
    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = System.currentTimeMillis();

        this.hash = calculateHash(); // 确保hash值的来源
    }

    // 使用 sha256 算法让一个输入转变成256位的hash值
    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        data
        );
        return calculatedhash;
    }

    //Increases nonce value until hash target is reached.
    public void mineBlock(int difficulty) {
        String target = StringUtil.getDificultyString(difficulty); //Create a string with difficulty * "0"
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

    public String getData() {
        return data;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public int getNonce() {
        return nonce;
    }
}
