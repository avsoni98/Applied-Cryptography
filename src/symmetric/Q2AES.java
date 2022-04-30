package symmetric;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class Q2AES {
	
	
	
	//An English string is encrypted with 128-bit AES using PKCS5 padding, the CBC mode of operation, and the following parameters:
	//      KEY = "DO NOT TELL EVE!"  (expressed as an English string)
	//      IV  = 0x 20FC19123087BF6CAC8D0F1254123004 
	//Given that the ciphertext is:
	//      0x 3188073EA5DB3F5C05B6307B3595607135F5D4B22F2C3EB710AA31377F78B997 
	//determine the characters of the plaintext and output them as English string, not in hex or binary. What is the first word of the plaintext?
	
	
	
	public static void main(String[] args) throws Exception {
		
		
		String keyString = "DO NOT TELL EVE!"; //key as a string
		byte[] ky = keyString.getBytes();
		byte[] iv = CryptoTools.hexToBytes("20FC19123087BF6CAC8D0F1254123004");
		byte[] ct = CryptoTools.hexToBytes("3188073EA5DB3F5C05B6307B3595607135F5D4B22F2C3EB710AA31377F78B997");
				
		Key secretKey = new SecretKeySpec(ky, "AES"); //adjust if using DES or AES
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); //adjust if using DES or AES
		AlgorithmParameterSpec aps = new IvParameterSpec(iv);
		cipher.init(Cipher.DECRYPT_MODE, secretKey, aps);
		byte[] pt = cipher.doFinal(ct);
		
		System.out.print(new String(pt)); //output to the console
		//CryptoTools.bytesToFile(pt, "data/AS2Q2Ans.txt"); //output to a text file
	}
	
	

}
