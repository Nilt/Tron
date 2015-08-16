package screens;

import hud.Hud;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import assets.ColorPallette;
import effects.EffectHandler;
import main.Player;
import main.Tron;
import physics.Physics;
import powerups.PowerUpSpawner;

public class Level {

	private int pixelSize = Tron.PIXELSIZE;

	private int westBorder = pixelSize * 3;
	private int eastBorder = Tron.WIDTH - (pixelSize * 7) - 2;
	private int northBorder = pixelSize * 20;
	private int southBorder = Tron.HEIGHT - (pixelSize * 29) - 4;

	private Tron tron;
	private Physics physics;
	private EffectHandler effectHandler;
	private ArrayList<Player> players;

	private Hud hud;
	private PowerUpSpawner powerUpSpawner;

	private int amountOfPlayers;
	
	private boolean ready;
	private int countDown;
	private int countDownTimer;
	private BufferedImage[]	countDownImg;

	public Level(int amountOfPlayers) {
		this.tron = tron;
		this.amountOfPlayers = amountOfPlayers;
		physics = new Physics();
		effectHandler = new EffectHandler();
		players = new ArrayList<Player>();
		addPlayers();
		powerUpSpawner = new PowerUpSpawner(players, this);
		hud = new Hud(this);
		ready = false;
		countDown = 0;
		countDownTimer = 0;
		initCountDownImages();
	}

	public void addPlayers() {
		if (amountOfPlayers >= 1)
			players.add(new Player(this, 1, pixelSize * 20, pixelSize * 25,
					pixelSize));

		if (amountOfPlayers >= 2)
			players.add(new Player(this, 2, pixelSize * 140, pixelSize * 101,
					pixelSize));

		if (amountOfPlayers >= 3)
			players.add(new Player(this, 3, pixelSize * 20, pixelSize * 101,
					pixelSize));

		if (amountOfPlayers >= 4)
			players.add(new Player(this, 4, pixelSize * 140, pixelSize * 25,
					pixelSize));

	}
	
	public void initCountDownImages() {
		countDownImg = new BufferedImage[20];
		
		try {
			countDownImg[0] = ImageIO.read(new File(
					"Assets/Images/CountDown/3_1.png"));
			countDownImg[1] = ImageIO.read(new File(
					"Assets/Images/CountDown/3_2.png"));
			countDownImg[2] = ImageIO.read(new File(
					"Assets/Images/CountDown/3_3.png"));
			countDownImg[3] = ImageIO.read(new File(
					"Assets/Images/CountDown/3_4.png"));
			countDownImg[4] = ImageIO.read(new File(
					"Assets/Images/CountDown/3_5.png"));
			
			
			countDownImg[5] = ImageIO.read(new File(
					"Assets/Images/CountDown/2_1.png"));
			countDownImg[6] = ImageIO.read(new File(
					"Assets/Images/CountDown/2_2.png"));
			countDownImg[7] = ImageIO.read(new File(
					"Assets/Images/CountDown/2_3.png"));
			countDownImg[8] = ImageIO.read(new File(
					"Assets/Images/CountDown/2_4.png"));
			countDownImg[9] = ImageIO.read(new File(
					"Assets/Images/CountDown/2_5.png"));
			
			countDownImg[10] = ImageIO.read(new File(
					"Assets/Images/CountDown/1_1.png"));
			countDownImg[11] = ImageIO.read(new File(
					"Assets/Images/CountDown/1_2.png"));
			countDownImg[12] = ImageIO.read(new File(
					"Assets/Images/CountDown/1_3.png"));
			countDownImg[13] = ImageIO.read(new File(
					"Assets/Images/CountDown/1_4.png"));
			countDownImg[14] = ImageIO.read(new File(
					"Assets/Images/CountDown/1_5.png"));
			countDownImg[15] = ImageIO.read(new File(
					"Assets/Images/CountDown/GO.png"));
			countDownImg[16] = countDownImg[15];
			countDownImg[17] = countDownImg[16];
			countDownImg[18] = countDownImg[17];
			countDownImg[19] = countDownImg[18];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

	public void tick() {
		powerUpSpawner.tick();
		if(ready) {
			playersTick();
			effectHandler.tick();
			physics.collisionPhysics();
		} else {
			countDownTimer++;
			if(countDownTimer % 4 == 0 && countDown < countDownImg.length) {
				countDown++;
				if(countDown == countDownImg.length-1) {
					ready = true;
					countDown = 3;
					countDownTimer = 0;
				}
			}
		}
	}

	public void playersTick() {
		for (int player = players.size() - 1; player >= 0; player--) {
			players.get(player).tick();
		}
	}

	public void render(Graphics g) {
		groundRender(g);
		effectHandler.render(g);
		playersRender(g);
		powerUpSpawner.render(g);
		hud.render(g);
		if (Tron.debug)
			renderDebug(g);
		if(!ready) {
			//g.drawString("" + countDown, Tron.WIDTH/2, Tron.HEIGHT/2);
			g.drawImage(countDownImg[countDown], Tron.WIDTH / 2 -countDownImg[countDown].getWidth()/2, Tron.HEIGHT / 2 -countDownImg[countDown].getHeight()/2, null);
		}
	}

	public void groundRender(Graphics g) {
		g.setColor(ColorPallette.groundColor3);
		for (int x = 0; x < Tron.WIDTH; x += Tron.PIXELSIZE) {
			for (int y = 0; y < Tron.HEIGHT; y += Tron.PIXELSIZE) {
				g.drawLine(x, y, Tron.WIDTH, y);
				g.drawLine(x, y, x, Tron.HEIGHT);
			}
		}
	}

	public void playersRender(Graphics g) {
		for (int player = players.size() - 1; player >= 0; player--) {
			players.get(player).render(g);
		}
	}

	public void renderDebug(Graphics g) {
		physics.render(g);
		g.setColor(Color.green);
		g.drawString("Physics objects: " + physics.getPhysicsSize(), 20, 400);
	}

	public void removePlayer(Player playerToRemove) {
		players.remove(playerToRemove);
	}

	public int getWestBorder() {
		return westBorder;
	}

	public int getEastBorder() {
		return eastBorder;
	}

	public int getNorthBorder() {
		return northBorder;
	}

	public int getSouthBorder() {
		return southBorder;
	}

	public EffectHandler getEffectHandler() {
		return effectHandler;
	}

	public Physics getPhysics() {
		return physics;
	}

	public Tron getTron() {
		return tron;
	}

	public int getAmountOfPlayers() {
		return amountOfPlayers;
	}

	public Player getPlayer(int index) {
		try {
			return players.get(index - 1);
		} catch (IndexOutOfBoundsException e) {
			//System.out.println(e);
		}
		return null;
	}

	public Player getHighScorePlayer() {
		Player p = players.get(0);
		for (int i = 0; i < players.size(); i++) {
			if (p.getScore() < players.get(i).getScore())
				p = players.get(i);
		}

		return p;
	}

}
