package com.game.src.main;

import java.awt.image.BufferedImage;

public class Textures {

	private SpriteSheet ss;

	public BufferedImage player,trustee;

	public Textures(GamePanel game){
		ss = new SpriteSheet(game.getSpriteSheet());
		
		getTextures();		
	}

	private void getTextures() {
		player = ss.grabImage(1, 1, 90, 90);
		trustee = ss.grabImage(1, 2, 90, 90);
	}

}
