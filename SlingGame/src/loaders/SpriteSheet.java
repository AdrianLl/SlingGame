package loaders;

import java.awt.image.BufferedImage;

/**
 * Sprite sheet is the sprite sheet loader that will bring the sprite sheets
 * into the game for use.
 */
public class SpriteSheet {

	private BufferedImage image;

	/**
	 * Buffer image loader for loading in the sprite sheet
	 * 
	 * @param image
	 *            - sprite sheet that needs to be loaded
	 */
	public SpriteSheet(BufferedImage image) {
		this.image = image;
	}

	/**
	 * This grab image will take in the images on the actual sprite sheet
	 * depending on what needs to be loaded in.
	 * 
	 * @param col - column on the sprite sheet
	 * @param row - row on the sprite sheet
	 * @param width - width size width of the graphic
	 * @param height - height size of the graphic
	 * @return - the specified image within the spritesheet
	 */
	public BufferedImage grabImage(int col, int row, int width, int height) {

		BufferedImage img = image.getSubimage((col * 50) - 50, (row * 70) - 70, width, height);
		return img;

	}

}