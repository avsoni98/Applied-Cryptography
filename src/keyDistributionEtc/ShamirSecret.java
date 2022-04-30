package keyDistributionEtc;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class ShamirSecret {


    //calls upon Polynomial class
	public static void main(String[] args) throws Exception
	{
			int n_participants = 6; //number of participants TOTAL
	        int t = 3; //any t number of people can extract the secret
	        int secret = 44; //secret
	    
	        Polynomial secret_poly = new Polynomial(secret, 0); //secret coefficient w/ degree 0

	        int degree = 1;
	        ArrayList<Polynomial> arr = new ArrayList<Polynomial>();
	     	for (int i = 0; i < t-1; i++) {
	     		arr.add(new Polynomial(ThreadLocalRandom.current().nextInt(1, 100+1), degree));
	     		degree++;
	     	}
	     	arr.add(0, secret_poly);
	     	
	        Polynomial base = new Polynomial(0, 0);
	     	for (int j = 0; j < arr.size(); j++) { 
	         	base = base.plus(arr.get(j));
	     	}
	     	System.out.print(base + "\n");
        
     	//this is how we get the n number of points based on how many paritipants there are
     	for (int i = 1; i < n_participants+1; i++) {
        	System.out.println("("+i + ", " + base.evaluate(i) + ")");
        }  
	}
	
	
	
	
	
	
	
	}
		
	
	
	
