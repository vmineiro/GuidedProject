package maze.gui;

import maze.logic.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;


public class GUInterface {

	private JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					GUInterface window = new GUInterface();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUInterface() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		// MAZE PANEL
		MazePanel mazePanel = new MazePanel();
		mazePanel.setFocusable(true);
		frame.getContentPane().add(mazePanel, BorderLayout.CENTER);
	}

}
