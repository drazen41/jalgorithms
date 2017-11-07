import java.util.Iterator;

import javax.xml.transform.Templates;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class BaseballElimination {
	
	private class Team {
		private int key;
		private String name;
		private int wins;
		private int loss;
		private int left;
		private int[] games;
		private Bag<FlowEdge> gameFlows;
		private FlowNetwork flowNetwork ;
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
	private Bag<Team> teams;
	public BaseballElimination(String filename) {
		// TODO Auto-generated constructor stub
		teams = new Bag<Team>();
		In in = new In(filename);
		while (!in.isEmpty()) {
			int n = in.readInt();
			int numberOfGames = games(n-1);
			Team team = null;
			int s= -45;
			int t= -54;
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
				team.gameFlows = new Bag<FlowEdge>();
				
				team.flowNetwork = new FlowNetwork(numberOfGames + n + 1);
//				for (int j = 0; j < n; j++) {
//					int capacity = in.readInt();
//					if (i == j) {
//						games[j] = capacity;
//						continue;
//					}
//					FlowEdge flowEdge = new FlowEdge(i,j,capacity);
//					games[j] = capacity;
//					team.gameFlows.add(flowEdge);
//				}
				for (int j = 0; j < n; j++) {
					int capacity = in.readInt();
					if (i == j || j < i) {
						games[j] = capacity;
						continue;
					}
					FlowEdge gameFlow = new FlowEdge(i,j,capacity);
					team.flowNetwork.addEdge(gameFlow );
				}
				
				teams.add(team);
				team.flowNetwork.toString();
			}
			
			
			
		}
	}
	private int games(int n) {
		if (n == 1) {
			return 1;
		} 
		return n * games(n-1);
	}
	public int numberOfTeams() {  // number of teams
		return teams.size();
	}
	public Iterable<String> teams() {                                // all teams
		Bag<String> names = new Bag<String>();
		for (Team team : this.teams) {
			names.add(team.name );
		}
		return names;
		
	}
	private boolean teamsContains(String team) {
		for (String tim : teams()) {
			if (tim.equals(team)) {
				return true;
			}
		}
		return false;
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
		
		return 0;
	}
	public boolean isEliminated(String team) {              // is given team eliminated?
		Team tim = team(team);
		if (tim == null) {
			throw new IllegalArgumentException();
		}
		// trivial
		for (Team team2 : teams) {
			if (team2.name.equals(tim.name )) {
				continue;
			}
			if (team2.wins > tim.wins + tim.left ) {
				return true;
			}
		}
		// nontrivial
		
		for (Team team2 : teams) {
			if (team2.name.equals(tim.name )) {
				continue;
			}
			
		}
		
		return false;
	}
	public Iterable<String> certificateOfElimination(String team) {  // subset R of teams that eliminates given team; null if not eliminated
		Team tim = team(team);
		if (tim == null) {
			throw new IllegalArgumentException();
		}
		return null;
	}

}
