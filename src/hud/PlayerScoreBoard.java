package hud;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Player;
import assets.FontImages;
import assets.SpriteSheet;

public class PlayerScoreBoard {

	private Hud hud;
	
	private int playerIndex;
	private Player player;
	private int x;
	private int y;
	
	SpriteSheet font;
	BufferedImage awol;
	
	public PlayerScoreBoard(Hud hud,int p, int x, int y) {
		this.hud = hud;
		this.playerIndex = p;
		this.x = x;
		this.y = y;
		player = hud.getLevel().getPlayer(p);
		
		if(player != null)
			font = new SpriteSheet(getImageForPlayer(p), 18,27, 10);
		else
			try {
				awol =  ImageIO.read(new File(getImageForPlayer(p + 4)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public String getImageForPlayer(int playerNumber) {
		String filePath = "";
		
		switch (playerNumber) {
		case 1:
			filePath = FontImages.player1Font;
			break;
		case 2:
			filePath = FontImages.player2Font;
			break;
		case 3:
			filePath = FontImages.player3Font;
			break;
		case 4:
			filePath = FontImages.player4Font;
			break;
		case 5:
			filePath = FontImages.player1Awol;
			break;
		case 6:
			filePath = FontImages.player2Awol;
			break;
		case 7:
			filePath = FontImages.player3Awol;
			break;
		case 8:
			filePath = FontImages.player4Awol;
			break;
		}
		
		return filePath;
	}
	
	public void render(Graphics g) {
		
		if(player != null) {
			String score = player.getScoreAsString();
			int numbersToDraw = score.length();
			if(numbersToDraw >= 1)
				g.drawImage(font.getImage(Integer.parseInt(""+score.charAt(score.length()-1))), x,y, null);
			if(numbersToDraw >= 2)
				g.drawImage(font.getImage(Integer.parseInt(""+score.charAt(score.length()-2))), x-20,y, null);
			if(numbersToDraw >= 3)
				g.drawImage(font.getImage(Integer.parseInt(""+score.charAt(score.length()-3))), x-40,y, null);
			if(numbersToDraw >= 4)
				g.drawImage(font.getImage(Integer.parseInt(""+score.charAt(score.length()-4))), x-60,y, null);
		} else if(player == null) {
				g.drawImage(awol, x, y, null);
		}
		
	}
	
}
