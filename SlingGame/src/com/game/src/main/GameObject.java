package com.game.src.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * 
 * Game Object will be used to create game objects such as:
 * -player
 * -trustee
 * -canon
 *
 * At the moment it is only implemented to work with the cursor
 *
 */
public class GameObject {

	private double x; //x coordinate on the game canvas
	private double y; //y coordinate on the game canvas

	private BufferedImage player;

	/**
	 * Creates a game object with starting position x and y on the game panel
	 *  
	 * @param x - starting x position
	 * @param y - starting y position
	 * @param game - game panel specified here
	 */
	public GameObject(double x, double y, GamePanel game) {
		this.x = x; // x location
		this.y = y; // y location

		/**
		 * Using the Spritesheet class we will load the images on it 
		 * depending on the sector they are located in. 
		 */
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());

		/**
		 * Here the player cursor has been loaded from the sprite sheet in the images folder.
		 * In order to grab the images we will specify
		 * col,row,sizeX,sizeY
		 * 
		 * Size referes to the size of the image, in this case the cursor
		 */
		player = ss.grabImage(1, 1, 32, 32);
		
	}
	/**
	 * The tick method will dictate movement of the game objects that are loaded
	 */
	public void tick() {
		x++;
		y++;

	}
	
	/**
	 * Render will bring up and actually draw the images to make the visible.
	 */

	public void render(Graphics g) {
		/**
		 * This will draw the specified image, in this case player
		 * in location with coordinates (x,y)
		 */
		g.drawImage(player, (int) x, (int) y, null);
	}
}
