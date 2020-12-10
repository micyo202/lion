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
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

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
@Slf4j
public class BlockChain {

    private BlockChain() {}

    private static final List<Block> BLOCK_CHAIN = new ArrayList<>();

    private static final int DIFFICULTY = 5;

    /**
     * 开采块链
     */
    public static String minedBlockChain(String data) {
        String hash;
        if (BLOCK_CHAIN.isEmpty()) {
            hash = addBlock(new Block(data, "0"));
        } else {
            hash = addBlock(new Block(data, BLOCK_CHAIN.get(BLOCK_CHAIN.size() - 1).hash));
        }
        return hash;
    }

    /**
     * 解析块链
     */
    public static String decryptBlockchain(String blockHash) {
        if ("ALL".equalsIgnoreCase(blockHash)) {
            return JsonUtil.obj2Json(BLOCK_CHAIN);
        } else {
            List<Block> blockList = BLOCK_CHAIN
                    .parallelStream()
                    .filter(b -> b.hash.equals(blockHash))
                    .collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(blockList)) {
                return JsonUtil.obj2Json(blockList);
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
        for (int i = 1; i < BLOCK_CHAIN.size(); i++) {
            currentBlock = BLOCK_CHAIN.get(i);
            previousBlock = BLOCK_CHAIN.get(i - 1);
            // 比较注册Hash散列和计算哈希
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                log.warn("当前的Hash散列不相等");
                return false;
            }
            // 比较以前的Hash散列和注册的以前的Hash散列
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                log.warn("以前的Hash散列不相等");
                return false;
            }
            // 检查哈希是否已开采
            if (!currentBlock.hash.substring(0, DIFFICULTY).equals(hashTarget)) {
                log.warn("当前块链还没有被开采");
                return false;
            }

        }
        return true;
    }

    private static String addBlock(Block block) {
        String hash = block.mineBlock(DIFFICULTY);
        BLOCK_CHAIN.add(block);
        return hash;
    }
}
