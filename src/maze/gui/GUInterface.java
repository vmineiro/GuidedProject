package maze.gui;

import maze.logic.*;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.FlowLayout;

public class GUInterface {
	
	private JFrame frame;
	private MazePanel mazePanel;
	private JPanel buttonsPanel;
	
	private JButton newGame;
	private JButton configGame;
	private JButton exitGame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		try 
		{
			GUInterface window = new GUInterface();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public GUInterface() 
	{
		frame = new JFrame("MAZE GAME");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(500, 550));
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		createWidgets();
		addWidgets(frame.getContentPane());
		
		frame.pack();
		frame.setVisible(true);		
	}
	
	private void createWidgets()
	{
		// Maze Panel
		mazePanel = new MazePanel();
		mazePanel.setFocusable(true);
		
		// Buttons Panel
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// Button - NEW GAME
		newGame = new JButton("New Game");
		newGame.addActionListener(new NewGameListener());
		
		// Button - EXIT GAME
		exitGame = new JButton("Exit Game");	
		exitGame.addActionListener(new ExitGameListener());

		// Button - EXIT GAME
		configGame = new JButton("Game Configuration");	
		configGame.addActionListener(new configGameListener());
		
	}
	
	private void addWidgets(Container cont)
	{
		buttonsPanel.add(newGame);
		buttonsPanel.add(configGame);
		buttonsPanel.add(exitGame);
		
		cont.add(mazePanel, BorderLayout.CENTER);
		cont.add(buttonsPanel, BorderLayout.SOUTH);
	}
	
	private class NewGameListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String newGameMsg = "New Game?";
			int reply = JOptionPane.showConfirmDialog(frame,newGameMsg,"New Game",JOptionPane.YES_NO_OPTION);
			
			if(reply == JOptionPane.YES_OPTION)
			{
				mazePanel.launchNewGame();
			}
			else if(reply == JOptionPane.NO_OPTION || reply == JOptionPane.CLOSED_OPTION){}	
			
			mazePanel.requestFocusInWindow();
		}	
	}
	
	private class ExitGameListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String exitGameMsg = "Quit Game?";
			int reply = JOptionPane.showConfirmDialog(frame,exitGameMsg,"Exit Game",JOptionPane.YES_NO_OPTION);
			
			if(reply == JOptionPane.YES_OPTION)
			{
				frame.setVisible(false);
				System.exit(0);
			}
			else if(reply == JOptionPane.NO_OPTION || reply == JOptionPane.CLOSED_OPTION){}
			
			mazePanel.requestFocusInWindow();
		}
	}
	
	private class configGameListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
					
		}
		
	}

}
