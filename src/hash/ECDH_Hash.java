package hash;

import java.nio.ByteBuffer;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class ECDH_Hash {

	public static void main(String[] args) throws Exception {





		byte[] a_pub_hex = CryptoTools.hexToBytes("3059301306072A8648CE3D020106082A8648CE3D0301070342000450C35C2FB11926C2C91E089CFC743F9D942EE14B8D42E25AE6588C4F93DDFF6ACDF520F74AF3E2500EF2A5E2C346D4DA7E92C1F89AD9FD4F3ED1B97DC3F39DC8");
		byte[] a_priv_hex = CryptoTools.hexToBytes("3041020100301306072A8648CE3D020106082A8648CE3D0301070427302502010104200FE89D3070EECF985F971851B088EC97605A08D037F3CF3463FED25BCE0037B5");

		

		byte[] bob_pub_hex = CryptoTools.hexToBytes("3059301306072A8648CE3D020106082A8648CE3D03010703420004678DF0E72D7FC86006174E506B1729081E5D1201936EBA8A39E8741E4F713F8C29AE2E62038D95B36A585E2A87FEA73BE482611115457A3D3823EA5D79E31154");
		byte[] bob_priv_hex = CryptoTools.hexToBytes("3041020100301306072A8648CE3D020106082A8648CE3D030107042730250201010420090145EB296FD96158EDF5E59D20EBB8E7332BBE150784D91900DB2006980127");

		

		byte[] ct_hex = CryptoTools.hexToBytes("B1803ED24B595CCB11AA39473DC7B10B"); //ciphertext message is from Alice

		

		X509EncodedKeySpec alice_PublKy = new X509EncodedKeySpec(a_pub_hex); //NEEDED FOR HASH
		PKCS8EncodedKeySpec alice_PrivKy = new PKCS8EncodedKeySpec(a_priv_hex);


		X509EncodedKeySpec bob_PublKy = new X509EncodedKeySpec(bob_pub_hex); //NEEDED FOR HASH
		PKCS8EncodedKeySpec bob_PrivKy = new PKCS8EncodedKeySpec(bob_priv_hex); 



		

		//STEP 2 - Read other's public key:



	    KeyFactory kf = KeyFactory.getInstance("EC"); //EC or something else


	    PrivateKey a_Priv = kf.generatePrivate(alice_PrivKy); 

	    //PrivateKey b_Priv = kf.generatePrivate(bob_PrivKy); 

	    

	    PublicKey a_Public = kf.generatePublic(alice_PublKy); //NEEDED FOR HASH
	    PublicKey b_Public = kf.generatePublic(bob_PublKy);  //NEEDED FOR HASH, BUT MUTED OTHERWISE





		//STEP 3 - Perform key agreement for both keys
		KeyAgreement ka = KeyAgreement.getInstance("ECDH"); 
		ka.init(a_Priv); //alice's private key (message from alice)
		ka.doPhase(b_Public, true); //Bob's public key (message to bob)
		byte[] sharedSecret = ka.generateSecret(); //customary to perform byte hashing
		System.out.printf( new String(sharedSecret));




		//HASHING************************************
	    // Derive a key from the shared secret and both public keys
	    MessageDigest hash = MessageDigest.getInstance("SHA-256"); //type of hash may vary
	    hash.update(sharedSecret);
	    // Simple deterministic ordering
	    List<ByteBuffer> keys = Arrays.asList(ByteBuffer.wrap(a_pub_hex), ByteBuffer.wrap(bob_pub_hex));
	    Collections.sort(keys);
	    hash.update(keys.get(0));
	    hash.update(keys.get(1));
	    byte[] derivedKey = hash.digest();
		
		System.out.print("\n The hash: \n" + CryptoTools.bytesToHex(derivedKey));

		
		
		///////////////////////////////AES CIPHER FOR DECRYPTION///////////////////////////////////////////////////////
		byte[] iv = CryptoTools.hexToBytes("4000000001000000000C00000001000C");	//IV value for CBC

		//Bob perform decryption using the sharedsecret

		Key secretKey = new SecretKeySpec(sharedSecret, "AES"); //adjust if using DES or AES
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); //adjust if using DES or AES and padding
		IvParameterSpec aps= new IvParameterSpec(iv); //if using CBC 
		cipher.init(Cipher.DECRYPT_MODE, secretKey, aps);
		byte[] plaintext = cipher.doFinal(ct_hex);

		System.out.print("\nOriginal Message: \n" + new String(plaintext));

	
		
		
		
		

	}	
	
	
	
	
}
