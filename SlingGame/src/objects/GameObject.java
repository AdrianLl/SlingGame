package objects;

import java.awt.Graphics;

public interface GameObject{
	public void tick();
	public void render(Graphics g);
	
	public double getX();
	public double getY();
}
