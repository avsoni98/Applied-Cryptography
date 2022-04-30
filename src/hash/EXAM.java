package hash;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.DrbgParameters.NextBytes;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

import util.CryptoTools;

public class EXAM {

	
	
	public static void main(String[] args) throws Exception { 
		
		//HASHING the plaintext with SHA512 when SENDING it
		byte[] message = "I am in love with Cryptography".getBytes();
		MessageDigest md = MessageDigest.getInstance("SHA512");
		byte[] hash = md.digest(message);
				
		//Your RSA keys are as follows (all expressed as big integers)
		BigInteger n = new BigInteger("94587468335128982981605019776781234618384857805657005686084562260910788622013722070926491690843853690071248130134427832324966728582532832363221542231787068203763027067400082835394459857525017707284768411819006776211493735326500782954621660256501187035611332577696332459049538105669711385995976912007767106063");
		BigInteger e = new BigInteger("74327");
		BigInteger d = new BigInteger("7289370196881601766768920490284861650464951706793000236386405648425161747775298344104658393385359209126267833888223695609366844098655240542152017354442883676634193191857568369042999854440242050353181703706753485749165295123694487676952198090537385200990850805837963871485320168470788328336240930212290450023");
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		RSAPublicKeySpec pubSpec = new RSAPublicKeySpec(n, e); //specify values yourself
		RSAPrivateKeySpec privSpec = new RSAPrivateKeySpec(n, d); //specify values yourself
		PublicKey pub = keyFactory.generatePublic(pubSpec);
		PrivateKey priv = keyFactory.generatePrivate(privSpec);

		Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, priv);
		byte[] signedHash = cipher.doFinal(hash);
	
		System.out.println(CryptoTools.bytesToHex(signedHash));
	
	}
	
	
	
}
