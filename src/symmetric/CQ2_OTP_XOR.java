package symmetric;

import util.CryptoTools;

public class CQ2_OTP_XOR {
	
	
	
	public static byte[] bitwiseXOR(byte[] b1, byte[] b2){ //XOR two given byte arrays
		byte[] bt = new byte[b1.length]; 
		for(int i=0; i< b1.length; i++ ){
				
					bt[i] = (byte) (b1[i] ^ b2[i]);
		}
		return bt;
	}
	
	
	public static void main(String[] args) throws Exception {
		
		//
	
		byte[] AliceEncrypt = CryptoTools.hexToBytes("0A4F0C08003503492F247442105B5757"); //only encrypted with Alice key
		byte[] BobAliceEncrypt = CryptoTools.hexToBytes("5E2769286B507A69494B066252343579"); //encrypted with Alice and Bob key
		byte[] AliceDecrypt = CryptoTools.hexToBytes("170708454B1116002A2E2111725F5000"); //decrypted with Alice's key, only Bob's key remains
				
		byte[] AliceKey = bitwiseXOR(BobAliceEncrypt, AliceDecrypt); //XOR the Encryption of Alice + Bob with the Decryption of Alice
		byte[] AliceKeyMessage = bitwiseXOR(AliceEncrypt, AliceKey); //XOR Alice's encryption message with the her decryption 
		byte[] BobsKey = bitwiseXOR(AliceKeyMessage, BobAliceEncrypt);
		
		
		System.out.print(new String(AliceKey));
		System.out.print("\n" + new String(AliceKeyMessage));
		//System.out.print(new String(BobsKey));


		
		
		
		
		
	}
	
	
	
		
	
	
	
	
	
	

}
