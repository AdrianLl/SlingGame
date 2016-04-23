package objects;

import java.awt.Graphics;
import java.util.Random;

import game.Animation;
import game.GamePanel;
import game.Textures;

public class Trustee implements GameObject {

	private double x, y;

	Random r = new Random();


	Animation trusteeAnimation;
	/**
	 * Trustee constructor
	 * 
	 * @param x
	 *            coordinate for trustee start
	 * @param y
	 *            y coordinate for trustee start
	 * @param textures
	 */
	public Trustee(double x, double y, Textures textures) {

		this.x = x;
		this.y = y;
		
		trusteeAnimation = new Animation(3,textures.trustees[0],textures.trustees[1],
				textures.trustees[2],textures.trustees[3],textures.trustees[4],
				textures.trustees[5],textures.trustees[6],textures.trustees[7],
				textures.trustees[8],textures.trustees[9],textures.trustees[10],
				textures.trustees[11],textures.trustees[12],textures.trustees[13]);

	}

	public void tick() {
		x += 2;

		if (x > (GamePanel.WIDTH)) {
			x = 0;

			// line below makes random spawn on y but won't be needed
			// if the game will be just using the 3 paths
			// y=r.nextInt(GamePanel.HEIGHT);
			
		}
		
		trusteeAnimation.runAnimation();

	}

	public void render(Graphics g) {
		trusteeAnimation.drawAnimation(g, x, y, 0);

	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
