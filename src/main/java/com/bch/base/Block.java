package com.bch.base;

import java.util.Date;

/**
 * @Description:
 * @Auther: XIAOWS
 * @Date: 2018/6/4 10:00
 */
public class Block {
	public String previousHash;//前一块的hash和data
	private String data;
	private long timeStamp;
	public String hash;//数字签名
	private int nonce;//临时工


	public Block(String data, String previousHash) {
		this.previousHash = previousHash;
		this.data = data;
		this.timeStamp = new Date().getTime();
		this.hash = calculateHash();//Make sure we do this after we set the other values.
	}

	/**
	 * 获取当前数字签名
	 *
	 * @return
	 */
	public String calculateHash() {
		return StringUtil.applySha256(previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + data);
	}

	/**
	 * 暂时理解为挖矿
	 * 具体逻辑还不理解
	 *
	 * @param difficulty
	 */
	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0');
		while (!hash.substring(0, difficulty).equals(target)) {
			nonce++;
			hash = calculateHash();
//			System.out.println(nonce +" @ "+ hash);
		}
		System.out.println("Block Mined! : " + hash + " " + nonce);
	}

}
