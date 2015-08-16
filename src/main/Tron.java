package main;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import screens.Level;

public class Tron extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final static String TITLE = "Tron";
	public static final int WIDTH = 980;
	public static final int HEIGHT = 700;
	public static final int PIXELSIZE = 6;

	public static boolean debug = false;

	private DrawPanel drawPanel;

	public Tron() {
		super(TITLE);
		addKeyListener(this);
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		drawPanel = new DrawPanel();
		add(drawPanel, BorderLayout.CENTER);
		setVisible(true);
		drawPanel.start();
	}

	public static void main(String[] args) {
		new Tron();
	}

	// player 1
	private static int dir = -1;
	private static int prevDir = -1;
	public static int VKUP = 0;
	public static int VKRIGHT = 1;
	public static int VKDOWN = 2;
	public static int VKLEFT = 3;

	// player 2
	private static int dir2 = -1;
	private static int prevDir2 = -1;
	public static int VKUP2 = 0;
	public static int VKRIGHT2 = 1;
	public static int VKDOWN2 = 2;
	public static int VKLEFT2 = 3;

	// player 3
	private static int dir3 = -1;
	private static int prevDir3 = -1;
	public static int VKUP3 = 0;
	public static int VKRIGHT3 = 1;
	public static int VKDOWN3 = 2;
	public static int VKLEFT3 = 3;

	// player 4
	private static int dir4 = -1;
	private static int prevDir4 = -1;
	public static int VKUP4 = 0;
	public static int VKRIGHT4 = 1;
	public static int VKDOWN4 = 2;
	public static int VKLEFT4 = 3;

	// Menu selection
	private static int menuItemSelected = 0;
	private int lastSelected = 0;

	@Override
	public void keyPressed(KeyEvent e) {
		int gameState = drawPanel.getGameState();

		if (e.getKeyCode() == KeyEvent.VK_P)
			debug = !debug;

		// SPLASH SCREEN
		if (gameState == DrawPanel.SPLASH) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				drawPanel.deleteSplashScreen();
				drawPanel.setGameState(DrawPanel.MENU);
				// drawPanel.setLevel(new Level(getMenuItemSelected()+1));
			}
		}
		// END SPLASH SCREEN

		// MENU SCREEN
		else if (gameState == DrawPanel.MENU) {
			if (menuItemSelected >= 0 && menuItemSelected < 4) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT
						|| e.getKeyCode() == KeyEvent.VK_A) {

					menuItemSelected--;

					if (menuItemSelected < 0)
						menuItemSelected = 3;
					lastSelected = menuItemSelected;
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT
						|| e.getKeyCode() == KeyEvent.VK_D) {

					menuItemSelected++;

					if (menuItemSelected > 3)
						menuItemSelected = 0;
					lastSelected = menuItemSelected;
				}

			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN
					|| e.getKeyCode() == KeyEvent.VK_S) {
				if (menuItemSelected != 4)
					lastSelected = menuItemSelected;
				menuItemSelected = 4;
			} else if (e.getKeyCode() == KeyEvent.VK_UP
					|| e.getKeyCode() == KeyEvent.VK_W) {
				menuItemSelected = lastSelected;
			}

			if (e.getKeyCode() == KeyEvent.VK_SPACE
					|| e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (menuItemSelected >= 0 && menuItemSelected < 4) {
					drawPanel.setGameState(DrawPanel.RUNNING);
					drawPanel.setLevel(new Level(getMenuItemSelected() + 1));
				} else if (menuItemSelected == 4) {
					System.exit(0);
				}
			}
		}
		// END MENU SCREEN

		// RUNNING
		else if (gameState == DrawPanel.RUNNING) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				stopPlayers();
				drawPanel.setLevel(new Level(getMenuItemSelected() + 1));
			}

			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				drawPanel.setGameState(DrawPanel.MENU);
				drawPanel.deleteLevel();
				stopPlayers();
			}

			// PLAYER 1
			if (e.getKeyCode() == KeyEvent.VK_W) {
				prevDir = dir;
				dir = VKUP;
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				prevDir = dir;
				dir = VKDOWN;
			}

			if (e.getKeyCode() == KeyEvent.VK_A) {
				prevDir = dir;
				dir = VKLEFT;
			} else if (e.getKeyCode() == KeyEvent.VK_D) {
				prevDir = dir;
				dir = VKRIGHT;
			}

			// PLAYER 2
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				prevDir2 = dir2;
				dir2 = VKUP2;
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				prevDir2 = dir2;
				dir2 = VKDOWN2;
			}

			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				prevDir2 = dir2;
				dir2 = VKLEFT2;
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				prevDir2 = dir2;
				dir2 = VKRIGHT2;
			}

			// PLAYER 3
			if (e.getKeyCode() == KeyEvent.VK_U) {
				prevDir3 = dir3;
				dir3 = VKUP3;
			} else if (e.getKeyCode() == KeyEvent.VK_J) {
				prevDir3 = dir3;
				dir3 = VKDOWN3;
			}

			if (e.getKeyCode() == KeyEvent.VK_H) {
				prevDir3 = dir3;
				dir3 = VKLEFT3;
			} else if (e.getKeyCode() == KeyEvent.VK_K) {
				prevDir3 = dir3;
				dir3 = VKRIGHT3;
			}

			// PLAYER 4
			if (e.getKeyCode() == KeyEvent.VK_NUMPAD8) {
				prevDir4 = dir4;
				dir4 = VKUP4;
			} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD5) {
				prevDir4 = dir4;
				dir4 = VKDOWN4;
			}

			if (e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
				prevDir4 = dir4;
				dir4 = VKLEFT4;
			} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD6) {
				prevDir4 = dir4;
				dir4 = VKRIGHT4;
			}

		}
		// END RUNNING
	}

	public void stopPlayers() {
		dir = -1;
		prevDir = -1;
		dir2 = -1;
		prevDir2 = -1;
		dir3 = -1;
		prevDir3 = -1;
		dir4 = -1;
		prevDir4 = -1;
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public static int getDir() {
		return dir;
	}

	public static int getPrevDir() {
		return prevDir;
	}

	public static int getDir2() {
		return dir2;
	}

	public static int getPrevDir2() {
		return prevDir2;
	}

	public static int getDir3() {
		return dir3;
	}

	public static int getPrevDir3() {
		return prevDir3;
	}

	public static int getDir4() {
		return dir4;
	}

	public static int getPrevDir4() {
		return prevDir4;
	}

	public static int getMenuItemSelected() {
		return menuItemSelected;
	}

}
