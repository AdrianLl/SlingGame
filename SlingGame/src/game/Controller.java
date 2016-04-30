package game;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import objects.EnemyObjects;
import objects.PlayerObjects;
import objects.Trustee;

/**
 * The controller class will a lot of the game movement such as:
 * trustee,spawning, bean bag spawning, movement/direction of trustees
 */

public class Controller {

	//Linked list of game objects (trustee, beanbag, etc)
	private LinkedList<PlayerObjects> po = new LinkedList<PlayerObjects>();
	private LinkedList<EnemyObjects> eo = new LinkedList<EnemyObjects>();
	
	PlayerObjects playerObj;
	EnemyObjects enemyObj;

	private Random r = new Random(); //random num generator for random spawning

	Textures textures;
	GamePanel game;
	
	public Controller(Textures textures, GamePanel game) {
		//for now just randomly spawning in the x direction on the three paths
		this.textures=textures;
		this.game=game;
		addObject(new Trustee(r.nextInt(GamePanel.WIDTH), 193, textures,this,game));
		addObject(new Trustee(r.nextInt(GamePanel.WIDTH), 325, textures,this,game));
		addObject(new Trustee(r.nextInt(GamePanel.WIDTH), 458, textures,this,game));

	}

	public void tick() {
		//Player Class
		for (int i = 0; i < po.size(); i++) {
			playerObj = po.get(i);
			playerObj.tick();
		}
		
		//Enemy Class
		for (int i = 0; i < eo.size(); i++) {
			enemyObj = eo.get(i);
			enemyObj.tick();
		}
	}

	public void render(Graphics g) {
		//Player Class
		for (int i = 0; i < po.size(); i++) {
			playerObj = po.get(i);
			playerObj.render(g);
		}
		//Enemy Class
		for (int i = 0; i < eo.size(); i++) {
			enemyObj = eo.get(i);
			enemyObj.render(g);
		}
	}
	
	// Player Constructors/Destructor
	public void addObject(PlayerObjects block) {
		po.add(block);
	}

	public void removeObject(PlayerObjects block) {
		po.remove(block);
	}

	// Enemy Constructors/Destructor
	public void addObject(EnemyObjects block) {
		eo.add(block);
	}

	public void removeObject(EnemyObjects block) {
		eo.remove(block);
	}
	
	public LinkedList<PlayerObjects> getPlayerObjects(){
		return po;
	}
	public LinkedList<EnemyObjects> getEnemyObjects(){
		return eo;
	}

}
