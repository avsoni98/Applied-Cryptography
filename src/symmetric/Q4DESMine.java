package symmetric;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.cert.PKIXRevocationChecker;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import util.CryptoTools;

public class Q4DESMine {

	
	public static byte[] desDecrypt(byte[] ct, byte[] ky) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
		//AlgorithmParameterSpec aps = new IvParameterSpec(iv);
		Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
		Key myKey = new SecretKeySpec(ky, "DES");
		cipher.init(Cipher.DECRYPT_MODE, myKey);
		byte[] pt = cipher.doFinal(ct);	
		return pt;	
	}
	
	
	public static byte[] bitwiseFlip(byte[] ky){ //negation
		byte[] bt = new byte[ky.length]; 
		for(int i=0; i< ky.length; i++ ){
			bt[i] = (byte) ~ky[i];
		}
		return bt;
	}
	
	
	public static byte[] bitwiseXOR(byte[] b1, byte[] b2){ //XOR two given byte arrays
		byte[] bt = new byte[b1.length]; 
		for(int i=0; i< b1.length; i++ ){	
					bt[i] = (byte) (b1[i] ^ b2[i]);
		}
		return bt;
	}
	

	
	public static void main(String[] args) throws Exception {
		//YORK MODE OF OPERATION
		//byte[] ct = CryptoTools.hexToBytes("437DBAB5607137A5CFC1031114634087"); original ciphertext before dividing up into 2 blocks of 8 bytes (total is 16 bytes)
		byte[] ky = CryptoTools.hexToBytes("6B79466F724D4F50");
		byte[] iv = CryptoTools.hexToBytes("6976466F724D4F50");
		byte[] ct1 = CryptoTools.hexToBytes("437DBAB5607137A5"); //first 16 bytes of Ciphertext
		byte[] ct2 = CryptoTools.hexToBytes("CFC1031114634087"); //last 16 bytes of Ciphertext
		
		//Block 1
		byte[] negate_iv1 = bitwiseFlip(iv);
		byte[] decrypted_1 = desDecrypt(ct1, ky);
		byte[] decryptXor_1 = bitwiseXOR(decrypted_1, negate_iv1);
		
		//Block 2
		byte[] negate_iv2 = bitwiseFlip(ct1);
		byte[] decrypted_2 = desDecrypt(ct2, ky);
		byte[] decryptXor_2 = bitwiseXOR(decrypted_2, negate_iv2);
		
		System.out.print(new String(decryptXor_1));
		System.out.print(new String(decryptXor_2));

		//int numBlocks = ct.length/8; //amount of blocks based on length of the ciphertext
		
				
	}
	
	
	
	
}
