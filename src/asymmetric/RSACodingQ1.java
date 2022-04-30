package asymmetric;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

public class RSACodingQ1 {

	//returns the BigInteger plaintext pt given n, p, e, and the ciphertext, ct
	public static BigInteger manualDecryptRSA(BigInteger n, BigInteger p, BigInteger e, BigInteger ct) {
		
		BigInteger one = new BigInteger("1");
		BigInteger q = n.divide(p); //calculate q given n and p. n = (p)(q)
	    BigInteger phi = p.subtract(one).multiply(q.subtract(one));
	    BigInteger d = e.modInverse(phi); //Given e, have to calculate for d. (d)(e) = 1 mod(phi)
		
	    BigInteger pt = ct.modPow(d, n);	//manually get the plaintext by performing this last step    
	        
		return pt;
				
	}
	
		
	public static void main(String[] args) throws Exception { 
		//Your RSA keys are as follows (all expressed as big integers)
		//Determine the plaintext that was sent to you. Notice that since no padding was used, you may need to trim the plaintext before printing it.
	BigInteger n = new BigInteger("94587468335128982981605019776781234618384857805657005686084562260910788622013722070926491690843853690071248130134427832324966728582532832363221542231787068203763027067400082835394459857525017707284768411819006776211493735326500782954621660256501187035611332577696332459049538105669711385995976912007767106063");
	BigInteger e = new BigInteger("74327");
	BigInteger d = new BigInteger("7289370196881601766768920490284861650464951706793000236386405648425161747775298344104658393385359209126267833888223695609366844098655240542152017354442883676634193191857568369042999854440242050353181703706753485749165295123694487676952198090537385200990850805837963871485320168470788328336240930212290450023");
	
	BigInteger ct = new BigInteger("87014856975716299121085087309577038316883175412853820115551293556230488405826385706604303724175236985573832006395540199066061101502996745421485579743246846982636317440505885092956723199407403632041108913018671613508572002898008615700858579079601105011909417884801902333329415712320494308682279897714456370814");
	
	
	KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	RSAPublicKeySpec pubSpec = new RSAPublicKeySpec(n, e); //specify values yourself
	RSAPrivateKeySpec privSpec = new RSAPrivateKeySpec(n, d); //specify values yourself
	PublicKey pub = keyFactory.generatePublic(pubSpec);
	PrivateKey priv = keyFactory.generatePrivate(privSpec);

	Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
	//cipher.init(Cipher.<ENCRYPT/DECRYPT>_MODE, <pub/priv>);
	cipher.init(Cipher.DECRYPT_MODE, priv);
	byte[] pt = cipher.doFinal(ct.toByteArray());
	
	System.out.println(new String(pt).trim());
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
