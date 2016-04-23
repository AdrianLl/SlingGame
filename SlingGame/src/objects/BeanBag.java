package objects;

import java.awt.Graphics;

import game.Textures;
/**
 * The BeanBag class will describe the bean bags.
 * Details such as (x,y) location can be set as well as the texture being used
 * Movement and rendering will also be described here
 *
 */
public class BeanBag implements GameObject{

	//(x,y) coordinate of beanbag on the GamePanel
	private double x;
	private double y;

	//loads the images that is set for the beanbag
	private Textures texture;

	/**
	 * Constructor for a BeanBag
	 * @param x - location within x coordinate
	 * @param y - location within y coordinate
	 * @param texture - image loaded for beanbag
	 */
	public BeanBag(double x, double y, Textures texture) {
		this.x = x;
		this.y = y;
		this.texture = texture;
	}
	/**
	 * The tick method dictates beanbag movement in the x or y direction.
	 * Depending on the movement that is added per tick it will also determine movement speed
	 */

	public void tick() {
		//at the moment the bean bag is only moving in the y direction
		//moves up by 7 when shot out of the canon
		y -= 7;
	}

	public void render(Graphics g) {
		g.drawImage(texture.beanbag, (int) x, (int) y, null);
	}

	public double getY() {
		return y;
	}
	@Override
	public double getX() {
		return x;
	}
}
