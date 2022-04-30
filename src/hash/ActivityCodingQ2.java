package hash;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.IllegalFormatWidthException;

import javax.crypto.Cipher;

import util.CryptoTools;

public class ActivityCodingQ2 {

	
	
	
	
	
	public static void main(String[] args) throws Exception {
		


	BigInteger nA = new BigInteger("171024704183616109700818066925197841516671277");
	BigInteger eA = new BigInteger("1571");

	BigInteger pB = new BigInteger("98763457697834568934613");
	BigInteger qB = new BigInteger("8495789457893457345793");
	BigInteger eB = new BigInteger("87697");
	BigInteger nB = pB.multiply(qB);
	BigInteger phiB = pB.subtract(BigInteger.ONE).multiply(qB.subtract(BigInteger.ONE));
	BigInteger dB = eB.modInverse(phiB);
	
	
	//Alice sending the message. Signs/encrypts message 'm' with her private key, gets signature 's'. Then encrypts the message and signature with Bob's public keys
	BigInteger m_encrypted = new BigInteger("418726553997094258577980055061305150940547956");
	BigInteger s_encrypted = new BigInteger("749142649641548101520133634736865752883277237");

	//Decrypt manually by Bob:
	BigInteger m_decrypted = m_encrypted.modPow(dB, nB); //Decrypt with Bob's private key to get the signed message 'm'
	BigInteger s_decrypted = s_encrypted.modPow(dB, nB); //Gives the signature of m i.e. m signed
    
	//System.out.println(CryptoTools.bytesToHex(m_decrypted.toByteArray())); //get decrypted message m in HEX
    //System.out.println(new String(m_decrypted.toByteArray()).trim()); //get decrypted message m in STRING - wasn't english
   
	System.out.println(CryptoTools.bytesToHex(m_decrypted.toByteArray())); //get decrypted signature s in HEX (created from signing m)
  
    //***********Checking if messages are the same by decrypted the signature - Bob will recreate the signature s by signing i.e. encrypting the decrypted message m using Alice's public key and check if singatures are the same***************************************88
	//FOR THIS QUESTION: Alice signed the message m using her PRIVATE key to get s. Since we can't recreate s b/c we don't know her private key, we will decrypt (the already decrypted) s using her public key to get the message m and compare it ************* 
	//Usually you're supposed to be able to recreate the signature and compare it, but because the private key was used we can't do that so we have to work in reverse from s to get m

	
	BigInteger m_from_s = s_decrypted.modPow(eA, nA); //Gives the signature of m i.e. m signed
	System.out.println("\n"+CryptoTools.bytesToHex(m_from_s.toByteArray()));	
	
	if (m_from_s == m_decrypted) {
		
	}
	
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
