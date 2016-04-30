package objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.GamePanel;
import game.Collisions;
import game.Textures;
/**
 * The BeanBag class will describe the bean bags.
 * Details such as (x,y) location can be set as well as the texture being used
 * Movement and rendering will also be described here
 *
 */
public class BeanBag extends GameObject implements PlayerObjects{

	private GamePanel game;
	
	//(x,y) coordinate of beanbag on the GamePanel
	private double xDir;
	private double yDir;

	//loads the images that is set for the beanbag
	private Textures texture;

	/**
	 * Constructor for a BeanBag
	 * @param x - location within x coordinate
	 * @param y - location within y coordinate
	 * @param texture - image loaded for beanbag
	 */
	public BeanBag(double x, double y, Textures texture,GamePanel game) {
		super(x,y);
		this.texture = texture;
		this.game=game;
	}
	
	public BeanBag(double x, double y, Textures texture, double xDir,double yDir, GamePanel game){
		super(x,y);
		this.texture = texture;
		this.xDir=xDir;
		this.yDir=yDir;
		this.game=game;
	}
	/**
	 * The tick method dictates beanbag movement in the x or y direction.
	 * Depending on the movement that is added per tick it will also determine movement speed
	 */

	public void tick() {
		// at the moment the bean bag is only moving in the y direction
		// moves up by 7 when shot out of the canon
		x -= xDir / 100;
		y -= yDir / 100;
		
		if(Collisions.Collision(this, game.eo)){
			
			System.out.println("COLLISION BETWEEN BEAN BAG AND TRUSTEE");
			
		}

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
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,90,90);
	}
}
