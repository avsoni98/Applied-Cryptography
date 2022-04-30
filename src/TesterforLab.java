import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class TesterforLab {
	public static void main(String[] args)
    {
        // Create a list of strings
		Queue<String> open = new LinkedList<>();
		open.add("initial");
		open.add("nothinighere");

	    ArrayList<String> visited = new ArrayList<>();
	    visited.add(open.remove());
	    
	    List<String> n = new LinkedList<>();

        // Let us print the sorted list
        System.out.println(open);
        
        
    }
}
