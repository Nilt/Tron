package physics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import effects.Trail;
import main.Player;
import main.Tron;

public class Physics {

	private ArrayList<PhysicsObject> playersAndTrails;

	public Physics() {
		playersAndTrails = new ArrayList<PhysicsObject>();
	}

	public void collisionPhysics() {
		playersHitWall();
	}

	public void playersHitWall() {
		for (int currentPhysicsObject = 0; currentPhysicsObject < playersAndTrails
				.size(); currentPhysicsObject++) {
			for (int secondPhysicsObject = 0; secondPhysicsObject < playersAndTrails
					.size(); secondPhysicsObject++) {
				PhysicsObject first = playersAndTrails
						.get(currentPhysicsObject);
				PhysicsObject second = playersAndTrails
						.get(secondPhysicsObject);

				int id1 = -1;
				int id2 = -2;

				// GET ID for first object
				if (first instanceof Trail) {
					id1 = ((Trail) first).getPlayer().getPlayerNumber();
				} else if (first instanceof Player) {
					id1 = ((Player) first).getPlayerNumber();
				}
				// GET ID for first object
				if (second instanceof Trail) {
					id2 = ((Trail) second).getPlayer().getPlayerNumber();
				} else if (second instanceof Player) {
					id2 = ((Player) second).getPlayerNumber();
				}

				// Destroy players in head on collision
				if (first instanceof Player && second instanceof Player
						&& id1 != id2) {
					if (first.getBounds().intersects(second.getBounds())) {
						first.destroy();
						second.destroy();
					}
				}

				if (!first.equals(second) && id1 != id2)
					destroyOnHit(first, second);

			}
		}
	}

	public void destroyOnHit(PhysicsObject first, PhysicsObject second) {
		if (first.getBounds().intersects(second.getBounds())) {
			if (second instanceof Trail) {
				Trail t = (Trail) second;
				t.getPlayer().setScore(1);
			}
			first.destroy();
			removeEntity(first);
		}
	}

	public void render(Graphics g) {
		for (int i = playersAndTrails.size() - 1; i >= 0; i--) {
			int x = playersAndTrails.get(i).getBounds().x;
			int y = playersAndTrails.get(i).getBounds().y;

			if (playersAndTrails.get(i) instanceof Trail)
				g.setColor(Color.pink);
			else if (playersAndTrails.get(i) instanceof Player)
				g.setColor(Color.red);
			g.fillRect(x, y, Tron.PIXELSIZE, Tron.PIXELSIZE);
		}
	}

	public void renderDebug(Graphics g) {

	}

	public void addEntity(PhysicsObject obj) {
		playersAndTrails.add(obj);
	}

	public void removeEntity(PhysicsObject obj) {
		playersAndTrails.remove(obj);
	}

	public int getPhysicsSize() {
		return playersAndTrails.size();
	}

}
