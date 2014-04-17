package maze.gui;

import maze.logic.*;


import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUInterface {
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		try {
			JFrame frame = new JFrame("MAZE GAME");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setPreferredSize(new Dimension(500, 550));
			frame.getContentPane().setLayout(new BorderLayout(0, 0));
			
			// Main Panel
			JPanel mainPanel = new JPanel();
			frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
			mainPanel.setLayout(new BorderLayout(0, 0));
			
			// Maze Panel
			MazePanel mazePanel = new MazePanel();
			mainPanel.add(mazePanel, BorderLayout.CENTER);
			mazePanel.setFocusable(true);

			// Buttons Panel
			JPanel buttonsPanel = new JPanel();
			mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

			// Button - NEW GAME
			JButton newGame = new JButton("New Game");
			buttonsPanel.add(newGame);

			// Button - EXIT GAME
			JButton exitGame = new JButton("Exit Game");
			buttonsPanel.add(exitGame);
			
			frame.pack();
			frame.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public GUInterface() {}

}
