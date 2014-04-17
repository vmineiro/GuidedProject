package maze.gui;

import maze.logic.*;


import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.BorderLayout;


public class GUInterface {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		try {
			JFrame frame = new JFrame("MAZE GAME");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setPreferredSize(new Dimension(500, 500));
			
			// MAZE PANEL
			MazePanel mazePanel = new MazePanel();
			mazePanel.setFocusable(true);
			frame.getContentPane().add(mazePanel, BorderLayout.CENTER);
			
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
