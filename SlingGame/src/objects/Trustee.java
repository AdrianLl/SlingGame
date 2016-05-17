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

	Animation trusteeAnimationF, trusteeAnimationB;

	private GamePanel game;
	private Controller c;

	boolean forward = true; // bool for forward/backward movement

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

		trusteeAnimationF = new Animation(5, textures.trusteeF[0], textures.trusteeF[1], textures.trusteeF[2],
							textures.trusteeF[3], textures.trusteeF[4], textures.trusteeF[5], textures.trusteeF[6],
							textures.trusteeF[7], textures.trusteeF[8], textures.trusteeF[9], textures.trusteeF[10],
							textures.trusteeF[11], textures.trusteeF[12], textures.trusteeF[13]);

		trusteeAnimationB = new Animation(5, textures.trusteeB[0], textures.trusteeB[1], textures.trusteeB[2],
				textures.trusteeB[3], textures.trusteeB[4], textures.trusteeB[5],textures.trusteeB[6],
				textures.trusteeB[7], textures.trusteeB[8], textures.trusteeB[9], textures.trusteeB[10],
				textures.trusteeB[11], textures.trusteeB[12], textures.trusteeB[13]);

		this.c = c;
		this.game = game;
	}

	public void tick() {

		if (x > (GamePanel.WIDTH-20)) {
			forward = false;
		}
		if (x < -60) {
			forward = true;
		}

		if (forward) {
			x += 2;
		}

		if (!forward) {
			x -= 2;
		}

		/*
		 * When the beanbag collides with the trustee, it disappears.
		 */
		for (int i = 0; i < game.po.size(); i++) {

			PlayerObjects tempPlayer = game.po.get(i);

			if (Collisions.Collision(this, tempPlayer)) {
				c.removeObject(tempPlayer);
				c.removeObject(this);
			}

		}

		trusteeAnimationF.runAnimation();
		trusteeAnimationB.runAnimation();

	}


	public void render(Graphics g) {
		if (forward) {
			trusteeAnimationF.drawAnimation(g, x, y, 0);
		} else if (!forward) {
			trusteeAnimationB.drawAnimation(g, x, y, 0);
		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 50, 70);
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 0;
	}
}
