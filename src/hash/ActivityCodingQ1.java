package hash;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import util.CryptoTools;

public class ActivityCodingQ1 {

	public static byte[] decryptRSA(BigInteger n, BigInteger d, BigInteger e, byte[] ct) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		RSAPublicKeySpec pubSpec = new RSAPublicKeySpec(n, e); //specify values yourself
		RSAPrivateKeySpec privSpec = new RSAPrivateKeySpec(n, d); //specify values yourself
		PublicKey pub = keyFactory.generatePublic(pubSpec);
		PrivateKey priv = keyFactory.generatePrivate(privSpec); 

		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1padding"); //padding is important or else you get 0s
		cipher.init(Cipher.DECRYPT_MODE, priv);
		byte[] pt = cipher.doFinal(ct); //signed hash
		return pt; //returns the byte of the decrypted signed hash 
	}
 	
	

	
	public static void main(String[] args) throws Exception {

	//Get the hash of the plaintext and then sign i.e. encrypt it with RSA
	

	//Plaintext for sending a message
	String ptString = "Meet me at 5 pm tomorrow";
	byte[] pt1 = ptString.getBytes();
		
	//HASHING the plaintext with 512-bit SHA2 when SENDING it
	MessageDigest md = MessageDigest.getInstance("SHA-512");
	byte[] hash = md.digest(pt1);	
		
		
	//RSA keys expressed in BigIntegers
	BigInteger n = new BigInteger("94587468335128982981605019776781234618384857805657005686084562260910788622013722070926491690843853690071248130134427832324966728582532832363221542231787068203763027067400082835394459857525017707284768411819006776211493735326500782954621660256501187035611332577696332459049538105669711385995976912007767106063");
	BigInteger e = new BigInteger("74327");
	BigInteger d = new BigInteger("7289370196881601766768920490284861650464951706793000236386405648425161747775298344104658393385359209126267833888223695609366844098655240542152017354442883676634193191857568369042999854440242050353181703706753485749165295123694487676952198090537385200990850805837963871485320168470788328336240930212290450023");
	
	
	//Signing the HASH with RSA encryption
	KeyFactory keyFactory = KeyFactory.getInstance("RSA"); 
	RSAPublicKeySpec pubSpec = new RSAPublicKeySpec(n, e); //specify values yourself
	RSAPrivateKeySpec privSpec = new RSAPrivateKeySpec(n, d); //specify values yourself
	PublicKey pub = keyFactory.generatePublic(pubSpec);
	PrivateKey priv = keyFactory.generatePrivate(privSpec); 

	Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1padding"); //padding is important or else you get 0s
	//cipher.init(Cipher.<ENCRYPT/DECRYPT>_MODE, <pub/priv>);
	cipher.init(Cipher.ENCRYPT_MODE, pub);
	byte[] ct = cipher.doFinal(hash); //signed hash
	
	System.out.println(CryptoTools.bytesToHex(hash)); //returns the digital signature as a hex value
	
	
	// ********RECEIVED a plaintext message and the digital signature****************************************************************************************************************
	
	//Plaintext from sent message, recipient is hashing it themselves
	String ptStringReceived = "Meet me at 5 pm tomorrow";
	byte[] pt2 = ptString.getBytes(); 
			
	//HASHING the received plaintext with 512-bit SHA2 which was used when SENDING it
	MessageDigest md2 = MessageDigest.getInstance("SHA-512");
	byte[] hash2 = md2.digest(pt2);	
	
	//Recipient is decrypting the signature (signed hash) y they received with the plaintext (x,y) 
	byte[] receivedSig = decryptRSA(n, d, e, ct);
	
	//If padding is not used in RSA for both decryption and encryption, have to check manually. Lots of 0s appear
	//System.out.print(CryptoTools.bytesToHex(receivedSig)); 
	//System.out.print("\n" + CryptoTools.bytesToHex(hash2));
	
	//If padding PKCS1padding is used in RSA, compare if the decrypted signed hash received is THE SAME as the one recreated using given plaintext
	if (CryptoTools.bytesToHex(hash2).equals(CryptoTools.bytesToHex(receivedSig))) {
		
		System.out.print(true);
		System.out.print("\nDecrypted received signature hash in hex: " + CryptoTools.bytesToHex(receivedSig));
		System.out.print("\nRecreated hash converted to hex: " + CryptoTools.bytesToHex(hash2));
	}
	
	else {
		System.out.print(false);
	}
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
