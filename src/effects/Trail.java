package effects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.Player;
import physics.PhysicsObject;

public class Trail extends PhysicsObject {

	private int x;
	private int y;
	private Player player;
	private Color color;
	private float alpha;
	private int size;
	private int timeToLive;
	

	public Trail(Player player, int x, int y, Color color, int size, int timeToLive) {
		this.player = player;
		this.x = x;
		this.y = y;
		this.color = color;
		this.size = size;
		this.timeToLive = timeToLive;
		alpha = 255;
	}

	public void tick() {
		if (alpha > 0)
			alpha -= 255 / timeToLive;
		if (alpha <= 10) {
			player.destroyTrail(this);
		}

	}

	public void render(Graphics g) {
		if(alpha > 0)
		g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(),
				(int)alpha));

		g.fillRect(x, y, size, size);
	}

	public boolean equals(Trail trail) {
		if(trail == this) return true;
		else return false;
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, size, size);
	}
	
	public Player getPlayer() { return player; }

}
