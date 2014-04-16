package maze.gui;

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
	public static void main(String[] args) {
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
	public GUInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setPreferredSize(new Dimension(500, 500));
		
		// MAZE PANEL
		MazePanel mazePanel = new MazePanel();
		frame.getContentPane().add(mazePanel, BorderLayout.CENTER);
		
		// BUTTONS PANEL
		JPanel buttons = new JPanel();
		frame.getContentPane().add(buttons, BorderLayout.SOUTH);
		
		
	}

}
