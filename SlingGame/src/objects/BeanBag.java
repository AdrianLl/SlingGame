package objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.Textures;
/**
 * The BeanBag class will describe the bean bags.
 * Details such as (x,y) location can be set as well as the texture being used
 * Movement and rendering will also be described here
 *
 */
public class BeanBag extends GameObject implements PlayerObjects{
	
	//(x,y) coordinate of beanbag on the GamePanel
	private double xDir;
	private double yDir;
	BeanBagColor color;

	//loads the images that is set for the beanbag
	private Textures texture;

	/**
	 * Constructor for a BeanBag
	 * @param x - location within x coordinate
	 * @param y - location within y coordinate
	 * @param texture - image loaded for beanbag
	 */
	public BeanBag(double x, double y, Textures texture) {
		super(x, y);
		this.texture = texture;
	}
	
	public BeanBag(double x, double y, Textures texture, double xDir, double yDir) {
		super(x, y);
		this.texture = texture;
		this.xDir = xDir;
		this.yDir = yDir;
	}
	
	public BeanBag(double x, double y, Textures texture, double xDir, double yDir, BeanBagColor color) {
		super(x, y);
		this.texture = texture;
		this.xDir = xDir;
		this.yDir = yDir;
		this.color = color;
	}
	
	/**
	 * The tick method dictates beanbag movement in the x or y direction.
	 * Depending on the movement that is added per tick it will also determine movement speed
	 */
	public void tick() {

		if (xDir >= 539) {
			x += (yDir - 150) / 100;
			y -= (1024 - xDir) / 100;
			//System.out.println(x + "," + y);

		} else {
			x -= (yDir - 150) / 100;
			y -= xDir / 100;
			//System.out.println(x + "," + y);
		}

	}

	public void render(Graphics g) {
		if(this.color==BeanBagColor.RED)
			g.drawImage(texture.beanbagRED, (int) x, (int) y, null);
		
		else if(this.color==BeanBagColor.PURPLE)
			g.drawImage(texture.beanbagPURPLE, (int) x, (int) y, null);
		
		else if(this.color==BeanBagColor.PINK)
			g.drawImage(texture.beanbagPINK, (int) x, (int) y, null);
		
		else if(this.color==BeanBagColor.GREEN)
			g.drawImage(texture.beanbagGREEN, (int) x, (int) y, null);
		
		else if(this.color==BeanBagColor.BLUE)
			g.drawImage(texture.beanbagBLUE, (int) x, (int) y, null);
	}

	public double getY() {
		return y;
	}
	@Override
	public double getX() {
		return x;
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,60,60);
	}
}
