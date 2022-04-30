package keyDistributionEtc;

public class ReconstructShamirSecret {
	
		public static double legrange(double[] arrX, double[] arrY) 
		{
		    double result = 0;
		
		    for (int i = 0; i < arrX.length; i++) {
		    	//# of x values used are same as t needed	        
		        for (int j = 0; j < arrX.length; j++) {
		            if (j != i) {
		            	arrY[i] = arrY[i]*(-arrX[j]) / (arrX[i] - arrX[j]);
		            }
		        }
		        result += arrY[i];
		    }	 
		    return result;
		}
		 
		
		public static void main(String[] args) throws Exception
		{
		    //we have t=3 polynomial points which are: D0=(3,2578), D1=(4,3402),D2=(5,4414)
			//line up x values and their corresponding y values
		    double[] x_values= {3,4};
		    double[] y_values= {274,326};
		    
		    // use the x and y values + the number
		    System.out.print("The reconstructed secret is: " + legrange(x_values, y_values));
		}
}
