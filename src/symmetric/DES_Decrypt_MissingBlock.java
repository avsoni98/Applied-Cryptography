package symmetric;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class DES_Decrypt_MissingBlock {

	
	
	
public static byte[] desDecrypt(byte[] ct, byte[] ky) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding"); //did not use PKCS5padding, it gave bad key error
		Key myKey = new SecretKeySpec(ky, "DES");
		cipher.init(Cipher.DECRYPT_MODE, myKey);
		byte[] pt = cipher.doFinal(ct);
		return pt;
	}

	

	

	public static byte[] bitwiseXOR(byte[] b1, byte[] b2){ //XOR two given byte arrays
		byte[] bt = new byte[b1.length]; 
		for(int i=0; i< b1.length; i++ ){
					bt[i] = (byte) (b1[i] ^ b2[i]);
		}
		return bt;
	}

	

	

	public static void main(String[] args) throws Exception {

			//byte[] ct = CryptoTools.hexToBytes("????????????????4E51297B424F90D8B2ACD6ADF010DDC4");
			byte[] ct1 = CryptoTools.hexToBytes("4E51297B424F90D8");
			byte[] ct2 = CryptoTools.hexToBytes("B2ACD6ADF010DDC4");
			byte[] iv = CryptoTools.hexToBytes("0123456701234567");
			byte[] ky = "CSE@YORK".getBytes();	

			//Block 3
			byte[] decrypted_2 = desDecrypt(ct2, ky);
			byte[] decryptXor_2 = bitwiseXOR(ct1, decrypted_2);

			System.out.print(new String(decryptXor_2));

	}
	
	
	
	
	
}
