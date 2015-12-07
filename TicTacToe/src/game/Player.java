package game;

public class Player {
	private int wins = 0;
	private int losses = 0;
	private int ties = 0;
	private String playerName = "P1";
	
	public Player(String name) {
		playerName = name;
	}
	
	public String getName() {
		return playerName;
	}
	
	public void win() {
		wins++;
	}
	
	public void lose() {
		losses--;
	}
	
	public void tied() {
		ties++;
	}
	
	public int getWins() {
		return wins;
	}
	
	public int getLosses() {
		return losses;
	}
	
	public int getTies() {
		return ties;
	}
}
