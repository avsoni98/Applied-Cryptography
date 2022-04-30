package asymmetric;

import java.math.BigInteger;
import java.security.MessageDigest;

import util.CryptoTools;

public class DHSessionKeyQ5 {
	
	
	
	
	public static void main(String[] args) throws Exception {
		
	BigInteger p = new BigInteger("341769842231234673709819975074677605139"); //public parameter
	BigInteger g = new BigInteger("37186859139075205179672162892481226795");  //public parameter

	BigInteger aX = new BigInteger("83986164647417479907629397738411168307"); //Alice's private key value
	BigInteger bX = new BigInteger("140479748264028247931575653178988397140"); //Bob's private key value


	BigInteger pubA = g.modPow(aX, p); //the generated public key of Alice
	BigInteger pubB = g.modPow(bX, p); //the generated public key of Bob

	BigInteger sesKeyA = pubB.modPow(aX, p); //the session key created using Alice's public key
	BigInteger sesKeyB = pubB.modPow(aX, p); //the session key created using Alice's public key


	
	
	//HASHING the plaintext with SHA1 when SENDING it
	//MessageDigest md = MessageDigest.getInstance("SHA1");
	//byte[] hash = md.digest(BeforeHashBytes);
	
	System.out.println(sesKeyA); 
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
