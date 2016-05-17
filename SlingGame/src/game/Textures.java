package game;

import java.awt.image.BufferedImage;

import loaders.SpriteSheet;

/**
 * Textures will load up all images from the sprite sheet. This class was made
 * so a different version of the sprite sheet doesn't need to be loaded every
 * time that another images needs to go in.
 */
public class Textures {

	private SpriteSheet ss;

	public BufferedImage[] trusteeF = new BufferedImage[14]; // walk forward
	public BufferedImage[] trusteeB = new BufferedImage[14]; // walk backward
	
	public BufferedImage[] deanF = new BufferedImage[14]; // walk forward
	public BufferedImage[] deanB = new BufferedImage[14]; // walk backward
	
	public BufferedImage[] studentF = new BufferedImage[14]; // walk forward
	public BufferedImage[] studentB = new BufferedImage[14]; // walk backward
	
	public BufferedImage canon;
	
	public BufferedImage beanbagRED;
	public BufferedImage beanbagPURPLE;
	public BufferedImage beanbagPINK;
	public BufferedImage beanbagGREEN;
	public BufferedImage beanbagBLUE;

	public Textures(GamePanel game) {
		ss = new SpriteSheet(game.getSpriteSheet());
		getTextures();
	}

	/*
	 * loads all images that are to be used on the screen and require movement.
	 */
	private void getTextures() {
		// col,row,size
			
		beanbagGREEN = ss.grabImage(1, 19, 20, 20);
		beanbagPINK = ss.grabImage(2, 19, 20, 20);
		beanbagBLUE = ss.grabImage(3, 19, 20, 20);
		beanbagRED = ss.grabImage(4, 19, 20, 20);
		beanbagPURPLE = ss.grabImage(5, 19, 20, 20);
		

		for (int i = 0, j = 1; i < 14; i++, j++) {

			trusteeF[i] = ss.grabImage(j, 6, 50, 70);
			trusteeB[i] = ss.grabImage(j, 8, 50, 70);

		}
		
		for (int i = 0, j = 1; i < 14; i++, j++) {

			deanF[i] = ss.grabImage(j, 10, 50, 70);
			deanB[i] = ss.grabImage(j, 12, 50, 70);

		}
		
		for (int i = 0, j = 1; i < 14; i++, j++) {

			studentF[i] = ss.grabImage(j, 14, 50, 70);
			studentB[i] = ss.grabImage(j, 16, 50, 70);

		}

	}

}
