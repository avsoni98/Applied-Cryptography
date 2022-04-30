package symmetric;

import java.security.AlgorithmConstraints;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class AES_CBC_Decrypt {
	
	//An English string is encrypted with 128-bit AES using PKCS5 padding, the CBC mode of operation, and the following parameters:
	//       KEY: 0x 9F0DCEDB322F3C6873F9256E01376BA4
	//       IV:  0x 20FC19123087BF6CAC8D0F1254123004
	//Given that the ciphertext is:
	//       0x F38ADBA8A7B4CC613578355032205D50
	//write a program that determines the letters of the plaintext and output them as English string, not in hex or binary. What is the first word of the plaintext?
	
	public static void main(String[] args) throws Exception {
	
	
		byte[] ct = CryptoTools.hexToBytes("F38ADBA8A7B4CC613578355032205D50");
		byte[] iv = CryptoTools.hexToBytes("20FC19123087BF6CAC8D0F1254123004");
		byte[] key = CryptoTools.hexToBytes("9F0DCEDB322F3C6873F9256E01376BA4");	
				
		Key secretKey = new SecretKeySpec(key, "AES"); //adjust if using DES or AES
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); //adjust for DES/AES, change CBC or ECB (no IV value)
		AlgorithmParameterSpec aps = new IvParameterSpec(iv);
		cipher.init(Cipher.DECRYPT_MODE, secretKey, aps);
		byte[] pt = cipher.doFinal(ct);
		
		CryptoTools.bytesToFile(pt, "data/AS2Q1Ans.txt");
	}
	
	
}
