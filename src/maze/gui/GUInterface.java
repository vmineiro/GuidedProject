/*
 * Graphic User Interface
 */
package maze.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * The Class GUInterface.
 */
public class GUInterface {

	/** The frame. */
	private JFrame frame;
	
	/** The maze panel. */
	private MazePanel mazePanel;
	
	/** The buttons panel. */
	private JPanel buttonsPanel;

	/** The settings dialog. */
	private ConfigDialog configDialog;
	
	/** The save load dialog. */
	private SaveLoadDialog saveLoadDialog;
	
	/** The maze editor panel. */
	private MazeEditorDialog mazeEditorPanel;

	/** The new game button. */
	private JButton newGame;
	
	/** The game settings button. */
	private JButton configGame;
	
	/** The exit game button. */
	private JButton exitGame;
	
	/** The save load game button. */
	private JButton saveLoadGame;
	
	/** The create maze button. */
	private JButton createMaze;

	/**
	 * Launch the application.
	 */
	public static void startGameGUI() 
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
		frame.setPreferredSize(new Dimension(665, 735));
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		createWidgets();
		addWidgets(frame.getContentPane());

		frame.pack();
		frame.setVisible(true);		
	}
	

	/**
	 * Creates the widgets.
	 */
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

		// Button - SAVE GAME
		saveLoadGame = new JButton("Save/Load Game");
		saveLoadGame.addActionListener(new SaveLoadGameListener());

		// Button - EXIT GAME
		exitGame = new JButton("Exit Game");	
		exitGame.addActionListener(new ExitGameListener());

		// Button - GAME CONFIGURATION
		configGame = new JButton("Game Settings");	
		configGame.addActionListener(new configGameListener());

		// Button - CREATE MAZE
		createMaze = new JButton("Create Maze");	
		createMaze.addActionListener(new CreateMazeListener());

	}

	
	/**
	 * Adds the widgets.
	 *
	 * @param cont the cont
	 */
	private void addWidgets(Container cont)
	{
		buttonsPanel.add(newGame);
		buttonsPanel.add(saveLoadGame);
		buttonsPanel.add(configGame);
		buttonsPanel.add(createMaze);
		buttonsPanel.add(exitGame);

		cont.add(mazePanel, BorderLayout.CENTER);
		cont.add(buttonsPanel, BorderLayout.SOUTH);
	}

	
	/**
	 * The listener interface for receiving newGame events.
	 * The class that is interested in processing a newGame
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addNewGameListener<code> method. When
	 * the newGame event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see NewGameEvent
	 */
	private class NewGameListener implements ActionListener
	{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
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

			mazePanel.repaint();
			mazePanel.requestFocusInWindow();
		}	
	}

	
	/**
	 * The listener interface for receiving exitGame events.
	 * The class that is interested in processing a exitGame
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addExitGameListener<code> method. When
	 * the exitGame event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see ExitGameEvent
	 */
	private class ExitGameListener implements ActionListener
	{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
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

	
	/**
	 * The listener interface for receiving configGame events.
	 * The class that is interested in processing a configGame
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addconfigGameListener<code> method. When
	 * the configGame event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see configGameEvent
	 */
	private class configGameListener implements ActionListener
	{
		

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// JDialog - GAME SETTINGS
			configDialog = new ConfigDialog(frame, true, "Game Settings");

			if(configDialog.getConfigOption())
			{
				mazePanel.setMazeSize(configDialog.getSelMazeSize());
				mazePanel.setNDragons(configDialog.getSelNDragons());
				mazePanel.setModeGame(configDialog.getSelMode());
				mazePanel.setBuilder(configDialog.getSelBuider());

				mazePanel.setUpKey(configDialog.getSelUpKey());
				mazePanel.setDownKey(configDialog.getSelDownKey());
				mazePanel.setLeftKey(configDialog.getSelLeftKey());
				mazePanel.setRightKey(configDialog.getSelRightKey());
				mazePanel.setEagleKey(configDialog.getSelEagleKey());

				String newGameMsg = "New Game?";
				int reply = JOptionPane.showConfirmDialog(frame,newGameMsg,"New Game",JOptionPane.YES_NO_OPTION);

				if(reply == JOptionPane.YES_OPTION)
				{
					mazePanel.launchNewGame();
					
					if (mazePanel.getGame().getMaze().getBoard().length > 25){
						frame.setSize(780, 850);
					} else {
						frame.setSize(frame.getPreferredSize());
					}
					
					mazePanel.repaint();
				}
				else if(reply == JOptionPane.NO_OPTION || reply == JOptionPane.CLOSED_OPTION){}	
			}
			mazePanel.requestFocusInWindow();
		}
		
	}
	

	/**
	 * The listener interface for receiving saveLoadGame events.
	 * The class that is interested in processing a saveLoadGame
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addSaveLoadGameListener<code> method. When
	 * the saveLoadGame event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see SaveLoadGameEvent
	 */
	private class SaveLoadGameListener implements ActionListener
	{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// JDialog - SAVE/LOAD GAME
			saveLoadDialog = new SaveLoadDialog(frame, true, "Save/Load Game");

			if(saveLoadDialog.gameSaved())
			{
				try
				{
					mazePanel.getGame().saveGame(saveLoadDialog.getFilePath());
					JOptionPane.showMessageDialog(frame,"Game saved.");
				}
				catch (IOException i)
				{
					JOptionPane.showMessageDialog(frame,"Error writing the file.");
				}		
			} 
			else if (saveLoadDialog.gameLoaded())
			{
				String newGameMsg = "Load Game?";
				int reply = JOptionPane.showConfirmDialog(frame,newGameMsg,"Load Game",JOptionPane.YES_NO_OPTION);

				if(reply == JOptionPane.YES_OPTION)
				{
					try
					{
						mazePanel.loadGame(saveLoadDialog.getFilePath());
					}
					catch (IOException i )
					{
						JOptionPane.showMessageDialog(frame,"File not found.");
					}
					catch (ClassNotFoundException i)
					{
						JOptionPane.showMessageDialog(frame,"File not supported.");
					}
				}
				else if(reply == JOptionPane.NO_OPTION || reply == JOptionPane.CLOSED_OPTION){}	
			}

			saveLoadDialog.setSaveGame(false);
			saveLoadDialog.setLoadGame(false);
			
			if (mazePanel.getGame().getMaze().getBoard().length > 25)
			{
				frame.setSize(780, 850);;
			}
			else
			{
				frame.setSize(frame.getPreferredSize());
			}
			
			mazePanel.repaint();
			mazePanel.requestFocusInWindow();
		}
		
	}
	
	
	/**
	 * The listener interface for receiving createMaze events.
	 * The class that is interested in processing a createMaze
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addCreateMazeListener<code> method. When
	 * the createMaze event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see CreateMazeEvent
	 */
	private class CreateMazeListener implements ActionListener
	{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) 
		{					
			// JDialog - Maze Editor
			mazeEditorPanel = new MazeEditorDialog(frame, true, "Maze Editor");	
			
			if (mazeEditorPanel.playGame)
			{
				mazePanel.getGame().setGame(mazeEditorPanel.getGame());
			}
			
			if (mazePanel.getGame().getMaze().getBoard().length > 25)
			{
				frame.setSize(780, 850);;
			} 
			else
			{
				frame.setSize(frame.getPreferredSize());
			}
			
			mazePanel.repaint();
			mazePanel.requestFocusInWindow();					
		}	
		
	}

}
