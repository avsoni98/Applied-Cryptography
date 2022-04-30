package symmetric;

import java.security.Key;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class CQ3 {


	//Encrypt the plaintext "Facebook" using DES without any padding or mode of operation (i.e. ECB) using the key "universe" and output the ciphertext in binary (use the byteToBin method in util). 
	//Next, generate a random number between 0 and 63; flip that bit in the plaintext; encrypt; and compute the number of bits in the ciphertext that have flipped as a result. 
	//Re-do this avalanche effect computation several times and determine the average number of flipped bits in the ciphertext.
	
	
	public static byte[] encrypt(byte[] plainText, byte[] key) throws Exception{
		Cipher engine = Cipher.getInstance("DES/ECB/NoPadding");
		Key myKey = new SecretKeySpec(key,"DES");
		engine.init(Cipher.ENCRYPT_MODE, myKey);
		byte[] cipherText = engine.doFinal(plainText);
		return cipherText;
	}
	
	
	public static void main(String[] args) throws Exception {
	
		byte[] pt1 = "Facebook".getBytes();
		byte[] key = "universe".getBytes();


		byte[] OGencryptedBytes = encrypt(pt1, key);
		byte[] encryptedBytes = encrypt(pt1, key);
		

					for (int i = 0; i < encryptedBytes.length; i++ ) { //flip bit at a specific point in the byte
						int randomNum = ThreadLocalRandom.current().nextInt(0, 63 + 1); // use to find random value between 0 and 63
						encryptedBytes[randomNum/8] ^= 1 << (randomNum%8); //use to flip bit at specific point											 
						}
					
		byte[] flippedencryptedBytes = encrypt(encryptedBytes, key); //encrypt the bytes where the bit was changed at a specific point
		
		String flippedString = CryptoTools.bytesToBin(flippedencryptedBytes); //change flipped+encrypted bytes and change to binary string
		
		String originalString = CryptoTools.bytesToBin(OGencryptedBytes); //change the original encrypted
		
		
				//find number of differences in the string binary
					int k = 0;
					for( int i = 0; i < originalString.length(); i++) {
						
						 if (originalString.charAt(i) != flippedString.charAt(i)) {
							 
							 k++;
						 	
						 }
												
					}
					
					System.out.print(k);
					
					
	}

}						 	 
						 
					 			//System.out.print(new String(CryptoTools.bytesToBin(encryptedBytes)));

