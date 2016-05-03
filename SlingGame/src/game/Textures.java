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

	public BufferedImage[] trustees = new BufferedImage[14];
	public BufferedImage canon, beanbag;

	public Textures(GamePanel game) {
		ss = new SpriteSheet(game.getSpriteSheet());
		getTextures();
	}

	/*
	 * loads all images that are to be used on the screen and require movement.
	 */
	private void getTextures() {
		// col,row,size
		canon = ss.grabImage(1, 1, 90, 90);
		beanbag = ss.grabImage(2, 1, 90, 90);

		trustees[0] = ss.grabImage(1, 4, 90, 90);
		trustees[1] = ss.grabImage(2, 4, 90, 90);
		trustees[2] = ss.grabImage(3, 4, 90, 90);
		trustees[3] = ss.grabImage(4, 4, 90, 90);
		trustees[4] = ss.grabImage(5, 4, 90, 90);

	}

}
