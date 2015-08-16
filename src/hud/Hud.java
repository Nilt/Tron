package hud;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import assets.ColorPallette;
import assets.SpriteSheet;
import main.Tron;
import screens.Level;

public class Hud {
	
	private final int OFFSET = 20;
	
	private Level level;
	
	PlayerScoreBoard player1;
	PlayerScoreBoard player2;
	PlayerScoreBoard player3;
	PlayerScoreBoard player4;
	PlayerScoreBoard[] players;
	
	public Hud(Level level) {
		this.level = level;
		players = new PlayerScoreBoard[level.getAmountOfPlayers()];
		System.out.println(players.length);
		for(int i = 0; i<players.length; i++) {
			players[i] =  new PlayerScoreBoard(this, i+1, Tron.WIDTH - (Tron.WIDTH / 5) * (4-i) - OFFSET, 60);
		}

	}
	
	public void render(Graphics g) {
		g.setColor(Color.black);
		
		g.fillRect(0, 0, Tron.WIDTH, level.getNorthBorder());
		g.fillRect(0, 0, level.getWestBorder(), Tron.HEIGHT);
		g.fillRect(0,level.getSouthBorder() + Tron.PIXELSIZE * 20+1, Tron.WIDTH, Tron.HEIGHT);
		g.fillRect(level.getEastBorder() + Tron.PIXELSIZE*3+1,0, Tron.WIDTH, Tron.HEIGHT);
		borderRender(g);
		
		renderPlayerScoreBoards(g);
	}

	public void borderRender(Graphics g) {
		g.setColor(Color.white);
		try{
			g.setColor(level.getHighScorePlayer().getColor());
		} catch(IndexOutOfBoundsException e) {}
		g.drawRect(level.getWestBorder(), level.getNorthBorder(), level.getEastBorder(), level.getSouthBorder());
	}
	
	public void renderPlayerScoreBoards(Graphics g) {
		for(int i = 0; i< players.length; i++) {
			players[i].render(g);
		}
	}
	
	public Level getLevel() {
		return level;
	}

}
