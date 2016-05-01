package game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	public Rectangle playButton = new Rectangle(300, 120, 150, 80);
	public Rectangle quitButton = new Rectangle(300, 220, 150, 80);
	public Rectangle helpButton = new Rectangle(300, 320, 150, 80);
			
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		Font font1 = new Font("arial",Font.BOLD,50);
		g.setFont(font1);
		g.setColor(Color.black);
		g.drawString("Shoot the Trustee", GamePanel.WIDTH/2,400);
		
		Font font2 = new Font("arial",Font.BOLD,30);
		g.setFont(font2);
		g.drawString("Play",playButton.x + 20,playButton.y + 30);
		g2d.draw(playButton);
		g.drawString("Quit",quitButton.x + 20,quitButton.y + 30);
		g2d.draw(quitButton);
		g.drawString("Help",helpButton.x + 20,helpButton.y + 30);
		g2d.draw(helpButton);
		
	}
}
