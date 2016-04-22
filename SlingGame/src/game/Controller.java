package game;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import loaders.Textures;
import objects.BeanBag;
import objects.Trustee;

/**
 * The controller class will a lot of the game movement such as:
 * -trustee spawning
 * -bean bag spawning
 * -movement/direction of trustees
 */
public class Controller {

	// beanbags
	private LinkedList<BeanBag> b = new LinkedList<BeanBag>();
	// trustee
	private LinkedList<Trustee> e = new LinkedList<Trustee>();

	Random r = new Random();

	BeanBag tempBeanBag;
	Trustee tempTrustee;

	GamePanel game;

	Textures texture;

	public Controller(GamePanel game, Textures texture) {
		this.game = game;
		this.texture = texture;

		addTrustee(new Trustee(r.nextInt(GamePanel.WIDTH), 193, texture));

		addTrustee(new Trustee(r.nextInt(GamePanel.WIDTH), 325, texture));

		addTrustee(new Trustee(r.nextInt(GamePanel.WIDTH), 458, texture));
		/*
		 * for (int y = 0; y < (GamePanel.WIDTH); y += 200) { addTrustee(new
		 * Trustee(y, 193, texture)); }
		 * 
		 * for (int y = 100; y < (GamePanel.WIDTH); y += 200) { addTrustee(new
		 * Trustee(y, 325, texture)); }
		 * 
		 * for (int y = 0; y < (GamePanel.WIDTH); y += 200) { addTrustee(new
		 * Trustee(y, 458, texture)); }
		 */
	}

	public void tick() {
		// bean bag
		for (int i = 0; i < b.size(); i++) {
			tempBeanBag = b.get(i);

			if (tempBeanBag.getY() < 0)
				removeBeanBag(tempBeanBag);

			tempBeanBag.tick();
		}
		// trustee
		for (int i = 0; i < e.size(); i++) {
			tempTrustee = e.get(i);
			tempTrustee.tick();
		}

	}

	public void render(Graphics g) {
		// bean bag
		for (int i = 0; i < b.size(); i++) {
			tempBeanBag = b.get(i);
			tempBeanBag.render(g);
		}
		// trustee
		for (int i = 0; i < e.size(); i++) {
			tempTrustee = e.get(i);
			tempTrustee.render(g);
		}
	}

	public void addBeanBag(BeanBag block) {
		b.add(block);
	}

	public void removeBeanBag(BeanBag block) {
		b.remove(block);
	}

	public void addTrustee(Trustee block) {
		e.add(block);
	}

	public void removeTrustee(Trustee block) {
		e.remove(block);
	}
}
