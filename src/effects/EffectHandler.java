package effects;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class EffectHandler {

	private ArrayList<Effect> effectList;
	private ParticleRenderer particleRenderer;

	public EffectHandler() {
		effectList = new ArrayList<Effect>();
		particleRenderer = new ParticleRenderer(100);
	}

	public void tick() {
		for (int effect = effectList.size() - 1; effect >= 0; effect--) {
			effectList.get(effect).tick();
		}

		particleRenderer.tick();

	}

	public void render(Graphics g) {
		for (int effect = effectList.size() - 1; effect >= 0; effect--) {
			effectList.get(effect).render(g);
		}
		particleRenderer.render(g);

	}

	public void addEffect(Effect e) {
		effectList.add(e);
	}

	public void removeEffect(Effect e) {
		effectList.remove(e);
	}

	public void setExplosionOrigin(Point p) {
		particleRenderer.setExplosionOrigin(p);
	}

}
