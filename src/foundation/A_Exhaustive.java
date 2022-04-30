package foundation;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import keyDistributionEtc.Polynomial;
import util.CryptoTools;

public class A_Exhaustive {
//AFFINE Exhaustive search for alpha and beta
//alpha is 5, beta is 14 when input into CryptoTools.bytesToFile(AffineDencrypt(ct, a, b), "data/Trial.txt");
//alpha has max 12 values to pick from that have gcd(a,26)==1	
	
	public static byte[] AffineDencrypt(byte[] ct, Integer a, Integer b)
	{
						
		byte pt[] = new byte[ct.length];
		
		BigInteger alpha = BigInteger.valueOf(a);
		BigInteger mod = BigInteger.valueOf(26);
		
		for (int i = 0; i < ct.length; i++) {
						
			pt[i] = (byte) ((char)(ct[i]  - b + 'A')*alpha.modInverse(mod).intValue() % 26 + 'A');	
					
		}
		return pt; 
	}
	
	
	public static byte[] AffineEncrypt(byte[] pt, Integer a, Integer b)
	{		
		byte ct[] = new byte[pt.length];
		
		//BigInteger alpha = BigInteger.valueOf(a);
		//BigInteger mod = BigInteger.valueOf(26);
		
		for (int i = 0; i < pt.length; i++) {
						
			ct[i] = (byte) (((char)(pt[i] * a) - 'A'+ b)  % 26 + 'A');	
					
		}
		return ct; 
	}
	
	
	
	public static double CosineSimilarity(byte[] ar) {
		double dotProduct = 0.0;
		double normA = 0.0;
		double normB = 0.0;
		
		for (int i = 0; i < CryptoTools.getFrequencies(ar).length; i++) {
			
			dotProduct += CryptoTools.getFrequencies(ar)[i] * CryptoTools.ENGLISH[i]; //ENGLISH is constant value
			normA += Math.pow(CryptoTools.getFrequencies(ar)[i], 2);
	        normB += Math.pow(CryptoTools.ENGLISH[i], 2);
		}
		return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
	}
	
	

	public static double maxCSValue(ArrayList<Double> arr) {
		
		double result = 0;
		for (int i = 0; i< arr.size(); i++){

				if (arr.get(i) > result){
				result = arr.get(i);
				}
			}
		return result;
	}
	
	
	public static void findAlphaBeta(byte[] ct) throws Exception {
		
        ArrayList<Double> arr = new ArrayList<Double>();

		for (int a = 0; a < 26; a++) {
			
			BigInteger alpha = BigInteger.valueOf(a);
			BigInteger mod = BigInteger.valueOf(26);
			//checks if the gcd of alpha and mod 26 is 1. If it is, then we can use that alpha value and then loop through b values
			if (mod.gcd(alpha).intValue() == 1) {
				
				for (int b = 0; b < 26; b++) { //beta has max 26 possible values
											
					CryptoTools.bytesToFile(AffineDencrypt(ct, a, b), "data/checkAffine.txt"); //mute if encrypting
					byte[] eh = CryptoTools.fileToBytes("data/checkAffine.txt");  //mute if encrypting
					arr.add(CosineSimilarity(eh));
					System.out.println("Alpha = " + a + " Beta = " + b +" Cosine Similarity = " + CosineSimilarity(eh)); //mute this to check decryption in txt
				}
			}
		
		 }
					System.out.print("Highest CosineSimilarity value is: " + maxCSValue(arr));

		
	}
	

	
	public static void main(String[] args) throws Exception {
		
		//For DECRYPTION of Affine
		byte[] raw = CryptoTools.fileToBytes("data/AffineCiphertext.txt"); //read the ciphertext and clean it from this file
		byte[] ct = CryptoTools.clean(raw);
		byte[] ct2 = AffineEncrypt(ct, 5, 14);
		findAlphaBeta(ct2);	//also finds the highest max cosine similarity value
		//a5/b14
		
		//System.out.print("\n" + new String(AffineDencrypt(ct2, 9, 0))); //decrypt the ciphertext in the file, input correct alpha and beta
		
		
		
		//For ENCRYPTION of Affine
		//byte[] pt = CryptoTools.fileToBytes("data/AffinePlaintext.txt");
		//System.out.print("\n" + new String(AffineEncrypt(pt, a, b))); //use this output encryption in console
				
		
		
	}
	
}
	
	
	
	
	


