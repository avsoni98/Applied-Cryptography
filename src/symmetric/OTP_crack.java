package symmetric;

import java.util.Arrays;

import util.CryptoTools;

public class OTP_crack {
	

	public static byte[] bitwiseXOR(byte[] b1, byte[] b2){ //XOR two given byte arrays
		byte[] bt = new byte[b1.length]; 
		for(int i=0; i< b1.length; i++ ){
				bt[i] = (byte) (b1[i] ^ b2[i]);
		}
		return bt;
	}

	

	

	public static void main(String[] args) throws Exception {
		//compare known word by doing XOR with the two ciphertexts XORed with each other that use the SAME KEY
		byte[] CT1 = CryptoTools.hexToBytes("3D48044D421349564A1541054204131C"); //uses same OTP key
		byte[] CT2 = CryptoTools.hexToBytes("3D54024D531442454C0941175404150A"); //uses same OTP key
		byte[] word = "bridge".getBytes();


		byte[] PT1 = bitwiseXOR(CT1, CT2); //XOR the two ciphertext messages



		for(int i = 0; i < PT1.length; i++) {
			
		int end = word.length + PT1.length;
		byte[] check = Arrays.copyOfRange(PT1, i, end);
		byte[] finalXOR = bitwiseXOR(word, check); //XOR at each position 
		System.out.print("   " + new String(finalXOR));


		}

	}

	
}