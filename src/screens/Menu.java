package screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import assets.SpriteSheet;
import main.DrawPanel;
import main.Tron;

public class Menu {

	DrawPanel dp;
	
	private SpriteSheet selectedImages;
	private SpriteSheet unselectedImages;
	private BufferedImage selectedExit;
	private BufferedImage unselectedExit;
	
	private MenuItem[] menuItems;
	
	private int selectedIndex;
	
	public Menu(DrawPanel dp) {
		this.dp = dp;
		
		getImages();
		
		menuItems = new MenuItem[5];
		initMenuItems();
		selectedIndex = Tron.getMenuItemSelected();
	}
	
	public void getImages() {
		selectedImages = new SpriteSheet("Assets/Images/Fonts/menuColor.png", 18*2,27*2, 4);
		unselectedImages = new SpriteSheet("Assets/Images/Fonts/menuGrey.png", 18,27, 4);
		try {
			selectedExit = ImageIO.read(new File(
					"Assets/Images/Fonts/exitSelected.png"));
			unselectedExit = ImageIO.read(new File(
					"Assets/Images/Fonts/exitUnSelected.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initMenuItems() {
		for(int i = 0; i<4; i++) {
			menuItems[i] = new MenuItem(unselectedImages.getImage(i),selectedImages.getImage(i), i*100 + Tron.WIDTH / 3 + 10, 300);
		}
		menuItems[4] = new MenuItem(unselectedExit,selectedExit, Tron.WIDTH / 2, 400);
	}
	
	public void tick() {
		selectedIndex = Tron.getMenuItemSelected();
		for(int i = 0; i<menuItems.length; i++) {
			if(i == selectedIndex)
				menuItems[i].setSelected(true);
			else menuItems[i].setSelected(false);
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i<menuItems.length; i++) {
			menuItems[i].render(g);
		}
	}

	
	
	
}
