package effects;

import java.awt.Graphics;

public class Effect {
	
	private int x;
	private int y;
	
	EffectHandler eh;
	
	public Effect(EffectHandler eh, int x, int y) {
		this.eh = eh;
		this.x = x;
		this.y = y;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {return x;}
	public int getY() {return y;}

}
