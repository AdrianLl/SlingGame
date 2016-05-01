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
			game.mousePressed(e);	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	
}
