package assets;

import java.util.Random;

public class Vector2D {

	float x;
	float y;

	Random rand;

	public Vector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector2D() {
		rand = new Random();
		float x = rand.nextFloat() * 3;
		float y = rand.nextFloat() * 3;

		if (rand.nextBoolean())
			setX(x);
		else
			setX(-x);

		if (rand.nextBoolean())
			setY(y);
		else
			setY(-y);
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public String toString() {
		return "x : " + getX() + ", y : " + getY();
	}
}
