package screens;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MenuItem {

	private BufferedImage unselectedImg;
	private BufferedImage selectedImg;
	
	private int x;
	private int y;
	
	private boolean selected;
	
	public MenuItem(BufferedImage unselectedImg, BufferedImage selectedImg, int x, int y) {
		this.unselectedImg = unselectedImg;
		this.selectedImg = selectedImg;
		this.x = x;
		this.y = y;
		
	}
	
	public void render(Graphics g) {
		if(selected) {
			g.drawImage(selectedImg, x - selectedImg.getWidth() / 2, y - selectedImg.getHeight() / 2, null);
		} else g.drawImage(unselectedImg, x - unselectedImg.getWidth() / 2, y - unselectedImg.getHeight() / 2, null);
	}
	
	public void setSelected(boolean b) {
		selected = b;
	}
}
