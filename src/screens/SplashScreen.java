package screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import sound.AudioController;
import main.DrawPanel;
import main.Tron;

public class SplashScreen {

	BufferedImage[] logoSplash;
	private int index;
	private float alpha;

	private long time;
	
	private boolean fadeIn;
	private int clockEnd;

	DrawPanel dp;
	
	public SplashScreen(DrawPanel dp) {
		this.dp = dp;
		index = 0;
		alpha = 255;
		initImage();
		time = System.currentTimeMillis();
		AudioController.playSplash();
		fadeIn = false;
		clockEnd = 0;
	}

	public void initImage() {
		logoSplash = new BufferedImage[6];
		try {
			logoSplash[0] = ImageIO.read(new File(
					"Assets/Images/logoSplash/Tron1.png"));
			logoSplash[1] = ImageIO.read(new File(
					"Assets/Images/logoSplash/Tron2.png"));
			logoSplash[2] = ImageIO.read(new File(
					"Assets/Images/logoSplash/Tron4.png"));
			logoSplash[3] = ImageIO.read(new File(
					"Assets/Images/logoSplash/Tron1.png"));
			logoSplash[4] = ImageIO.read(new File(
					"Assets/Images/logoSplash/Tron4.png"));
			logoSplash[5] = ImageIO.read(new File(
					"Assets/Images/logoSplash/Tron2.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void render(Graphics g) {
		waitTime();

		g.drawImage(logoSplash[index],
				Tron.WIDTH / 2 - logoSplash[0].getWidth() / 2, Tron.HEIGHT / 2
						- logoSplash[0].getHeight() / 2, null);
		
		if(!fadeIn)
			fadeOut();
		else fadeIn();

		g.setColor(new Color(0, 0, 0, (int)alpha));
		g.fillRect(0, 0, Tron.WIDTH, Tron.HEIGHT);
	}
	
	public void fadeOut() {
		if (alpha > 0) {
			alpha-= 5f ;
		} else if(alpha <= 20f) {
			fadeIn = true;
		}
	}
	
	public void fadeIn() {
		if (alpha < 250) {
			alpha+= 1.5f ;
		} else if(alpha >= 250) {
			clockEnd++;
		}
		if(clockEnd == 30) {
			dp.setGameState(DrawPanel.MENU);
			dp.deleteSplashScreen();
		}
	}

	public void waitTime() {
		long deltaTime = System.currentTimeMillis();

		if (deltaTime - time > 100) {
			time = System.currentTimeMillis();
			index++;
			if (index > logoSplash.length - 1)
				index = 0;
		}
	}

}
