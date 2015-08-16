package main;

/**
 * Score gets reset in DrawPanel in method setGameState.
 * This only happens if gameState goes from menu to running.
 * 
 * 
 * @author Nils
 *
 */

public class ScoreHolder {

	int player1Score;
	int player2Score;
	int player3Score;
	int player4Score;
	
	public ScoreHolder() {
		resetScore();
	}
	
	public void resetScore() {
		//System.out.println("Score reset");
		player1Score = 0;
		player2Score = 0;
		player3Score = 0;
		player4Score = 0;
	}
	
	public void setPlayer1Score(int player1Score) {
		this.player1Score = player1Score;
	}
	
	public int getPlayer1Score() {
		return player1Score;
	}
	
	public void setPlayer2Score(int player2Score) {
		this.player2Score = player2Score;
	}
	
	public int getPlayer2Score() {
		return player2Score;
	}
	
	public void setPlayer3Score(int player3Score) {
		this.player3Score = player3Score;
	}
	
	public int getPlayer3Score() {
		return player3Score;
	}
	
	public void setPlayer4Score(int player4Score) {
		this.player4Score = player4Score;
	}
	
	public int getPlayer4Score() {
		return player4Score;
	}
	
	public String toString() {
		return "P1: " + getPlayer1Score() + "\nP2: " + getPlayer2Score() + "\nP3: " + getPlayer3Score() +"\nP4: " + getPlayer4Score();
	}
	
}
