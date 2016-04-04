package com.game.src.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

public class GamePanel extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	/*
	 * Dimensions for the game canvas are set here.
	 */
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;

	/*
	 * The title bar for trustee
	 */
	public final String TITLE = "Trustee Down";

	private boolean running = false;

	private Thread thread;

	/*
	 * image buffer loaders
	 * 
	 * image - loads the actual image spriteSheet - loads the sprite sheet w/
	 * coordinates
	 * 
	 */

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;

	private Player p;

	public void init() {
		// initialize method
		
		requestFocus();
		
		BufferedImageLoader loader = new BufferedImageLoader();

		try {
			spriteSheet = loader.loadImage("/wcrosshair.png");
			background = loader.loadImage("/background.png");
		} catch (IOException e) {
			e.printStackTrace();
		}

		addKeyListener(new KeyInput(this));

		p = new Player(200, 200, this);

	}

	private synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	private synchronized void stop() {
		if (!running)
			return;

		running = false;

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}

	@Override
	public void run() {

		init();

		long lastTime = System.nanoTime();
		final double ticks = 60.0;
		double ns = 1000000000 / ticks;
		double delta = 0;

		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();

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

	private void tick() {
		p.tick();

	}

	private void render() {

		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		/////////

		
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		
		g.drawImage(background, 0, 0, null);
		
		
		p.render(g);
		
		

		///////

		g.dispose();
		bs.show();
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		int moveSpeed = 10;

		if (key == KeyEvent.VK_RIGHT) {
			p.setVelX(moveSpeed);
		} else if (key == KeyEvent.VK_LEFT) {
			p.setVelX(-moveSpeed);
		} else if (key == KeyEvent.VK_DOWN) {
			p.setVelY(moveSpeed);
		} else if (key == KeyEvent.VK_UP) {
			p.setVelY(-moveSpeed);
		}

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		int moveSpeed =0 ;

		if (key == KeyEvent.VK_RIGHT) {
			p.setVelX(moveSpeed);
		} else if (key == KeyEvent.VK_LEFT) {
			p.setVelX(-moveSpeed);
		} else if (key == KeyEvent.VK_DOWN) {
			p.setVelY(moveSpeed);
		} else if (key == KeyEvent.VK_UP) {
			p.setVelY(-moveSpeed);
		}

	}

	public static void main(String args[]) {

		GamePanel game = new GamePanel();
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		game.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		game.setMinimumSize(new Dimension(WIDTH, HEIGHT));

		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();

	}

	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}
}