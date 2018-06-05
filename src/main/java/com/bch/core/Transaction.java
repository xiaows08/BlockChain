package com.bch.core;


import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;

/**
 * @Description:交易类
 * @Auther: XIAOWS
 * @Date: 2018/6/4 15:27
 */
public class Transaction {
	public String transactionId;//This is also the HASH of the transaction.
	public PublicKey sender;//Senders address/public key.
	public PublicKey reciepient;//Reciepients address/public key.
	public float value;
	public byte[] signature;//This is to prevent anybody else from spending funds in our wallet.

	public ArrayList<TransactionInput> inputs = new ArrayList<>();
	public ArrayList<TransactionOutput> outputs = new ArrayList<>();

	public static int sequence = 0;//a rough count of how many transactions have been generated.

	public Transaction(PublicKey from, PublicKey to, float value, ArrayList<TransactionInput> inputs) {
		this.sender = from;
		this.reciepient = to;
		this.value = value;
		this.inputs = inputs;
	}

	//This Calculates the transaction hash (which will be used as its id)
	private String calculateHash() {
		sequence++;
		return StringUtil.applySha256(
				StringUtil.getStringFromKey(sender)
						+ StringUtil.getStringFromKey(reciepient)
						+ Float.toString(value)
						+ sequence);
	}

	/**
	 * 在现实中，您可能希望签名更多信息，例如使用的输出/输入和/或时间戳（现在我们只是签名最低限度的信息）
	 * Signs all the data we don't wish to be tampered with.
	 *
	 * @param privateKey
	 */
	public void generateSignature(PrivateKey privateKey) {
		String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient);
		signature = StringUtil.applyECDSASig(privateKey, data);
	}


	/**
	 * 签名将由矿工验证，只有签名验证成功后交易才能被添加到区块中去
	 * Verifies the data we signed hasn't been tampered with.
	 *
	 * @return
	 */
	public Boolean verifiySignature() {
		String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient);
		return StringUtil.verifyECDSASig(sender, data, signature);
	}
}
