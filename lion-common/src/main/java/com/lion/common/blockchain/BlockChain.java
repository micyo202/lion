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

import com.lion.common.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BlockChainDemo
 * 块链示例
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/04/08
 */
public class BlockChain {

    private static final List<Block> BLOCKCHAIN = new ArrayList<>();

    private static final int DIFFICULTY = 5;

    /**
     * 开采块链
     */
    public static String minedBlockChain(String data) {
        String hash;
        if (BLOCKCHAIN.isEmpty()) {
            hash = addBlock(new Block(data, "0"));
        } else {
            hash = addBlock(new Block(data, BLOCKCHAIN.get(BLOCKCHAIN.size() - 1).hash));
        }
        return hash;
    }

    /**
     * 解析块链
     */
    public static String decryptBlockchain(String blockHash) {
        if ("all".equals(blockHash)) {
            String blockchainJson = JsonUtil.obj2Json(BLOCKCHAIN);
            return blockchainJson;
        } else {
            List<Block> blockList = BLOCKCHAIN
                    .parallelStream()
                    .filter(b -> b.hash.equals(blockHash))
                    .collect(Collectors.toList());
            if (null != blockList && !blockList.isEmpty()) {
                String blockJson = JsonUtil.obj2Json(blockList);
                return blockJson;
            } else {
                return null;
            }
        }
    }

    /**
     * 检查区块链的完整性
     */
    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[DIFFICULTY]).replace('\0', '0');

        // 循环通过区块链来检查散列
        for (int i = 1; i < BLOCKCHAIN.size(); i++) {
            currentBlock = BLOCKCHAIN.get(i);
            previousBlock = BLOCKCHAIN.get(i - 1);
            // 比较注册Hash散列和计算哈希
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("当前的Hash散列不相等");
                return false;
            }
            // 比较以前的Hash散列和注册的以前的Hash散列
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("以前的Hash散列不相等");
                return false;
            }
            // 检查哈希是否已开采
            if (!currentBlock.hash.substring(0, DIFFICULTY).equals(hashTarget)) {
                System.out.println("当前块链还没有被开采");
                return false;
            }

        }
        return true;
    }

    private static String addBlock(Block block) {
        String hash = block.mineBlock(DIFFICULTY);
        BLOCKCHAIN.add(block);
        return hash;
    }
}
