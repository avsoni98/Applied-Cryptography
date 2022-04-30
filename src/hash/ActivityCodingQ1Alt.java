package hash;

import java.math.BigInteger;
import java.security.MessageDigest;

import util.CryptoTools;

public class ActivityCodingQ1Alt {

	
	
	public static void main(String[] args) throws Exception {
		
		//The receiver is going to take the plaintext message m and compute their own hash of it
		//Receiver is then going to take the signature and decrypt it to get the sent hash
		//Receiver will then compare the decrypted hash to their self-computed hash
		
		BigInteger n = new BigInteger("94587468335128982981605019776781234618384857805657005686084562260910788622013722070926491690843853690071248130134427832324966728582532832363221542231787068203763027067400082835394459857525017707284768411819006776211493735326500782954621660256501187035611332577696332459049538105669711385995976912007767106063");
		BigInteger d = new BigInteger("7289370196881601766768920490284861650464951706793000236386405648425161747775298344104658393385359209126267833888223695609366844098655240542152017354442883676634193191857568369042999854440242050353181703706753485749165295123694487676952198090537385200990850805837963871485320168470788328336240930212290450023");
		BigInteger e = new BigInteger("74327");

		//Plaintext for sending a message
		String ptString = "Meet me at 5 pm tomorrow";
		byte[] pt1 = ptString.getBytes();
			
		//HASHING the plaintext with 512-bit SHA2 when SENDING it
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		byte[] hash = md.digest(pt1);	
				
		System.out.print(CryptoTools.bytesToHex(hash)); //self-computed hash
				
		//RSA manual encryption of the hash gives the signature i.e. encrypted/signed hash
		BigInteger hashBigInteger = new BigInteger(hash);
		BigInteger compute_signature = hashBigInteger.modPow(e, n);
		byte[] signature = compute_signature.toByteArray();
		
		//System.out.println(CryptoTools.bytesToHex(signature)); //There is NO PADDING this way - so compare to RSA with nopadding to check if the signed i.e. ciphertext values will be the same

		
		//RSA manual DECRYPTION of the signed hash returns the hash
		BigInteger hashBigInteger2 = new BigInteger(signature);
		BigInteger compute_signature2 = hashBigInteger2.modPow(d, n); //use d instead of e for decryption of the received signature
		byte[] signature_decrypted = compute_signature2.toByteArray();
				
		System.out.println("\n" + CryptoTools.bytesToHex(signature_decrypted));
		
		//Then compare the self-computed hash with the decrypted signature
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
