package com.game.src.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class BeanBag {
	
	
	private double x;
	private double y;
	
	BufferedImage image;
	
	public BeanBag(double x, double y, GamePanel game){
		this.x=x;
		this.y=y;
		
		SpriteSheet ss=new SpriteSheet(game.getSpriteSheet());
		
		image = ss.grabImage(2, 1, 90, 90);
	}
	
	public void tick(){
		y -=7;
	}
	
	public void render(Graphics g){
		g.drawImage(image,(int)x,(int)y, null);
	}
	
	public double getY(){
		return y;
	}
}
