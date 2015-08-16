package main;

import java.awt.Color;
import java.awt.Graphics;

public class Debug {
	
	DrawPanel dp;

	public Debug(DrawPanel dp) {
		this.dp = dp;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.drawString("" + dp.getFps(), 20, 20);
		g.drawString("Dir     :" + Tron.getDir(), 20, 40);
		g.drawString("prevDir :" + Tron.getPrevDir(), 20, 60);
		g.setColor(Color.white);
		g.drawString("GameState: " + dp.getGameState(), 20, 300);
	}
	
}
