import edu.princeton.cs.algs4.StdOut;

public class BaseballTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub				
		BaseballElimination division = new BaseballElimination(args[0]);
		StdOut.println(division.wins("Montreal"));
		StdOut.println(division.losses("Montreal"));
		StdOut.println(division.remaining("Montreal"));
		StdOut.println(division.isEliminated("Montreal"));
	    for (String team : division.teams()) {
	        if (division.isEliminated(team)) {
	            StdOut.print(team + " is eliminated by the subset R = { ");
	            for (String t : division.certificateOfElimination(team)) {
	                StdOut.print(t + " ");
	            }
	            StdOut.println("}");
	        }
	        else {
	            StdOut.println(team + " is not eliminated");
	        }
	    }
	}

}
