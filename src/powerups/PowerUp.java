package powerups;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import effects.ExplosionEffect;
import physics.PhysicsObject;
import sound.AudioController;
import main.Player;
import main.Tron;

public class PowerUp extends PhysicsObject {
	
	private PowerUpSpawner powerUpSpawner;
	private int x;
	private int y;
	private int clock;
	private long time;
	
	private Color color1 = new Color(255, 250, 138);
	private Color color2 = new Color(248, 241, 5);
	private Color color3 = new Color(232, 197, 0);
	
	public PowerUp(PowerUpSpawner powerUpSpawner,int x, int y) {
		this.powerUpSpawner = powerUpSpawner;
		this.x = x;
		this.y = y;
		clock = 0;
		time = System.currentTimeMillis();
	}
	
	public void tick() {
		long deltaTime = System.currentTimeMillis();
		
		if(deltaTime - time > 50) {
			time = System.currentTimeMillis();
			clock++;
			if(clock >= 3) clock = 0;
		}
	}
	
	public void render(Graphics g) {
		if(clock == 0) {
			g.setColor(color1);
		} else if(clock == 1) {
			g.setColor(color2);
		} else if(clock == 2) {
			g.setColor(color3);
		} 
		g.drawRect(x, y, Tron.PIXELSIZE, Tron.PIXELSIZE);
		
		if(clock == 1) {
			g.setColor(color1);
		} else if(clock == 2) {
			g.setColor(color2);
		} else if(clock == 0) {
			g.setColor(color3);
		} 
		g.drawRect(x+1, y+1, Tron.PIXELSIZE-2, Tron.PIXELSIZE-2);
		
		if(clock == 2) {
			g.setColor(color1);
		} else if(clock == 0) {
			g.setColor(color2);
		} else if(clock == 1) {
			g.setColor(color3);
		} 
		g.fillRect(x+2, y+2, 3, 3);
		
		g.setColor(color3); 
		g.drawRect(x-clock, y-clock, Tron.PIXELSIZE+clock*2, Tron.PIXELSIZE+clock*2);
	}
	
	public void destroy(Color color) {
		AudioController.playExplo();
		powerUpSpawner.getLevel().getEffectHandler().addEffect(new ExplosionEffect(powerUpSpawner.getLevel().getEffectHandler(), x, y, color));
		powerUpSpawner.getLevel().getEffectHandler().setExplosionOrigin(new Point(x, y));
		powerUpSpawner.removePowerUp(this);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,Tron.PIXELSIZE, Tron.PIXELSIZE);
	}

}
