package assets;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class SpriteSheet {

	BufferedImage img;
	BufferedImage[] imgList;
	
	public SpriteSheet(String fileName, int x, int y, int totalImages) {
		try {
			img = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(img == null) {
			System.out.println("Err");
		}
		imgList = new BufferedImage[totalImages];
		int offX = 0;
		int offY = 0;
		for(int i = 0; i < imgList.length; i++) {
			imgList[i] = img.getSubimage(offX, offY, x, y);
			offX += x;
			if(offX >= img.getWidth()) {
				offX = 0;
				offY += y;
			}
		}
		//System.out.println("loaded: " + imgList.length + " images");
	}
	
	public void draw(Graphics g) {
		//g.drawImage(imgList[0][0], 50, 50, null);
	}
	
	public BufferedImage getImage(int i) {
		return imgList[i];
	}
	
}
