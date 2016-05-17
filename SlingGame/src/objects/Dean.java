package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import game.Collisions;
import game.Controller;
import game.GamePanel;
import game.Textures;
import loaders.Animation;

public class Dean extends GameObject implements EnemyObjects {

	Random r = new Random();

	Animation deanAnimationF, deanAnimationB;

	private GamePanel game;
	private Controller c;

	boolean forward = true; // bool for forward/backward movement

	/**
	 * Trustee constructor
	 * 
	 * @param x - coordinate for trustee start
	 * @param y - coordinate for trustee start
	 * @param textures
	 */
	public Dean(double x, double y, Textures textures, Controller c, GamePanel game) {

		super(x, y);

		deanAnimationF = new Animation(5, textures.deanF[0], textures.deanF[1], textures.deanF[2],
							textures.deanF[3], textures.deanF[4], textures.deanF[5], textures.deanF[6],
							textures.deanF[7], textures.deanF[8], textures.deanF[9], textures.deanF[10],
							textures.deanF[11], textures.deanF[12], textures.deanF[13]);

		deanAnimationB = new Animation(5, textures.deanB[0], textures.deanB[1], textures.deanB[2],
				textures.deanB[3], textures.deanB[4], textures.deanB[5],textures.deanB[6],
				textures.deanB[7], textures.deanB[8], textures.deanB[9], textures.deanB[10],
				textures.deanB[11], textures.deanB[12], textures.deanB[13]);

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

		deanAnimationF.runAnimation();
		deanAnimationB.runAnimation();

	}


	public void render(Graphics g) {
		if (forward) {
			deanAnimationF.drawAnimation(g, x, y, 0);
		} else if (!forward) {
			deanAnimationB.drawAnimation(g, x, y, 0);
		}
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

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 0;
	}
}
