package hash;

import java.security.spec.X509EncodedKeySpec;

public class BirthdayProblemQ5 {

	
	public static double fact(int num){
        double fact=1, i;
        for(i=1; i<=num; i++){
            fact = fact*i;
        }
        return fact;
    }
	
	public static double combination(int n, int r){
		return (fact(n)/(fact(n-r)*fact(r)));
    }
	
	
	public static String probability(int n, int r, int days) {
		
		int exponent = r-1;
		final double e = 2.71828;
		double nCr = (int) combination(n, r);
		
		double division = -(nCr)/(Math.pow(days, exponent));
		double answer_prob = 1-Math.pow(e, division);

		double percent = answer_prob*100;
		
		String answer_percent = percent + " %";
		
		return answer_percent;
	}
	
	
	
	public static void main(String[] args) throws Exception {
	
	//int n = 33; //amount of people in a room
	//int r = 2; //people that share the same birthday
	//int days = 365; //days in a year
	
	int n = 64; //amount of people in a room
	int r = 2; //people to collide with that share the same birthday
	int days = 50000000; //days in a year
	System.out.print(probability(n, r, days)); //probability in decimals, multiply by 100 to get percent

	
	
	
	
	
	//int exponent = r-1;
	//final double e = 2.71828;
	//double nCr = (int) combination(n, r);
	//double division = -(nCr)/(Math.pow(days, exponent));
	//double answer = 1-Math.pow(e, division);
	
	
	
	//Coding question activity 4: 
	//Show that the probability becomes greater than 50% when x = 22.48 i.e. 23 people
	//double x = 22.48;
	//int share = 2;
	
	//for(int i = 1; i < x+1; i++) {
		
	//System.out.print("\n" + i + ": " + probability(i, 2, days));
		
		
	//}
	
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
