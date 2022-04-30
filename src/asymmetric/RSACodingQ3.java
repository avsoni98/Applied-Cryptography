package asymmetric;

import java.io.InterruptedIOException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.concurrent.ThreadLocalRandom;

import javax.crypto.Cipher;

public class RSACodingQ3 {

	
	
	public static void main(String[] args) throws Exception {
		//Your RSA keys are as follows (all expressed as big integers)
		//Get the plaintext - pt
		BigInteger phi = new BigInteger("8584037913642434144111279062847405921823163865842701785008602377400681495147541519557274092429073976252689387304835782258785521935078205581766754116919200");
		BigInteger e = new BigInteger("65537");
		BigInteger ct = new BigInteger("1817487313698347891034157970684926175211834109573277793102901854482611726141560963120214926234448852417078321539316776648109260519063106558303669880226359");
		BigInteger q = new BigInteger("87020952829623092932322362936864583897972618059974315662422560067745889600571");
		
		//Calcuate p - from phi and q
		BigInteger one = new BigInteger("1");
		BigInteger p = (phi.divide(q.subtract(one))).add(one);		
		
		//Calcuate n - from p and q
		BigInteger n = q.multiply(p);
		
		//Calculate d - from n and e
	    BigInteger d = e.modInverse(phi);

	    //Check if my manually calculated p creates the same phi that was given
	    //BigInteger phi2 = p.subtract(one).multiply(q.subtract(one));
	    //if (phi.equals(phi2)) {
		//	System.out.print("si");
		//}
	    //else {
		//	System.out.print("nein");
		//}
	    
	    
		//Use RSA decryption 
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		RSAPublicKeySpec pubSpec = new RSAPublicKeySpec(n, e); //specify values yourself
		RSAPrivateKeySpec privSpec = new RSAPrivateKeySpec(n, d); //specify values yourself
		PublicKey pub = keyFactory.generatePublic(pubSpec);
		PrivateKey priv = keyFactory.generatePrivate(privSpec);

		Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
		//cipher.init(Cipher.<ENCRYPT/DECRYPT>_MODE, <pub/priv>);
		cipher.init(Cipher.DECRYPT_MODE, priv);
		byte[] pt = cipher.doFinal(ct.toByteArray());
		
		//System.out.println(new String(pt).trim());
		
		//do this to find an e value that has a gcd of 1 with phi
		int i; 
		do {
			i = 0;
			int randomValue = ThreadLocalRandom.current().nextInt(1, phi.intValue());
			 BigInteger ee = BigInteger.valueOf(randomValue);
			
				if (ee.gcd(phi).intValue() == 1) {
					
					System.out.print(ee);
					i = 1;
				}
				else {
					i = 0;
				}
				
		} while (i == 0);
		 

		 
	
		
		
		
		
		
		
		
		
		
		
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
