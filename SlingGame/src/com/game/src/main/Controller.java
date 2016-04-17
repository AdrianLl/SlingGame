package com.game.src.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Controller {

	private LinkedList<BeanBag> b = new LinkedList<BeanBag>();
	private LinkedList<Trustee> e = new LinkedList<Trustee>();

	BeanBag tempBeanBag;
	Trustee tempTrustee;

	GamePanel game;

	Textures texture;

	public Controller(GamePanel game, Textures texture) {
		this.game = game;
		this.texture = texture;

		for (int y = 0; y < (GamePanel.WIDTH); y += 200) {
			addTrustee(new Trustee(y, 193, texture));
		}
		
		for (int y = 100; y < (GamePanel.WIDTH); y += 200) {
			addTrustee(new Trustee(y, 325, texture));
		}
		
		for (int y = 0; y < (GamePanel.WIDTH); y += 200) {
			addTrustee(new Trustee(y, 458, texture));
		}
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
