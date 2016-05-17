package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import game.Collisions;
import game.Controller;
import game.GamePanel;
import game.Textures;
import loaders.Animation;

public class Student extends GameObject implements EnemyObjects {

	Random r = new Random();

	Animation studentAnimationF, studentAnimationB;

	private GamePanel game;
	private Controller c;
	private int value = -5;

	boolean forward = true; // bool for forward/backward movement
	
	/**
	 * Student Constructor
	 * 
	 * @param x - coordinate for object start
	 * @param y - coordinate for object start
	 * @param textures - image load of the object
	 * @param c - controller to set movement/spawn
	 * @param game - gamepanel being used
	 */
	public Student(double x, double y, Textures textures, Controller c, GamePanel game) {

		super(x, y);

		studentAnimationF = new Animation(5, textures.studentF[0], textures.studentF[1], textures.studentF[2],
							textures.studentF[3], textures.studentF[4], textures.studentF[5], textures.studentF[6],
							textures.studentF[7], textures.studentF[8], textures.studentF[9], textures.studentF[10],
							textures.studentF[11], textures.studentF[12], textures.studentF[13]);

		studentAnimationB = new Animation(5, textures.studentB[0], textures.studentB[1], textures.studentB[2],
				textures.studentB[3], textures.studentB[4], textures.studentB[5],textures.studentB[6],
				textures.studentB[7], textures.studentB[8], textures.studentB[9], textures.studentB[10],
				textures.studentB[11], textures.studentB[12], textures.studentB[13]);

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

		studentAnimationF.runAnimation();
		studentAnimationB.runAnimation();

	}


	public void render(Graphics g) {
		if (forward) {
			studentAnimationF.drawAnimation(g, x, y, 0);
		} else if (!forward) {
			studentAnimationB.drawAnimation(g, x, y, 0);
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
	
	public int getValue(){
		return value;
	}
	public void setValue(int v){
		this.value=v;
	}
}
