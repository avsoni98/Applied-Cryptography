package symmetric;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class Q3DES {

	//Alice sends a document to you using DES, PKCS5 padding, the CBC mode of operation, and the following parameters:
	 //     KEY = "CSE@YORK"  (expressed as an English string)
	 //     IV  = 0x 0123456701234567 
	//You receive the following ciphertext:
	//      0x ????????????????4E51297B424F90D8B2ACD6ADF010DDC4 
	//As you can see, noise in the channel has unfortunately garbled the first received block (indicated by '?' above). 
	//Can you recover any part of the plaintext? If yes, then salvage that part and write it (as an English string, not in hex or binary), and in that case, what is the first word of the plaintext? 
	//Otherwise, explain why it is not possible to salvage any part of the plaintext.	
	
	
public static void main(String[] args) throws Exception {
		
		
		String keyString = "CSE@YORK";
		byte[] ky = keyString.getBytes();
		byte[] iv = CryptoTools.hexToBytes("0123456701234567");
		byte[] ct = CryptoTools.hexToBytes("4E51297B424F90D8B2ACD6ADF010DDC4"); //must be a multiple of 8
				
		Key secretKey = new SecretKeySpec(ky, "DES"); //adjust if using DES or AES
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding"); //adjust if using DES or AES
		AlgorithmParameterSpec aps = new IvParameterSpec(iv);
		cipher.init(Cipher.DECRYPT_MODE, secretKey, aps);
		byte[] pt = cipher.doFinal(ct);
		
		//CryptoTools.bytesToFile(pt, "data/AS2Q3Ans.txt");
		System.out.print(new String(pt));
		
		//ANSWER = It is NOT possible to obtain any discernable segment in the english language from the ciphertext that was intercepted. 
		//		   This is because the process of diffusion in CBC makes it so that each segment relies on the one before it. We are missing the 	
		//		   first segment, so we cannot obtain any discernable information from the 2nd and 3rd segments. If only the first segment was available,
		//		   we might be able to decrypt it to obtain discernable content in the English language instead of the random values we obtained here
	}
	
	
	
	
	
	
	
}
