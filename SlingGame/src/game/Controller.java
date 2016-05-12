package game;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;


import objects.EnemyObjects;
import objects.PlayerObjects;
import objects.Trustee;


/**
 * The controller class will control the game movement such as:
 * trustee, spawning, bean bag spawning, movement/direction of trustees
 */

public class Controller {

	//Linked list of game objects (trustee, beanbag, etc)
	private LinkedList<PlayerObjects> po = new LinkedList<PlayerObjects>();
	private LinkedList<EnemyObjects> eo = new LinkedList<EnemyObjects>();
	//ScoreBoard Counter
	private static int score = 0;
	
	PlayerObjects playerObj;
	EnemyObjects enemyObj;

	private Random r = new Random(); //random num generator for random spawning

	Textures textures;
	GamePanel game;
	
	//audio

	
	public Controller(Textures textures, GamePanel game) {
		//for now just randomly spawning in the x direction on the three paths
		this.textures=textures;
		this.game=game;
		addObject(new Trustee(r.nextInt(GamePanel.WIDTH), 206, textures,this,game));
		addObject(new Trustee(r.nextInt(GamePanel.WIDTH), 338, textures,this,game));
		addObject(new Trustee(r.nextInt(GamePanel.WIDTH), 473, textures,this,game));
		
		addObject(new Trustee(r.nextInt(GamePanel.WIDTH), 206, textures,this,game));
		addObject(new Trustee(r.nextInt(GamePanel.WIDTH), 338, textures,this,game));
		addObject(new Trustee(r.nextInt(GamePanel.WIDTH), 473, textures,this,game));
		
		addObject(new Trustee(r.nextInt(GamePanel.WIDTH), 206, textures,this,game));
		addObject(new Trustee(r.nextInt(GamePanel.WIDTH), 338, textures,this,game));
		addObject(new Trustee(r.nextInt(GamePanel.WIDTH), 473, textures,this,game));
		
		addObject(new Trustee(r.nextInt(GamePanel.WIDTH), 206, textures,this,game));
		addObject(new Trustee(r.nextInt(GamePanel.WIDTH), 338, textures,this,game));
		addObject(new Trustee(r.nextInt(GamePanel.WIDTH), 473, textures,this,game));
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
		//Draws the score +10 if it hits a trustee
		g.drawString("Score " + score, 75 , 675);
		
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
		//TODO uncomment for audio
		//AudioPlayer.getSound("bbSound").play();
		
		
	}
	//Removes bean bag from the screen
	public void removeObject(PlayerObjects block) {
		po.remove(block);
		//TODO uncomment for audio
		//AudioPlayer.getSound("trSound").play();
	}

	// Enemy Constructors/Destructor
	public void addObject(EnemyObjects block) {
		eo.add(block);
	}
	//Removes the trustees from the list
	public void removeObject(EnemyObjects block) {
		eo.remove(block);
		score += 10;
	}
	
	public LinkedList<PlayerObjects> getPlayerObjects(){
		return po;
	}
	public LinkedList<EnemyObjects> getEnemyObjects(){
		return eo;
	}

}
