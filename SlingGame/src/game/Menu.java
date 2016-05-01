package game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	public Rectangle playButton = new Rectangle(GamePanel.WIDTH/2-150, 395, 300, 80);
	public Rectangle menuButton = new Rectangle(GamePanel.WIDTH/2-150, 490, 300, 80);
	public Rectangle helpButton = new Rectangle(GamePanel.WIDTH/2-150, 585, 300, 80);
	public Rectangle quitButton = new Rectangle(GamePanel.WIDTH/2-150, 680, 300, 80);
	
			
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		Font font1 = new Font("chiller", Font.BOLD, 150);
		g.setFont(font1);
		g.setColor(Color.black);
		g.drawString("Trustee Down", 200, 200);

		Font font2 = new Font("cooper black", Font.BOLD, 30);
		g.setFont(font2);
		g.drawString("PLAY", playButton.x + 106, playButton.y + 50);
		g2d.draw(playButton);
		g.drawString("MENU", menuButton.x + 106, menuButton.y + 50);
		g2d.draw(menuButton);
		g.drawString("HELP", helpButton.x + 106, helpButton.y + 50);
		g2d.draw(helpButton);
		g.drawString("QUIT", quitButton.x + 106, quitButton.y + 50);
		g2d.draw(quitButton);
		
	}
}
