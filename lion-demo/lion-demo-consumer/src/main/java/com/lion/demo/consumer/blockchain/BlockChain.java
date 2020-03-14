package com.lion.demo.consumer.blockchain;

import com.lion.common.util.JsonUtil;

import java.util.ArrayList;

/**
 * BlockChainDemo
 * 块链示例
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/01/03
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public class BlockChain {

    private static ArrayList<Block> blockchain = new ArrayList<>();
    private static int difficulty = 5;

    public static void main(String[] args) {

        //add our blocks to the blockchain ArrayList:

        System.out.println("Trying to Mine block 1... ");
        addBlock(new Block("Hi im the first block", "0"));

        System.out.println("Trying to Mine block 2... ");
        addBlock(new Block("Yo im the second block", blockchain.get(blockchain.size() - 1).hash));

        System.out.println("Trying to Mine block 3... ");
        addBlock(new Block("Hey im the third block", blockchain.get(blockchain.size() - 1).hash));

        System.out.println("\nBlockchain is Valid: " + isChainValid());

        String blockchainJson = JsonUtil.jsonObj2Str(blockchain);
        System.out.println("\nThe block chain: ");
        System.out.println(blockchainJson);

    }


    // 检查区块链的完整性
    private static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //loop through blockchain to check hashes:
        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
            //compare registered hash and calculated hash:
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            //check if hash is solved
            if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }

        }
        return true;
    }

    private static void addBlock(Block newBlock) {
        newBlock.mineBlock(difficulty);
        blockchain.add(newBlock);
    }
}
