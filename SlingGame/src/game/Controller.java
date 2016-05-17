package game;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;


import objects.EnemyObjects;
import objects.PlayerObjects;
import objects.ScoreBeanBag;
import objects.Trustee;


/**
 * The controller class will control the game movement such as:
 * trustee, spawning, bean bag spawning, movement/direction of trustees
 */

public class Controller {

	//Linked list of game objects (trustee, beanbag, etc)
	private LinkedList<PlayerObjects> po = new LinkedList<PlayerObjects>();
	private LinkedList<EnemyObjects> eo = new LinkedList<EnemyObjects>();
	private LinkedList<ScoreBeanBag> scoreBag = new LinkedList<ScoreBeanBag>();
	
	//ScoreBoard Counter
	private static int score = 0;
	
	PlayerObjects playerObj;
	EnemyObjects enemyObj;

	private Random r = new Random(); //random num generator for random spawning

	Textures textures;
	GamePanel game;
	
	private SoundFXPlayer soundFX;

	
	public Controller(Textures textures, GamePanel game) {
		//for now just randomly spawning in the x direction on the three paths
		
		soundFX = new SoundFXPlayer();
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
		
		int startXline1 = 764;
		int startYline1 = 647;

		int startXline2 = 764;
		int startYline2 = 673;

		for (int i = 0; i < 10; i++) {
			addScoreBag(new ScoreBeanBag(startXline1, startYline1, textures, GamePanel.colors[i]));
			startXline1 += 25;
		}
		for (int i = 10; i < 20; i++) {
			addScoreBag(new ScoreBeanBag(startXline2, startYline2, textures, GamePanel.colors[i]));
			startXline2 += 25;
		}
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
		
		for (int i = 0; i < scoreBag.size(); i++) {
			playerObj = scoreBag.get(i);
			playerObj.tick();
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
		
		for (int i = 0; i < scoreBag.size(); i++) {
			playerObj = scoreBag.get(i);
			playerObj.render(g);
		}
		
	}
	
	// Player Constructors/Destructor
	public void addObject(PlayerObjects block) {
		po.add(block);
		soundFX.playBbSound();
		
		
	}
	//Removes bean bag from the screen
	public void removeObject(PlayerObjects block) {
		po.remove(block);
		soundFX.playTrSound();
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
	
	public void addScoreBag(ScoreBeanBag block) {
		scoreBag.add(block);
	}
	
	public void removeScoreBag() {
		scoreBag.removeFirst();
	}
	
	public LinkedList<PlayerObjects> getPlayerObjects(){
		return po;
	}
	public LinkedList<EnemyObjects> getEnemyObjects(){
		return eo;
	}

}
