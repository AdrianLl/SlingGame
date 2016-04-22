package objects;

import java.awt.Graphics;

import loaders.Textures;

public class BeanBag {

	private double x;
	private double y;

	private Textures texture;

	public BeanBag(double x, double y, Textures texture) {
		this.x = x;
		this.y = y;
		this.texture = texture;
	}

	public void tick() {
		y -= 7;
	}

	public void render(Graphics g) {
		g.drawImage(texture.beanbag, (int) x, (int) y, null);
	}

	public double getY() {
		return y;
	}
}
