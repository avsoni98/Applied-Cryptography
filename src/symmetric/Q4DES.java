package symmetric;

import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.function.BinaryOperator;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class Q4DES {

	
	public static String desDecrypt(byte[] iv, byte[] ct, byte[] ky) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
		AlgorithmParameterSpec aps = new IvParameterSpec(iv);
		Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");
		Key myKey = new SecretKeySpec(ky, "DES");
		cipher.init(Cipher.DECRYPT_MODE, myKey, aps);
		byte[] pt = cipher.doFinal(ct);
		String string_pt = new String(pt);
						
		return string_pt;
		
	}
	
	public static String bitwiseFlip(String str){ //use it to flip the String of bits (of iv or ct)
		String[] arr = str.split("");
	
		for(int i = 0 ; i< arr.length; i++){
		
			if(arr[i].equals("0")) {
				arr[i] = "1";
								}
			else {
				arr[i] ="0";
				}
		}
		
		 StringBuffer sb = new StringBuffer();
	      for(int i = 0; i < arr.length; i++) {
	         sb.append(arr[i]);
	      }
	      String flip = sb.toString();
	      return flip;
	}
	
	public static byte[] trimByte(byte[] extraByte){ //clean up spaces - fix the size to maximum 8 bytes
		byte[] bt = new byte[8]; //size 8
		for(int i=0; i< 8; i++ ){
			bt[i] = extraByte[i+1];
		}
		return bt;
	}
		
	
	public static void main(String[] args) throws Exception {
	
	
		//byte[] ct = CryptoTools.hexToBytes("437DBAB5607137A5CFC1031114634087"); original ciphertext before dividing up into 2 blocks of 8 bytes (total is 16 bytes)
		byte[] ky = CryptoTools.hexToBytes("6B79466F724D4F50");
		byte[] iv = CryptoTools.hexToBytes("6976466F724D4F50");
		byte[] ct1 = CryptoTools.hexToBytes("437DBAB5607137A5"); //first 16 bytes of Ciphertext
		byte[] ct2 = CryptoTools.hexToBytes("CFC1031114634087"); //last 16 bytes of Ciphertext
				
		byte[] negate_iv1 = new BigInteger(bitwiseFlip(CryptoTools.bytesToBin(iv)),2).toByteArray(); //2's complement of BigInteger does XOR with negation of IV
		byte[] decrypted_1 = desDecrypt(trimByte(negate_iv1),ct1,ky).getBytes(); 
		
		byte[] negate_ct1 = new BigInteger(bitwiseFlip(CryptoTools.bytesToBin(ct1)),2).toByteArray(); //2's complement of BigInteger does XOR with negation of block 1 of ciphertext
		byte[] decrypted_2 = desDecrypt(trimByte(negate_ct1),ct2,ky).getBytes();
		
		
		//CryptoTools.bytesToFile(decrypted_1, "data/AS2Q4Ans.txt");
		System.out.print(new String(decrypted_1));
		System.out.println(new String(decrypted_2));
		

		
		
		
	}
	
	
	
	
	
	
	
	
}
