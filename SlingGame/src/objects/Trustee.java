package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import game.Collisions;
import game.Controller;
import game.GamePanel;
import game.Textures;
import loaders.Animation;

public class Trustee extends GameObject implements EnemyObjects {

	Random r = new Random();

	Animation trusteeAnimation;

	private GamePanel game;

	private Controller c;

	/**
	 * Trustee constructor
	 * 
	 * @param x
	 *            coordinate for trustee start
	 * @param y
	 *            y coordinate for trustee start
	 * @param textures
	 */
	public Trustee(double x, double y, Textures textures, Controller c, GamePanel game) {

		super(x, y);

		trusteeAnimation = new Animation(3, textures.trustees[0], textures.trustees[1], textures.trustees[2],
				textures.trustees[3], textures.trustees[4], textures.trustees[5], textures.trustees[6],
				textures.trustees[7], textures.trustees[8], textures.trustees[9], textures.trustees[10],
				textures.trustees[11], textures.trustees[12], textures.trustees[13]);

		this.c = c;
		this.game = game;
	}

	public void tick() {
		x += 2;

		if (x > (GamePanel.WIDTH)) {
			x = 0;

		}

		for (int i = 0; i < game.po.size(); i++) {

			PlayerObjects tempPlayer = game.po.get(i);

			if (Collisions.Collision(this, tempPlayer)) {
				c.removeObject(tempPlayer);
				c.removeObject(this);
			}

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

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 30, 60);
	}
}
