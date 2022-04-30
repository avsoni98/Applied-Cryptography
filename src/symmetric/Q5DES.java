package symmetric;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.BitSet;
import java.util.Iterator;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class Q5DES {

	
	public static byte[] DecryptDES_ECB_NoPadding(byte[] ct, byte[] ky) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
		Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
		Key myKey = new SecretKeySpec(ky, "DES");
		cipher.init(Cipher.DECRYPT_MODE, myKey);
		byte[] pt = cipher.doFinal(ct);
		return pt;
		}
	
	
	public static byte[] bitwiseFlip(byte[] ky){ //clean up spaces - fix the size to maximum 8 bytes
		byte[] bt = new byte[ky.length]; 
		for(int i=0; i< ky.length; i++ ){
			bt[i] = (byte) ~ky[i];
		}
		return bt;
	}
	
	
	public static void main(String[] args) throws Exception {
	
		byte[] ky = "FACEBOOK".getBytes();
		byte[] ct = CryptoTools.hexToBytes("8A9FF0E2CD27DA4DC7F0C810E73D0E3B3B27CA03762BAE85597995997E625BDF0FEC655994EDD4B0851D7955B3F66717A52F83D01D73ABD9C593DA8C8CCBB073BB19E78442D9AA6D13B307EC0E8EA191E6A21897A82F1A643DC3BE0E12854D01C6006AA1D0EB1B94CAC573908018F284");
		byte[] negate_ky = bitwiseFlip(ky);		
				
		byte[] decrypt1 = DecryptDES_ECB_NoPadding(ct, negate_ky);
		byte[] decrypt2 = DecryptDES_ECB_NoPadding(decrypt1, ky);

		System.out.print(new String(decrypt2));

	}
	
	
	
	
}