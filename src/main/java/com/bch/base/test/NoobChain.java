package com.bch.base.test;

import com.bch.base.Block;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

/**
 * @Description:
 * @Auther: XIAOWS
 * @Date: 2018/6/4 10:31
 */
public class NoobChain {
	public static ArrayList<Block> blockChain = new ArrayList<>();
	public static int difficulty = 5;

	/**
	 * 检测区块链的完整性
	 * @return
	 */
	public static Boolean isChainValid() {
		Block previousBlock;
		Block currentBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');

		//Loop through blockChain to check hashes:
		for (int i = 1; i < blockChain.size(); i++) {
			previousBlock = blockChain.get(i - 1);
			currentBlock = blockChain.get(i);

			//Compare previous hash with registered previous hash:
			if (!previousBlock.hash.equals(currentBlock.previousHash)) {
				System.out.println("Previous Hashes not equal");
				return false;
			}

			//Compare registered hash with calculated hash:
			if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("Current Hashes not equal");
				return false;
			}

			//Check if hash is solved
			if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
//		System.out.println(new String(new char[5]));

//		Block genesisBlock = new Block("Hi im the first block", "0");
//		System.out.println("Hash for block 1 : " + genesisBlock.hash);
//
//		Block secondeBlock = new Block("Yo im the second block", genesisBlock.hash);
//		System.out.println("Hash for block 2 : " + secondeBlock.hash);
//
//		Block thirdBlock = new Block("Hey im the third block", secondeBlock.hash);
//		System.out.println("Hash for block 3 : " + thirdBlock.hash);

		//Add our blocks to the blockChain ArrayList:
//		blockChain.add(new Block("Hi im the first block", "0"));
//		blockChain.add(new Block("Yo im the second block", blockChain.get(blockChain.size() - 1).hash));
//		blockChain.add(new Block("Hey im the second block", blockChain.get(blockChain.size() - 1).hash));

		blockChain.add(new Block("Hi im the first block", "0"));
		System.out.println("Trying to mine block 1...");
		blockChain.get(0).mineBlock(difficulty);

		blockChain.add(new Block("Yo im the second block", blockChain.get(blockChain.size() - 1).hash));
		System.out.println("Trying to Mine block 2...");
		blockChain.get(1).mineBlock(difficulty);

		blockChain.add(new Block("Hey im the third block", blockChain.get(blockChain.size() - 1).hash));
		System.out.println("Trying to Mine block 3...");
		blockChain.get(2).mineBlock(difficulty);

		System.out.println("BlockChain is Valid: " + isChainValid());

		String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);

		System.out.println("The Block chain: ");
		System.out.println(blockChainJson);
	}
}
