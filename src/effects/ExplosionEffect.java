package effects;

import java.awt.Color;
import java.awt.Graphics;

import main.Player;

public class ExplosionEffect extends Effect{

	private int alpha;
	private int size;
	private int growRate;
	private int x2;
	private int y2;
	private Color color;
	
	public ExplosionEffect(EffectHandler eh, int x, int y, Color color) {
		super(eh, x, y);
		this.color = color;
		eh.addEffect(new WhiteFlashEffect(eh, 0,0, color));
		x2 = x;
		y2 = y;
		alpha = 255;
		size = 8;
		growRate = (int)(Math.random() * 3) + 4;
	}

	@Override
	public void tick() {
		if(alpha > 0) {
			alpha -= 15;
			size += 4 * growRate;
			super.setX(super.getX()-2*growRate);
			super.setY(super.getY()-2*growRate);
			x2 -= 2*growRate/2;
			y2 -= 2*growRate/2;
		} 
		if(alpha <= 15) {
			eh.removeEffect(this);
		} 
		
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(new Color(color.getRed(),color.getGreen(),color.getBlue(), alpha));
		g.drawOval(super.getX(), super.getY(), size, size);
		g.drawOval(x2, y2, size / 2, size / 2);
	}
	
}
