package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import loaders.BufferedImageLoader;
import loaders.KeyInput;
import loaders.MouseInput;
import objects.BeanBag;
import objects.Canon;
import objects.EnemyObjects;
import objects.PlayerObjects;

/**
 * Game canvas will have most of the game mechanics running through here.
 * 
 * This is where the main class is located and will execute from here.
 *
 */
public class GamePanel extends Canvas implements Runnable {
	// uses runnable to execute the game in a thread

	private static final long serialVersionUID = 1L;
	// serial id to ensure different versions don't get confused

	// Game Canvas size
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;

	// Game Canvas Title Bar at the top
	public final String TITLE = "Trustee Down v3";
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

	/**
	 * Game objects will be loaded here, for now we have the player (cursor)
	 * loaded here.
	 */
	private Canon canon;
	private Textures textures;
	private Controller c;
	private Menu menu;

	public LinkedList<PlayerObjects> po;
	public LinkedList<EnemyObjects> eo;
	
	private enum STATE{
		MENU,
		GAME
	};
	
	private STATE State = STATE.MENU;  

	/**
	 * init function to initialize game elements such as images and key
	 * listeners for now
	 */
	public void init() {
		requestFocus(); // makes the game panel the main focus when opened

		BufferedImageLoader loader = new BufferedImageLoader();
		// image loader initiated

		/************ IMAGES LOADED HERE ************/
		try {
			spriteSheet = loader.loadImage("/MainSprite.png");
			background = loader.loadImage("/background.png");
			crosshairImage = loader.loadImage("/crosshair.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		/************ IMAGES LOADED HERE ************/

		crosshair(); // loads the crosshair cursor

		this.addKeyListener(new KeyInput(this)); // key listener initialized
		this.addMouseListener(new MouseInput(this));

		textures = new Textures(this);
		canon = new Canon(500, 520, textures); // canon initialized
		c = new Controller(textures, this); // game controller initialized
		menu = new Menu();

		po = c.getPlayerObjects();
		eo = c.getEnemyObjects();

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
		if(State == STATE.GAME){
		canon.tick();
		c.tick();
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
		
		// g.drawImage(test, 0, 0, null);
		if(State == STATE.GAME){
			/***** IMAGES RENDERED HERE ************/
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			g.setColor(Color.WHITE); // whitespace color
			g.fillRect(0, 0, 1500, 1500); // whitepsace for png to show the school
			// draw background
			g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
			
			canon.render(g);
			c.render(g);
		} else if(State == STATE.MENU){
			menu.render(g);
		}
			/***** IMAGES RENDERED HERE ************/
		g.dispose(); // clean up graphic resources in the game
		bs.show(); // display (buffer strategy)
	}

	/**
	 * key event function for key pressed
	 * 
	 * this will affect the up,down,left,right keys
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode(); // determines the key pressed
		int moveSpeed = 10; // move speed when key pressed
		/**
		 * below is the behavior for the directional keys.
		 */
		if(State == STATE.GAME){
			if (key == KeyEvent.VK_RIGHT) {
				canon.setVelX(moveSpeed);
			} else if (key == KeyEvent.VK_LEFT) {
				canon.setVelX(-moveSpeed);
			} else if (key == KeyEvent.VK_DOWN) {
				canon.setVelY(moveSpeed);
			} else if (key == KeyEvent.VK_UP) {
				canon.setVelY(-moveSpeed);
			}
		}
	}

	/**
	 * behavior on key release, might need to be modified depending on game
	 * mechanics. at the moment stops movement on the cursor when key released
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		int moveSpeed = 0;

		if (key == KeyEvent.VK_RIGHT) {
			canon.setVelX(moveSpeed);
		} else if (key == KeyEvent.VK_LEFT) {
			canon.setVelX(-moveSpeed);
		} else if (key == KeyEvent.VK_DOWN) {
			canon.setVelY(moveSpeed);
		} else if (key == KeyEvent.VK_UP) {
			canon.setVelY(-moveSpeed);
		}

	}

	public void mousePressed(MouseEvent e) {
		// test
		System.out.println("XLOC: " + e.getX() + " YLOC: " + e.getY());
		c.addObject(new BeanBag(canon.getX(), canon.getY() - 40, textures, e.getX(), e.getY()));
	}

	/**
	 * get sprite sheet method to load the image buffer of the sprite sheet.
	 */
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	public void crosshair() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = (Image) crosshairImage;
		Point point = new Point(15, 15);
		Cursor crosshair = toolkit.createCustomCursor(img, point, "crosshair");

		setCursor(crosshair);
	}
}