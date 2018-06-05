package com.bch.core;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

/**
 * @Description:
 * @Auther: XIAOWS
 * @Date: 2018/6/4 14:55
 */
public class Wallet {
	public PrivateKey privateKey;
	public PublicKey publicKey;

	public Wallet() {
		generateKeyPair();
	}

	/**
	 * 椭圆曲线加密算法创建公钥和私钥
	 */
	private void generateKeyPair() {
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");

			//Initialize the key generator and generate a KeyPair
			keyGen.initialize(ecSpec, random);
			KeyPair keyPair = keyGen.generateKeyPair();

			//Set the public and private keys from keyPair
			privateKey = keyPair.getPrivate();
			publicKey = keyPair.getPublic();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
	}
}
