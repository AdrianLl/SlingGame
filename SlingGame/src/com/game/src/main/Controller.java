package com.game.src.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Controller {
	
	//private LinkedList<>
	
	private LinkedList<BeanBag> b = new LinkedList<BeanBag>();
	
	BeanBag TempBeanBag; 
	
	GamePanel game;
	
	public Controller(GamePanel game){
		this.game=game;
	}
	
	public void tick(){
		
		for(int i = 0; i< b.size();i++){
			TempBeanBag = b.get(i);
			
			if(TempBeanBag.getY() < 0)
				removeBeanBag(TempBeanBag);
			
			TempBeanBag.tick();
		}
		
	}
	
	public void render(Graphics g){
		for(int i = 0; i< b.size();i++){
			TempBeanBag = b.get(i);
			TempBeanBag.render(g);
		}		
	}
	
	public void addBeanBag(BeanBag block){
		b.add(block);
	}
	public void removeBeanBag(BeanBag block){
		b.remove(block);
	}
}
