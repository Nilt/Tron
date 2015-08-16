package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import assets.ColorPallette;
import effects.Trail;
import physics.PhysicsObject;
import screens.Level;

public class Player extends PhysicsObject {

	private int player;
	private int x;
	private int y;
	private int prevX;
	private int prevY;
	private int speed; // Equals to pixelSize from Level class
	private int xSpeed;
	private int ySpeed;
	private int trailLength;
	
	private int score;

	private Level level;

	private Color color;

	private ArrayList<Trail> trails;

	public Player(Level level, int player, int x,
			int y, int speed) {
		this.level = level;
		this.player = player;
		this.x = x;
		this.y = y;
		this.speed = speed;
		score = 0;
		trailLength = Tron.PIXELSIZE * 2;
		level.getPhysics().addEntity(this);
		trails = new ArrayList<Trail>();
		getPlayerColor();
	}

	public void getPlayerColor() {
		switch (player) {
		case 1:
			color = ColorPallette.player1Color;
			break;
		case 2:
			color = ColorPallette.player2Color;
			break;
		case 3:
			color = ColorPallette.player3Color;
			break;
		case 4:
			color = ColorPallette.player4Color;
			break;
		}
	}

	public void tick() {
		updatePrevCoords();
		outOfBounds();
		
		switch(player) {
			case 1:
				move();
				break;
			case 2:
				move2();
				break;
			case 3:
				move3();
				break;
			case 4:
				move4();
				break;
		}

		spawnTrail();
		trailTick();

	}

	public void outOfBounds() {
		if(x < level.getWestBorder() || x > level.getEastBorder() +Tron.PIXELSIZE *2 ||
			y < level.getNorthBorder() || y > level.getSouthBorder() + level.getNorthBorder() - Tron.PIXELSIZE*1) {
			destroy();
		}
	}
	
	public void move() {
		if (Tron.getDir() == Tron.VKRIGHT && xSpeed != -speed) {
			xSpeed = speed;
			ySpeed = 0;
		} else if (Tron.getDir() == Tron.VKLEFT && xSpeed != speed) {
			xSpeed = -speed;
			ySpeed = 0;
		}

		if (Tron.getDir() == Tron.VKDOWN && ySpeed != -speed) {
			ySpeed = speed;
			xSpeed = 0;
		} else if (Tron.getDir() == Tron.VKUP && ySpeed != speed) {
			ySpeed = -speed;
			xSpeed = 0;
		}

		x += xSpeed;
		y += ySpeed;
	}

	public void move2() {
		if (Tron.getDir2() == Tron.VKRIGHT2 && xSpeed != -speed) {
			xSpeed = speed;
			ySpeed = 0;
		} else if (Tron.getDir2() == Tron.VKLEFT2 && xSpeed != speed) {
			xSpeed = -speed;
			ySpeed = 0;
		}

		if (Tron.getDir2() == Tron.VKDOWN2 && ySpeed != -speed) {
			ySpeed = speed;
			xSpeed = 0;
		} else if (Tron.getDir2() == Tron.VKUP2 && ySpeed != speed) {
			ySpeed = -speed;
			xSpeed = 0;
		}

		x += xSpeed;
		y += ySpeed;
	}
	
	public void move3() {
		if (Tron.getDir3() == Tron.VKRIGHT3 && xSpeed != -speed) {
			xSpeed = speed;
			ySpeed = 0;
		} else if (Tron.getDir3() == Tron.VKLEFT3 && xSpeed != speed) {
			xSpeed = -speed;
			ySpeed = 0;
		}

		if (Tron.getDir3() == Tron.VKDOWN3 && ySpeed != -speed) {
			ySpeed = speed;
			xSpeed = 0;
		} else if (Tron.getDir3() == Tron.VKUP3 && ySpeed != speed) {
			ySpeed = -speed;
			xSpeed = 0;
		}

		x += xSpeed;
		y += ySpeed;
	}
	
	public void move4() {
		if (Tron.getDir4() == Tron.VKRIGHT4 && xSpeed != -speed) {
			xSpeed = speed;
			ySpeed = 0;
		} else if (Tron.getDir4() == Tron.VKLEFT4 && xSpeed != speed) {
			xSpeed = -speed;
			ySpeed = 0;
		}

		if (Tron.getDir4() == Tron.VKDOWN4 && ySpeed != -speed) {
			ySpeed = speed;
			xSpeed = 0;
		} else if (Tron.getDir4() == Tron.VKUP4 && ySpeed != speed) {
			ySpeed = -speed;
			xSpeed = 0;
		}

		x += xSpeed;
		y += ySpeed;
	}

	public void updatePrevCoords() {
		prevX = x;
		prevY = y;
	}

	public void spawnTrail() {
		if (xSpeed != 0 || ySpeed != 0) {
			Trail trail = new Trail(this, prevX, prevY, color, speed,
					trailLength);
			trails.add(trail);
			level.getPhysics().addEntity(trail);
		}
	}

	public void trailTick() {
		for (int i = trails.size() - 1; i >= 0; i--) {
			trails.get(i).tick();
		}
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, Tron.PIXELSIZE, Tron.PIXELSIZE);
		
		trailRender(g);
	}

	public void trailRender(Graphics g) {
		for (int i = trails.size() - 1; i > 0; i--) {
			trails.get(i).render(g);
		}
	}

	public void destroyTrail(Trail trailToDestroy) {
		level.getPhysics().removeEntity(trailToDestroy);
		trails.remove(trailToDestroy);

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, speed, speed);
	}

	public void destroy() {
		for(Trail t:trails) {
			level.getPhysics().removeEntity(t);
		}
		level.removePlayer(this);
	}

	public int getXSpeed() {
		return xSpeed;
	}

	public int getYSpeed() {
		return ySpeed;
	}

	public void increaseLength() {
		if(trailLength < Tron.PIXELSIZE * 30)
		trailLength += Tron.PIXELSIZE * 3;
	}

	public int getPlayerNumber() {
		return player;
	}
	
	public void setScore(int score) {
		this.score += score;
	}
	public String getScoreAsString() { 
		return "" + score; 
		}
	public int getScore() {
		return score;
	}
	public Color getColor() {
		return color;
	}
}
