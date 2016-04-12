package com.game.src.main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * 
 * Buffer Image Loader will load image sources into the game.
 * This will be used for images only such as PNG for now.
 *
 */
public class BufferedImageLoader {

	private BufferedImage image;

	public BufferedImage loadImage(String path) throws IOException {

		image = ImageIO.read(getClass().getResource(path));
		return image;
	}

}
