package com.bch.core.test;

import com.bch.base.Block;
import com.bch.core.StringUtil;
import com.bch.core.Transaction;
import com.bch.core.Wallet;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;
import java.util.ArrayList;

/**
 * @Description:
 * @Auther: XIAOWS
 * @Date: 2018/6/4 17:20
 */
public class NoobChain {
	public static ArrayList<Block> blockChain = new ArrayList<>();
	public static int difficulty = 5;
	public static Wallet walletA;
	public static Wallet walletB;

	public static void main(String[] args) {
		// Setup Bouncey castle as a Security Provider
		Security.addProvider(new BouncyCastleProvider());
		// Create the new wallets
		walletA = new Wallet();
		walletB = new Wallet();
		// Test public and private keys
		System.out.println("Private and public keys: ");
		System.out.println(StringUtil.getStringFromKey(walletA.privateKey));
		System.out.println(StringUtil.getStringFromKey(walletA.publicKey));
		//Create a test transaction from WalletA to WalletB
		Transaction transaction = new Transaction(walletA.publicKey, walletB.publicKey, 5, null);
		transaction.generateSignature(walletA.privateKey);
		//Verify the signature works and verify it from the public key
		System.out.print("Is signature verified: ");
		System.out.println(transaction.verifiySignature());
	}

}
