package asymmetric;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import util.CryptoTools;

public class ManualRSAExample {
	
	//returns the EXPONENT k for n DONT' USE
	public static int FindExponentK(BigInteger n) {
		
		//BigInteger n = new BigInteger("53");
		BigInteger one = new BigInteger("1");
		BigInteger two = new BigInteger("2");
		int k = 0;
		int i = 0;
		do {
			BigInteger base = two.pow(k);
			BigInteger subtractOne = n.subtract(one);
			BigInteger m = subtractOne.divide(base);
			double check = subtractOne.doubleValue()/base.doubleValue();
			
				if (check%1 != 0) {
				//System.out.print("yes");
				i = 1; 
				}
				else {
				//System.out.print(" no");
				i = 0;
				k++;
				}
				
		} while(i == 0);
		
		return k-1;
	}
	
	



			public static void main(String[] args) throws Exception {



			BigInteger phi = new BigInteger("8584037913642434144111279062847405921823163865842701785008602377400681495147541519557274092429073976252689387304835782258785521935078205581766754116919200");
			BigInteger e = new BigInteger("65537");
			BigInteger q = new BigInteger("87020952829623092932322362936864583897972618059974315662422560067745889600571");
			BigInteger ct = new BigInteger("2860343511650624855536801423229241360270155261818891412133738950638333577677444735030337910529513416732869176248688449871162754270700440751893288037477549");
							

			BigInteger one = new BigInteger("1");
			BigInteger p = (phi.divide(q.subtract(one))).add(one);
			BigInteger n = p.multiply(q);
			BigInteger d = e.modInverse(phi); //Given e, have to calculate for d. (d)(e) = 1 mod(phi)
			BigInteger pt = ct.modPow(d, n);	//manually get the plaintext by performing this last step    
			BigInteger trial = ct.modPow(d, n);	//manually get the plaintext by performing this last step 
			
			System.out.println(new String(trial.toByteArray()).trim());

		





		}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
