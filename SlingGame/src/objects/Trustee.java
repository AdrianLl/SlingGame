package objects;

import java.awt.Graphics;
import java.util.Random;

import game.GamePanel;
import loaders.Textures;

public class Trustee {

	private double x, y;
	
	Random r = new Random();

	private Textures textures;

	/**
	 * Trustee constructor
	 * 
	 * @param x
	 *            - x coordinate for trustee start
	 * @param y
	 *            - y coordinate for trustee start
	 * @param textures
	 */
	public Trustee(double x, double y, Textures textures) {

		this.x = x;
		this.y = y;
		this.textures = textures;

	}

	public void tick() {
		x += 2;

		if (x > (GamePanel.WIDTH)) {
			x = 0;
			
			//line below makes random spawn on y but won't be needed
			//if the game will be just using the 3 paths
			//y=r.nextInt(GamePanel.HEIGHT);
		}

	}

	public void render(Graphics g) {
		g.drawImage(textures.trustee, (int) x, (int) y, null);

	}

	public double getX() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

}
