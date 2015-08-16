package effects;

import java.awt.Graphics;
import java.awt.Point;

public class ParticleRenderer {

	Particle[] explosion;

	public ParticleRenderer(int size) {
		explosion = new Particle[size];
		initParticles();
	}

	public void initParticles() {
		for (int i = 0; i < explosion.length; i++) {
			explosion[i] = new Particle(-10, -10);
		}
	}

	public void setExplosionOrigin(Point p) {
		for (Particle particle : explosion) {
			if (particle != null) {
				particle.updatePosition(p);
			}
		}
	}

	public void tick() {
		for (Particle p : explosion) {
			if (p != null)
				p.tick();
		}
	}

	public void render(Graphics g) {
		for (Particle p : explosion) {
			if (p != null)
				p.render(g);
		}
	}

}
