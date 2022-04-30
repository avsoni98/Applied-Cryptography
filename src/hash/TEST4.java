package hash;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class TEST4 {



	public static byte[] bitwiseXOR(byte[] b1, byte[] b2){ //XOR two given byte arrays

		byte[] bt = new byte[b1.length]; 

		for(int i=0; i< b1.length; i++ ){

				

					bt[i] = (byte) (b1[i] ^ b2[i]);

		}

		return bt;

	}

	

	

	public static void main(String[] args) throws Exception {



	

		//byte[] m_og = "Why do we tell actors to break a leg? because every play has a cast".getBytes();

		byte[] m_1 = "Why do we tell a".getBytes();

		byte[] m_2 = "ctors to break a".getBytes();

		byte[] m_3 = " leg? because ev".getBytes();

		byte[] m_4 = "ery play has a c".getBytes();

		byte[] m_5 = "ast".getBytes();

        byte[] padded_m5 = Arrays.copyOf(m_5, 16);



		byte[] iv = CryptoTools.hexToBytes("44668abddef1321134568abcdef13221");

		byte[] ky = CryptoTools.hexToBytes("34567abcdef0321134567abcdef03211");	

			

		//XOR the first message block with the IV



		byte[] first_XOR = bitwiseXOR(iv, m_1);

		Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");

		Key myKey = new SecretKeySpec(ky, "AES");

		cipher.init(Cipher.ENCRYPT_MODE, myKey);

		byte[] y = cipher.doFinal(first_XOR);

				

		byte[] second_XOR = bitwiseXOR(y, m_2);

		Cipher cipher2 = Cipher.getInstance("AES/ECB/NoPadding");

		Key myKey2 = new SecretKeySpec(ky, "AES");

		cipher.init(Cipher.ENCRYPT_MODE, myKey);

		byte[] y2 = cipher.doFinal(second_XOR);

	

		byte[] third_XOR = bitwiseXOR(y2, m_3);

		Cipher cipher3 = Cipher.getInstance("AES/ECB/NoPadding");

		Key myKey3 = new SecretKeySpec(ky, "AES");

		cipher.init(Cipher.ENCRYPT_MODE, myKey);

		byte[] y3 = cipher.doFinal(third_XOR);

		

		byte[] fourth_XOR = bitwiseXOR(y3, m_4);

		Cipher cipher4 = Cipher.getInstance("AES/ECB/NoPadding");

		Key myKey4 = new SecretKeySpec(ky, "AES");

		cipher.init(Cipher.ENCRYPT_MODE, myKey);

		byte[] y4 = cipher.doFinal(fourth_XOR);

		

		byte[] fifth_XOR = bitwiseXOR(y4, padded_m5);

		Cipher cipher5 = Cipher.getInstance("AES/ECB/NoPadding");

		Key myKey5 = new SecretKeySpec(ky, "AES");

		cipher.init(Cipher.ENCRYPT_MODE, myKey);

		byte[] y5 = cipher.doFinal(fifth_XOR);

		

		System.out.print(CryptoTools.bytesToHex(y5));

		

		

	

	}
	
	
	
	
	
	
	
}
