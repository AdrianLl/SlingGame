package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
/**
 * This interface was made to improve game controller.
 * It simplifies the tick() and render() function for game objects
 */
public interface PlayerObjects{
	public void tick();
	public void render(Graphics g);
	public Rectangle getBounds();
	
	public double getX();
	public double getY();
}
