package com.game.src.main;

import java.awt.image.BufferedImage;

public class Textures {

	private SpriteSheet ss;

	public BufferedImage player,beanbag,trustee;

	public Textures(GamePanel game){
		ss = new SpriteSheet(game.getSpriteSheet());
		
		getTextures();		
	}

	private void getTextures() {
		//col,row,size
		player = ss.grabImage(1, 1, 90, 90);
		beanbag = ss.grabImage(2, 1, 90, 90);
		trustee = ss.grabImage(3, 1, 90, 90);
	}

}
