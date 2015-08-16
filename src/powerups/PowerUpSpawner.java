package powerups;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import main.Player;
import screens.Level;

public class PowerUpSpawner {

	private ArrayList<PowerUp> powerUps;
	private ArrayList<Player> players;
	private Level level;
	private Random rand;
	
	public PowerUpSpawner(ArrayList<Player> players, Level level) {
		this.players = players;
		this.level = level;
		rand = new Random();
		powerUps = new ArrayList<PowerUp>();
		spawnPowerUp();
		
		
	}
	
	public void tick() {
		for(int i = powerUps.size() -1 ; i >= 0; i--) {
			powerUps.get(i).tick();
		}
		playerHitPowerUp();
		
	}
	
	public void playerHitPowerUp() {
		for(int player = players.size() - 1; player >= 0; player--) {
			for(int powerUp = powerUps.size()- 1; powerUp >= 0; powerUp--) {
				if(players.get(player).getBounds().intersects(powerUps.get(powerUp).getBounds())) {
					players.get(player).increaseLength();
					players.get(player).setScore(1);
					powerUps.get(powerUp).destroy(players.get(player).getColor());
					spawnPowerUp();
				}
			}
		}
	}
	
	public void spawnPowerUp() {
		int y = rand.nextInt(level.getSouthBorder() - level.getNorthBorder()) + level.getNorthBorder();
		int x = rand.nextInt(level.getEastBorder() - level.getWestBorder()) + level.getWestBorder();
		powerUps.add(new PowerUp(this,x, y));
	}
	
	public void render(Graphics g) {
		for(int i = powerUps.size() - 1; i >= 0; i--) {
			powerUps.get(i).render(g);
		}
	}
	
	public void removePowerUp(PowerUp removeThisPowerUp) {
		powerUps.remove(removeThisPowerUp);
	}
	
	public Level getLevel() {
		return level;
	}
	
}
