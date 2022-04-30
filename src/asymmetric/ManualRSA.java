package asymmetric;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class ManualRSA {
	

	
	public static void main(String[] args) throws Exception {
				//n and e are used to encrypt
				//phi and d are used to decrypt
		
		
				BigInteger phi = new BigInteger("40");
				BigInteger e = new BigInteger("7");
		

				BigInteger d = e.modInverse(phi);
				        
				System.out.print(d);
	
	 
	
	}
	
	

}
