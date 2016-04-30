package game;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String args[]) {
		
		int WIDTH = 1024;
		int HEIGHT = 768;

		GamePanel game = new GamePanel(); // game panel initialized
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		game.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		game.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		// window dimensions for game panel are set above

		JFrame frame = new JFrame(game.TITLE);
		// jframe with game tittle initialized

		frame.add(game); // adds the game panel to the frame
		frame.pack(); // sets desired sizes to the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// window close behavior
		frame.setResizable(false); // window cannot be changed in size
		frame.setLocationRelativeTo(null); // centers game panel
		frame.setVisible(true); // visibility
		game.start(); // start game thread
	}
}
