package com.lion.common.blockchain;

import com.lion.common.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BlockChainDemo
 * 块链示例
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2020/04/08
 * Copyright 2020 Yanzheng. All rights reserved.
 */
public class BlockChain {

    private static List<Block> blockchain = new ArrayList<>();
    private static int difficulty = 5;

    /**
     * 开采块链
     */
    public static String minedBlockChain(String data) {
        String hash;
        if (blockchain.isEmpty()) {
            hash = addBlock(new Block(data, "0"));
        } else {
            hash = addBlock(new Block(data, blockchain.get(blockchain.size() - 1).hash));
        }
        return hash;
    }

    /**
     * 解析块链
     */
    public static String decryptBlockchain(String blockHash) {
        if ("all".equals(blockHash)) {
            String blockchainJson = JsonUtil.jsonObj2Str(blockchain);
            return blockchainJson;
        } else {
            List<Block> blockList = blockchain
                    .parallelStream()
                    .filter(b -> b.hash.equals(blockHash))
                    .collect(Collectors.toList());
            if (null != blockList && !blockList.isEmpty()) {
                String blockJson = JsonUtil.jsonObj2Str(blockList);
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
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        // 循环通过区块链来检查散列
        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
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
            if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.out.println("当前块链还没有被开采");
                return false;
            }

        }
        return true;
    }

    private static String addBlock(Block block) {
        String hash = block.mineBlock(difficulty);
        blockchain.add(block);
        return hash;
    }

    public static void main(String[] args) {
        System.out.println("创建第1个块...");
        addBlock(new Block("Hello 我是第一个块", "0"));

        System.out.println("创建第2个块...");
        addBlock(new Block("Hi 我是第二个块", blockchain.get(blockchain.size() - 1).hash));

        System.out.println("创建第3个块...");
        addBlock(new Block("Hey 我是第三个块", blockchain.get(blockchain.size() - 1).hash));

        System.out.println("\n块链:" + blockchain);

        System.out.println("\n块链是否有效: isChainValid() = " + isChainValid());

        String blockchainJson = JsonUtil.jsonObj2Str(blockchain);
        System.out.println("\n块链内容: ");
        System.out.println(blockchainJson);
    }
}
