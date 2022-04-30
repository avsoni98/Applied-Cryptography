package asymmetric;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


//using the Miller-Rabin test and the base 2. Write Composite or Inconclusive as your answer.

public class PrimalityCodingQ4 {

	//returns the EXPONENT k for n
	public static int FindExponentK(BigInteger n) {
		
		BigInteger one = new BigInteger("1");
		BigInteger subOne = n.subtract(one);
		int k = 0;
		
		while (subOne.mod(BigInteger.TWO.pow(k)) == BigInteger.ZERO) {
			k++;
		}
		
		return k-1;
	}
	
	//KEEP IN MIND THAT -1 WILL SHOW UP AS N-1 I.E. IF N=53 THEN -1 WILL BE 52. 
	public static String introducedA(BigInteger n) {
		
		String result = "";
		
		//Getting m using the correct k from FindExponentK
		int k = FindExponentK(n);
		BigInteger zero = new BigInteger("0");
		BigInteger one = new BigInteger("1");
		BigInteger two = new BigInteger("2");
		BigInteger baseK = two.pow(k);
		BigInteger subtractOne = n.subtract(one);
		BigInteger m = subtractOne.divide(baseK); //retrieved m
		

		
		//for (int i = 0; i < k; i++) {
		
		//int valueA = ThreadLocalRandom.current().nextInt(2, n.intValue()-1); //choose a random 'a' value, from 2 to n-2 
		BigInteger a = new BigInteger("2"); //base a
		BigInteger b0 = a.modPow(m, n);
		
		if (b0.compareTo(one) == 0 || b0.compareTo(subtractOne) == 0) {
		result =  "Inconclusive - step 3 - probably prime"; //we only used ONE predetermined 'a' value, so that's why it's inconclusive and not probably prime
		}
		
		else {
				BigInteger b1 = b0;	
				while(!(b1.compareTo(one) == 0 || b1.compareTo(subtractOne) == 0)) {		
				b1 = b1.modPow(two, n);
				}
				if (b1.compareTo(one) == 0) {
					System.out.print("surely composite"); //if while-loop goes on infinitely, it is surely composite.
				}
				else if (b1.compareTo(subtractOne) == 0){
				System.out.print("Inconclusive - step 4 - probably prime");
				}
		}
						
	
		return result;
	
}
	
	
	public static void main(String[] args) throws Exception {
	
	//BigInteger n = new BigInteger("1033931178476059651954862004553");
	
	//System.out.println(FindExponentK(new BigInteger("53")));
	
	System.out.println(introducedA(new BigInteger("1033931178476059651954862004553")));

	//int numb = new BigInteger("1033931178476059651954862004553").bitLength();
	//Random rndRandom = new Random();
	//BigInteger trial = BigInteger.probablePrime(numb, rndRandom);
	//System.out.print(trial);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
