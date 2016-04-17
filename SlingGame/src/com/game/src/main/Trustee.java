package com.game.src.main;

import java.awt.Graphics;

public class Trustee {

	private double x, y;
	
	private Textures textures;

	public Trustee(double x, double y, Textures textures) {

		this.x = x;
		this.y = y;
		this.textures= textures;

	}
	
	public void tick(){
		y-=5;
		
	}
	
	public void render(Graphics g){
		g.drawImage(textures.trustee, (int)x,(int)y,null);
		
	}
}
