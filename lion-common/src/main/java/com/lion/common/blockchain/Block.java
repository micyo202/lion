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
package com.lion.common.blockchain;

import com.lion.common.util.DateUtil;
import com.lion.common.util.secure.SHAUtil;

/**
 * Block
 * 块
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/04/08
 */
public class Block {

    public String hash;
    public String previousHash;

    /**
     * 区块链数据，基本的数据
     */
    private String data;
    private long timestamp;
    private int nonce;

    /**
     * 区块链构造方法
     */
    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timestamp = DateUtil.getTimestamp();
        // 确保hash值的来源
        this.hash = calculateHash();
    }

    /**
     * 使用 sha256 算法让一个输入转变成256位的hash值
     */
    public String calculateHash() {
        String calculatedhash = SHAUtil.encrypt256(previousHash +
                Long.toString(timestamp) +
                Integer.toString(nonce) +
                data);
        return calculatedhash;
    }

    /**
     * Increases nonce value until hash target is reached
     */
    public String mineBlock(int difficulty) {
        // Create a string with difficulty * "0"
        String target = getDificultyString(difficulty);
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
        return hash;
    }

    /**
     * Returns difficulty string target, to compare to hash. eg difficulty of 5 will return "00000"
     */
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
