package objects;

import java.awt.Graphics;
/**
 * This interface was made to improve game controller.
 * It simplifies the tick() and render() function for game objects
 */
public interface GameObject{
	public void tick();
	public void render(Graphics g);
	
	public double getX();
	public double getY();
}
