package introCompSecurity;

import java.math.BigInteger;
import java.net.PasswordAuthentication;

public class Bruteforcepassword {

	public static double searchSpace(double alphabet_space, double max_passlength) {
		
		double result = 0;
		for (int i = 1; i < max_passlength+1; i++) {
		result =+ (((Math.pow(alphabet_space, i+1))-1)/(alphabet_space-1))-1;
		}
		return result;
	}
	
	
	
	
	
	public static void main(String[] args) throws Exception {

		//lowercase = a-z = 26
		//uppercase = A-Z = 26
		//numbers = 0-9 = 10
		//TOTAL Printable ASCII characters = 94
		
		
		double alphabet_space = 26; //Change depending on value of A
		double max_passlength = 3; //Change depending on the MAX PASSWORD LENGTH
		
		System.out.print("The search space is: " + searchSpace(alphabet_space, max_passlength));
	
	
	}
	
	
	
}
