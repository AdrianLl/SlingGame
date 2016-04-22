package objects;

import java.awt.Graphics;

import loaders.Textures;
/**
 * Game canon movement/interactions are made here
 */
public class Canon {

	//(x,y) coordinate location of canon
	private double x;
	private double y;

	private double velX = 0; // initial movement velocity in x
	private double velY = 0; // initial movement velocity in y
		
	private Textures gameTextures;

	/**
	 * Player is the constructor for the player class, in this case for the cursor. 
	 * @param x - x location on the game panel
	 * @param y - y location on the game panel
	 * @param game - game panel specified
	 */
	public Canon(double x, double y, Textures textures) {
		this.x = x;
		this.y = y;
		
		this.gameTextures = textures;
	}
	/*
	 * tick method for the cursor movement within the game panel
	 */
	public void tick() {
		x+=velX;
		y+=velY;
		int missingBound=50;
		
		/*public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;*/
		if(x <= 0 )
			x=0;
		if(x>= 1024-missingBound)
			x=1024-missingBound;
		if(y<=0)
			y=0;
		if(y >= 768-missingBound)
			y=768-missingBound;
		
	}
	/**
	 * render method that will draw out/update movement on the game panel
	 */
	public void render(Graphics g) {
		g.drawImage(gameTextures.canon, (int) x, (int) y, null);

	}
	/**
	 * get/set methods are below to help implement the key listeners and future mouse listener
	 */
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
	public void setVelX(double velX){
		this.velX = velX;
	}
	public void setVelY(double velY){
		this.velY = velY;
	}
}