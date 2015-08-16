package effects;

import java.awt.Color;
import java.awt.Graphics;

import main.Tron;

public class WhiteFlashEffect extends Effect{

	private int alpha;
	private Color color;
	
	public WhiteFlashEffect(EffectHandler eh, int x, int y, Color color) {
		super(eh, x, y);
		this.color = color;
		alpha = 255;
	}
	
	public void tick() {
		if(alpha > 20) alpha -= 20;
		else eh.removeEffect(this);
	}

	public void render(Graphics g) {
		g.setColor(new Color(color.getRed(),color.getGreen(),color.getGreen(), alpha));
		g.fillRect(super.getX(), super.getY(), Tron.WIDTH, Tron.HEIGHT);
	}
	
}
