package com.game.src.main;

import java.awt.Graphics;

public class Trustee {

	private double x, y;
	
	private Textures textures;

	/**
	 * Trustee constructor
	 * @param x - x coordinate for trustee start
	 * @param y - y coordinate for trustee start
	 * @param textures
	 */
	public Trustee(double x, double y, Textures textures) {

		this.x = x;
		this.y = y;
		this.textures= textures;

	}
	
	public void tick(){
		x+=2;
		
	}
	
	public void render(Graphics g){
		g.drawImage(textures.trustee, (int)x,(int)y,null);
		
	}
}
