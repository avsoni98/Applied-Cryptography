package asymmetric;

public class EEATrial {

	
	 public static int gcd(int a, int b)
	    {
	        if (a == 0)
	            return b;
	          
	        return gcd(b%a, a);
	    }
	
	
	 public static int gcdExtended(int a, int b, int x, int y)
	    {
	        // Base Case
	        if (a == 0)
	        {
	            x = 0;
	            y = 1;
	            return b;
	        }
	  
	        int x1=1, y1=1; // To store results of recursive call
	        int gcd = gcdExtended(b%a, a, x1, y1);
	  
	        // Update x and y using results of recursive
	        // call
	        x = y1 - (b/a) * x1;
	        y = x1;
	  
	        return gcd;
	    }
	
	
	 
	 
	 public static void main(String[] args) throws Exception {

		System.out.print(gcd(3587, 1819));
		//System.out.print(gcdExtended(50, 30, 1, 1));
	
	}
	
	
	
	
}
