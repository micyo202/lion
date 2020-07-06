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

import org.junit.jupiter.api.Test;

/**
 * BlockChainTests
 * TODO
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/6/17
 */
public class BlockChainTests {

    @Test
    public void testBlockChain() {
        System.out.println("创建第1个块...");
        String blockHash_1 = BlockChain.minedBlockChain("Hello 我是第一个块");

        System.out.println("创建第2个块...");
        String blockHash_2 = BlockChain.minedBlockChain("Hi 我是第二个块");

        System.out.println("创建第3个块...");
        String blockHash_3 = BlockChain.minedBlockChain("Hey 我是第三个块");

        System.out.println("\n块链是否有效: isChainValid() = " + BlockChain.isChainValid());

        System.out.println("第1个块内容...");
        System.out.println(BlockChain.decryptBlockchain(blockHash_1));

        System.out.println("第2个块内容...");
        System.out.println(BlockChain.decryptBlockchain(blockHash_2));

        System.out.println("第3个块内容...");
        System.out.println(BlockChain.decryptBlockchain(blockHash_3));
    }

}
