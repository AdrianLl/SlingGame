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
public class ScoreBeanBag extends GameObject implements PlayerObjects{
	
	//(x,y) coordinate of beanbag on the GamePanel
	private Textures texture;
	private BeanBagColor color;

	/**
	 * Constructor for a BeanBag
	 * @param x - location within x coordinate
	 * @param y - location within y coordinate
	 * @param texture - image loaded for beanbag
	 * @param color - beanbag color
	 */
	public ScoreBeanBag(double x, double y, Textures texture, BeanBagColor color) {
		super(x, y);
		this.texture = texture;
		this.color = color;
	}
	
	/**
	 * The tick method dictates beanbag movement in the x or y direction.
	 * Depending on the movement that is added per tick it will also determine movement speed
	 */
	public void tick() {
		x+=0;

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
	
	public BeanBagColor getColor(){
		return color;
	}
	
	public void setColor(BeanBagColor color) {
		this.color = color;
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,20,20);
	}
}
