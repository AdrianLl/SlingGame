package loaders;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.GamePanel;

public class MouseInput implements MouseListener{
	
	GamePanel game;
	
	public MouseInput(GamePanel game) {
		this.game = game;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		//Play button
		if(mx >= GamePanel.WIDTH/2 + 100 && mx <= GamePanel.WIDTH/2 + 200){
			if(my>=300 && my<=120){
				//Pressed play button
				//GamePanel.State = GamePanel.STATE.GAME;
			}
		}
			game.mousePressed(e);	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	
}
