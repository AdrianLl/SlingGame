package game;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import objects.GameObject;
import objects.Trustee;

/**
 * The controller class will a lot of the game movement such as:
 * trustee,spawning, bean bag spawning, movement/direction of trustees
 */

public class Controller {

	//Linked list of game objects (trustee, beanbag, etc)
	private LinkedList<GameObject> o = new LinkedList<GameObject>();
	GameObject obj;

	Random r = new Random(); //random num generator for random spawning

	public Controller(Textures textures) {
		//for now just randomly spawning in the x direction on the three paths
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
	
	/**
	 * Add specified object to the game panel
	 * @param block that is going to be added
	 */
	public void addObject(GameObject block) {
		o.add(block);
	}
	
	/**
	 * Removes specified object from the game panel
	 * @param block that is going to be removed
	 */

	public void removeObject(GameObject block) {
		o.remove(block);
	}
}
