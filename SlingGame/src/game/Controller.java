package game;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import loaders.Textures;
import objects.GameObject;
import objects.Trustee;

/**
 * The controller class will a lot of the game movement such as:
 * trustee,spawning, bean bag spawning, movement/direction of trustees
 */

public class Controller {

	private LinkedList<GameObject> o = new LinkedList<GameObject>();
	GameObject obj;

	Random r = new Random();

	public Controller(Textures textures) {
		addObject(new Trustee(r.nextInt(GamePanel.WIDTH), 193, textures));
		addObject(new Trustee(r.nextInt(GamePanel.WIDTH), 325, textures));
		addObject(new Trustee(r.nextInt(GamePanel.WIDTH), 458, textures));

	}

	public void tick() {
		for (int i = 0; i < o.size(); i++) {
			obj = o.get(i);
			obj.tick();
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < o.size(); i++) {
			obj = o.get(i);
			obj.render(g);
		}
	}

	public void addObject(GameObject block) {
		o.add(block);
	}

	public void removeObject(GameObject block) {
		o.remove(block);
	}
}
