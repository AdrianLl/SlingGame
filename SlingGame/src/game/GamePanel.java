package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import loaders.BufferedImageLoader;
import loaders.MouseInput;
import objects.BeanBag;
import objects.EnemyObjects;
import objects.PlayerObjects;

/**
 * GameCanvas will load game objects, render graphics, load sound, and initiate
 * game functions.
 *
 */
public class GamePanel extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	// serial id to ensure different versions don't get confused

	// Game Canvas Size
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;

	// Game Panel Button Size
	public static final int GAMEPANEL_BUTTON_WIDTH = 100;
	public static final int GAMEPANEL_BUTTON_HEIGHT = 40;

	// Game Canvas Title Bar at the top
	public final String TITLE = "Trustee Down Final";
	// Game Canvas running state
	private boolean running = false;
	// Game canvas thread
	private Thread thread;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	// spriteSheet - loads the sprite sheet containing graphic elements
	private BufferedImage background = null;
	// background - loads the background image for the game
	private BufferedImage crosshairImage = null;

	private Textures textures; // character animations, beanbags, canon images
	private Controller controller;
	private Menu menu;

	public LinkedList<PlayerObjects> po; // player objects
	public LinkedList<EnemyObjects> eo; // enemy objects

	private STATE state = STATE.MENU; // default game state is MENU
	
	//menu button load (x,y,width,height)
	public Rectangle menuButton = new Rectangle(15, 735, GAMEPANEL_BUTTON_WIDTH, GAMEPANEL_BUTTON_HEIGHT);
	public Rectangle helpButton = new Rectangle(115, 735, GAMEPANEL_BUTTON_WIDTH, GAMEPANEL_BUTTON_HEIGHT);
	public Rectangle quitButton = new Rectangle(215, 735, GAMEPANEL_BUTTON_WIDTH, GAMEPANEL_BUTTON_HEIGHT);

	private AudioPlayer sound;
	
	/**
	 * Game initialization happens here for images, audio, loaders.
	 */
	public void init() {
		requestFocus(); // makes the game panel the main focus when opened
		
		sound = new AudioPlayer(); //loads bg music
		sound.playBgMusic(); // plays bg music from beginning
		
		BufferedImageLoader loader = new BufferedImageLoader();
		// image loader initiated

		/************ IMAGES LOADED HERE START ************/
		try {
			spriteSheet = loader.loadImage("/MainSprite.png");
			background = loader.loadImage("/Background.png");
			crosshairImage = loader.loadImage("/Crosshair.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		/************ IMAGES LOADED HERE END ************/

		crosshair(); // loads the cursor

		this.addMouseListener(new MouseInput(this)); // mouse listener initiated

		textures = new Textures(this); // textures from sprite are loaded
		controller = new Controller(textures, this); // game controller initialized
		menu = new Menu();
		

		po = controller.getPlayerObjects(); //player objects initiated
		eo = controller.getEnemyObjects(); //enemy objects initiated 

	}

	/**
	 * Starts the game thread
	 */
	public synchronized void start() {
		if (running)
			return;

		running = true; // start
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * Stops the game thread
	 */
	private synchronized void stop() {
		if (!running)
			return;

		running = false; // stop

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}

	@Override

	/**
	 * Run will determine the way the thread runs ticks and fps will be
	 * determined here
	 */
	public void run() {

		init(); // initializes all game elements

		long lastTime = System.nanoTime(); // gets system time
		final double ticks = 60.0; // desired ticks
		double ns = 1000000000 / ticks; // nano seconds/ticks
		double delta = 0;

		int updates = 0; // updates on canvas
		int frames = 0; // frames running
		long timer = System.currentTimeMillis(); // gets system in milliseconds

		/**
		 * The loop below will set tick and frame rate for the refresh that is
		 * occuring on the screen. This was borrowed from a game made in a
		 * previous semester will be commented more or adjusted depending on how
		 * it runs once more objects are loaded etc.
		 */
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, FPS " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	// tick method for game objects
	private void tick() {
		if (state == STATE.PLAY) {
			controller.tick();
		}
	}

	// render method to draw the images on the canvas
	private void render() {

		// buffer load mechanism is below.
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		
		/***** IMAGES RENDERED HERE ************/
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		g.setColor(Color.WHITE); // whitespace color
		g.fillRect(0, 0, 1500, 1500); // whitespace for png to show the
		
		if (state == STATE.PLAY) { // school
			
			Graphics2D g2d = (Graphics2D) g;
			// draw background
			g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
			
			controller.render(g);

			/*
			 * This sets the font, size and x and y-coordinates of the strings,
			 * "PLAY", "MENU", "HELP" and "QUIT"
			 */
			Font font2 = new Font("cooper black", Font.BOLD, 18);
			g.setFont(font2);
			g.drawString("MENU", menuButton.x + 20, menuButton.y + 25);
			g2d.draw(menuButton);
			g.drawString("HELP", helpButton.x + 20, helpButton.y + 25);
			g2d.draw(helpButton);
			g.drawString("QUIT", quitButton.x + 20, quitButton.y + 25);
			g2d.draw(quitButton);
		} else if (state == STATE.MENU) {
			menu.render(g);
		}
		
		/***** IMAGES RENDERED HERE ************/
		//Moved into the state if statement
		//if (state == STATE.PLAY) {
	
		//}
		
		g.dispose(); // clean up graphic resources in the game
		bs.show(); // display (buffer strategy)
	}

	public void mousePressed(MouseEvent e) {

		if (state == STATE.PLAY) {

			/*
			 * Gets the x and y-coordinate of the cursor on the starting screen.
			 */
			int mx = e.getX();
			int my = e.getY();

			/*
			 * Designates the area on the starting screen where the "PLAY"
			 * button is and changes the state to play when "PLAY" is clicked.
			 */
			// MENU
			if (mx >= 3 && mx <= 103 ){
				if (my >= 735 && my <= 775) {
					System.out.println("MENU PRESSED");
					this.state = STATE.MENU;
				}
			}
			// HELP
			if (mx >= 103 && mx <= 203) {
				if (my >= 735 && my <= 775) {
					// Pressed help button
					System.out.println("HELP PRESSED");
					this.state = STATE.MENU;
				}
			}
			// QUIT
			if (mx >= 203 && mx <= 303) {
				if (my >= 735 && my <= 775) {
					// Pressed quit button
					System.out.println("QUIT PRESSED");
					System.exit(1);
				}
			}

			System.out.println("XLOC: " + e.getX() + " YLOC: " + e.getY());
			controller.addObject(new BeanBag(524, 645, textures, e.getX(), e.getY()));
		}
		
		if (state == STATE.MENU) {

			/*
			 * Gets the x and y-coordinate of the cursor on the starting screen.
			 */
			int mx = e.getX();
			int my = e.getY();

			/*
			 * Designates the area on the starting screen where the "PLAY" button is and changes the state to play 
			 * when "PLAY" is clicked.
			 */
			if (mx >= GamePanel.WIDTH / 2 - 150 && mx <= (GamePanel.WIDTH / 2 - 150) + 300) {
				if (my >= 395 && my <= 475) {
					// Pressed play button
					System.out.println("PLAY PRESSED");
					this.state = STATE.PLAY;
				}
			}
			// MENU
			if (mx >= GamePanel.WIDTH / 2 - 150 && mx <= (GamePanel.WIDTH / 2 - 150) + 300) {
				if (my >= 490 && my <= 570) {
					System.out.println("MENU PRESSED");
					this.state = STATE.MENU;
				}
			}
			// HELP
			if (mx >= GamePanel.WIDTH / 2 - 150 && mx <= (GamePanel.WIDTH / 2 - 150) + 300) {
				if (my >= 585 && my <= 665) {
					// Pressed help button
					System.out.println("HELP PRESSED");
					this.state = STATE.MENU;
				}
			}
			// QUIT
			if (mx >= GamePanel.WIDTH / 2 - 150 && mx <= (GamePanel.WIDTH / 2 - 150) + 300) {
				if (my >= 680 && my <= 760) {
					// Pressed quit button
					System.out.println("QUIT PRESSED");
					System.exit(1);
				}
			}
		}
	}


	/**
	 * getter for sprite sheets
	 * @return - sprite sheet buffered image
	 */
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	/**
	 * loads crosshair and replaces it for the cursor
	 */
	public void crosshair() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = (Image) crosshairImage;
		Point point = new Point(15, 15);
		Cursor crosshair = toolkit.createCustomCursor(img, point, "crosshair");

		setCursor(crosshair);
	}
}