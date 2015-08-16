package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import screens.Level;
import screens.Menu;
import screens.SplashScreen;
import sound.AudioController;

public class DrawPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int gameState;
	public static final int SPLASH = 0;
	public static final int RUNNING = 1;
	public static final int MENU = 2;

	private int fps;

	private boolean running;

	private Debug debugLog;

	private SplashScreen sp;
	private Menu menu;
	private Level level;

	private AudioController ac;

	private ScoreHolder sh;

	public DrawPanel() {
		running = true;
		gameState = 0;
		sh = new ScoreHolder();
		debugLog = new Debug(this);
		sp = new SplashScreen(this);
		menu = new Menu(this);
		
		ac = new AudioController();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, Tron.WIDTH, Tron.HEIGHT);

		if (gameState == SPLASH)
			sp.render(g);
		else if (gameState == MENU) {
			menu.render(g);
		}
		else if (gameState == RUNNING) {
			if (!ac.getBGMusic().isPlaying()) {
				ac.playMusic();
			}
			if (level != null)
				level.render(g);
		}

		if (Tron.debug) {
			debugLog.render(g);
			
		}
		
		

	}

	public void tick() {

		if(gameState == MENU) {
			menu.tick();
		}
		else if (gameState == RUNNING)
			if (level != null)
				level.tick();
	}

	public void start() {
		long lastTime = System.nanoTime();
		final double amountOfTicks = 30.0;
		double ns = 1000000000 / amountOfTicks; // default value 1000000000
		double delta = 0;
		int updates = 0;
		long timer = System.currentTimeMillis();

		while (running) {

			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta > 1) {
				tick();
				repaint();
				updates++;
				delta--;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				fps = updates;
				updates = 0;
			}
		}
	}

	public int getFps() {
		return fps;
	}

	public void setLevel(Level level) {

		try {
			sh.setPlayer1Score(this.level.getPlayer(0).getScore());
			sh.setPlayer2Score(this.level.getPlayer(1).getScore());
			sh.setPlayer3Score(this.level.getPlayer(3).getScore());
			sh.setPlayer4Score(this.level.getPlayer(4).getScore());
		} catch (NullPointerException e) {
		}
		
		if (this.level != null)
			this.level = null;
		this.level = level;
		try {
			//System.out.println("Score etter reset: " + level.getPlayer(1).getScore());
			level.getPlayer(0).setScore(sh.getPlayer1Score());
			level.getPlayer(1).setScore(sh.getPlayer2Score());
			level.getPlayer(2).setScore(sh.getPlayer3Score());
			level.getPlayer(3).setScore(sh.getPlayer4Score());
		} catch (NullPointerException e) {
		}
	}

	public Level getLevel() {
		return level;
	}

	public void setGameState(int gameState) {
		if(this.gameState == RUNNING && gameState == MENU) {
			sh.resetScore();
		}
		this.gameState = gameState;
	}

	public int getGameState() {
		return gameState;
	}
	
	public void deleteSplashScreen() {
		sp = null;
	}
	
	public Menu getMenu() {
		return menu;
	}
	
	public ScoreHolder getScoreHolder() {
		return sh;
	}
	
	public void deleteScoreHolder() {
		sh = null;
	}
	
	public void addScoreHolder(ScoreHolder sh) {
		this.sh = sh;
	}
	
	public void deleteLevel() {
		level = null;
	}

}