package hash;

public class SimpleBirthdayParadox {

	
	//Probability that 2 people in a room of x people where there can be 365 birthdays have the same birthday
	
	public static double simpleBirthday2People(double r, double t) {
		
		double sum = 1;
		
		for(double i = 0; i < r; i++) {
			
			sum*=(1-(i/t));
			
		}
		
		return 1-sum;
		
	}
	
	
	
	
	
	
	
	public static void main(String[] args) throws Exception {

		//collision with at least 1 OTHER PERSON (2 people total) 
		double r = 24; //number of people in a room
		double t = 365; //mod10000
		//simpleBirthday2People(r, t);
		
		System.out.print(simpleBirthday2People(r, t));
		
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
