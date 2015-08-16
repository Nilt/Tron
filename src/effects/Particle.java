package effects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import assets.Vector2D;
import main.Tron;

public class Particle {

	private float x;
	private float y;
	private float size;
	private int alpha;
	private Vector2D dir;

	public Particle(int x, int y) {
		this.x = x;
		this.y = y;
		resetParticle();
	}

	public void resetParticle() {
		size = (float) (Math.random() * Tron.PIXELSIZE + Tron.PIXELSIZE);
		alpha = 255;
		dir = null;
		dir = new Vector2D();
	}
	
	public void tick() {
		if(size > 0) size -= 0.2f;
		if (alpha > 10)
			alpha -= 10;
		if (alpha > 10) {
			x += dir.getX();
			y -= dir.getY();
		} else if (alpha < 10) {
			x = -10;
			y = -10;
		}
	}

	public void render(Graphics g) {
		int rc = (int) (Math.random() * 256);
		int gc = (int) (Math.random() * 256);
		int bc = (int) (Math.random() * 256);

		g.setColor(new Color(rc, gc, bc, alpha));
		g.fillRect((int)x, (int)y, (int)size, (int)size);
	}

	public void updatePosition(Point p) {
		resetParticle();
		setX((int) p.getX());
		setY((int) p.getY());
	}

	
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

}
