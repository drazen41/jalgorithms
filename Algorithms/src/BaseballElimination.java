import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BaseballElimination {
	
	private class Team {
		private int key;
		private String name;
		private int wins;
		private int loss;
		private int left;
		private int[] games;
		private Team(String name, int wins, int loss, int left, int[]games, int key) {
			this.key = key;
			this.games = new int[games.length];
			this.wins = wins;
			this.loss = loss;
			this.left = left;
			this.name = name;
			for (int i = 0; i < games.length; i++) {
				this.games[i] = games[i];
			}
			
		}
		private Team() {
			
		}
	}
	private FlowNetwork flowNetwork ;
	private Bag<String> eliminated;
	//private Bag<Team> teams;
	private int[][]games;
	private Team[] teams;
	private Team[] teamNetwork;
	private int numberOfGames=0;
	private int source;
	private int target;
	public BaseballElimination(String filename) {
		// TODO Auto-generated constructor stub
		//teams = new Bag<Team>();
		
		In in = new In(filename);
		while (!in.isEmpty()) {
			int n = in.readInt();
			teams = new Team[n];
			this.games = new int[n][n];
			Team team = null;
			
			for (int i = 0; i < n; i++) {
				String name = in.readString();
				int wins = in.readInt();
				int loss = in.readInt();
				int left = in.readInt();
				int[]games = new int[n];
				team = new Team();
				team.key = i;
				team.wins = wins;
				team.loss = loss;
				team.left = left;
				team.name = name;
				for (int j = 0; j < n; j++) {
					if (j > i) {
						numberOfGames++;
					}
					int capacity = in.readInt();
//					if (i == j) {
//						games[j] = capacity;
//						continue;
//					}
//					games[j] = capacity;
					this.games[i][j]=capacity;
				}

				team.games = games;
				//teams.add(team);
				teams[i] = team;
			}
			
			
			
		}
	}
	
	public int numberOfTeams() {  // number of teams
		return teams.length;
	}
	public Iterable<String> teams() {                                // all teams
		Bag<String> names = new Bag<String>();
		for (Team team : this.teams) {
			names.add(team.name );
		}
		return names;
		
	}
	
	private Team team(String team) {
		if (team == null) {
			return null;
		}
		for (Team tim : teams) {
			String name = tim.name;
			if (name.equals(team)) {
				return tim;
			}
		}
		return null;
	}
	public int wins(String team) {                     // number of wins for given team
		Team tim = team(team);
		if (tim == null) {
			throw new IllegalArgumentException();
		}
		return tim.wins ;
	}
	public int losses(String team) {                    // number of losses for given team
		Team tim = team(team);
		if (tim == null) {
			throw new IllegalArgumentException();
		}
		return tim.loss;
	}
	public int remaining(String team) {                 // number of remaining games for given team
		Team tim = team(team);
		if (tim == null) {
			throw new IllegalArgumentException();
		}
		return tim.left;
	}
	public int against(String team1, String team2) {    // number of remaining games between team1 and team2
		Team tim1 = team(team1);
		Team tim2 = team(team2);
		if (tim1 == null || tim2 == null) {
			throw new IllegalArgumentException();
		}
		int remaining = this.games[tim1.key][tim2.key];
		return remaining;
	}
	public boolean isEliminated(String team) {              // is given team eliminated?
		Team tim = team(team);
		if (tim == null) {
			throw new IllegalArgumentException();
		}
		eliminated = new Bag<String>();
		// trivial
		int i = 0;
		teamNetwork = new Team[teams.length-1];
		for (Team team2 : teams) {
			if (team2.name.equals(tim.name )) {
				continue;
			}
			if (team2.wins > tim.wins + tim.left ) {
				eliminated.add(team2.name);
				return true;
			}
			teamNetwork[i] = team2;
			i++;
		}
		// nontrivial
		createFlowNetworkFor(tim);
		FordFulkerson fordFulkerson = new FordFulkerson(flowNetwork , source, target);
//		StdOut.println(team + " maxflow: " + fordFulkerson.value());
		for (int j = 0; j < teams.length; j++) {
			if (tim.key == j) {
				continue;
			}
			if (fordFulkerson.inCut(j)) {
				eliminated.add(teams[j].name);
				
			}
		}
        if (!eliminated.isEmpty()) {
			return true;
		}  
		return false;
	}
	private void createFlowNetworkFor(Team tim) {
		int n = teams.length;
		int numberOfVertices = numberOfGames + 3;
		int s= numberOfGames+1;
		int t= numberOfGames+2;
		source = s;
		target = t;
		//int j = n-1;
//		StdOut.println("Flow network for: " + tim.name);
		flowNetwork = new FlowNetwork(numberOfVertices);
		int teamInd = 0;
		for (int i = 0; i < teams.length; i++) {
			if (teams[i].name.equals(tim.name)) {
				teamInd = i;
			}
		}
		for (int i = 0; i < games.length; i++) {
			if (i == teamInd) {
				continue;
			}
			Team team = teams[i];
			int teamCapacity = tim.wins + tim.left - team.wins;
			FlowEdge targetFlow = new FlowEdge(i,t,teamCapacity);
			flowNetwork.addEdge(targetFlow);
			for (int j = 0; j < games.length; j++) {
				if (j == teamInd || i == j || j < i) {
					continue;
				}
				int to1 = i;
				int to2 = j;
				FlowEdge gameFlow1 = new FlowEdge(n,to1,Double.POSITIVE_INFINITY);
				FlowEdge gameFlow2 = new FlowEdge(n,to2,Double.POSITIVE_INFINITY);
				FlowEdge sourceFlow = new FlowEdge(s,n,games[i][j]);
				flowNetwork.addEdge(gameFlow1);
				flowNetwork.addEdge(gameFlow2);
				flowNetwork.addEdge(sourceFlow);				
				n++;
			}
		}
//		StdOut.println(flowNetwork.toString());
	}

	public Iterable<String> certificateOfElimination(String team) {  // subset R of teams that eliminates given team; null if not eliminated
		Team tim = team(team);
		if (tim == null) {
			throw new IllegalArgumentException();
		}
		if (isEliminated(team)) {
			return eliminated;
		}
		return null;
	}

}
