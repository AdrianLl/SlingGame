package loaders;

import java.awt.image.BufferedImage;

import game.GamePanel;
/**
 * Textures will load up all images from the sprite sheet.
 * This class was made so a different version of the sprite sheet doesn't need to be loaded
 * every time that another images needs to go in.
 */
public class Textures {

	private SpriteSheet ss;

	public BufferedImage canon, beanbag, trustee;

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
		trustee = ss.grabImage(3, 1, 90, 90);
	}

}
