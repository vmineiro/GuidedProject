/*
 * Maze Editor Dialog
 */
package maze.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import maze.logic.*;
import maze.logic.Dragon.Mode;


/**
 * The Class MazeEditorDialog.
 */
public class MazeEditorDialog extends JDialog {

	/** The play game. */
	public boolean playGame = false;

	/** The new game. */
	private Game newGame;

	/** The board. */
	private String lab[][];

	/** The symbol selected. */
	private String symbolSelected;

	/** The maze size. */
	private int mazeSize;
	
	/** The mode. */
	private int mode;
	
	/** The n dragons. */
	private int nDragons = 0;
	
	/** The number of heros. */
	private int nHero = 0;
	
	/** The number of exits. */
	private int nExit = 0;
	
	/** The number of swords. */
	private int nSword = 0;

	/** The windows width. */
	private int xSize = 775;
	
	/** The windows height. */
	private int ySize = 665;

	/** The input size dialog. */
	private InputSizeJDialog inputSizeDialog;

	/** The maze viewer. */
	private MazeViewer mazeViewer;
	
	/** The cells symbols panel. */
	private JPanel cellsSymbolsPanel;
	
	/** The exit panel. */
	private JPanel exitPanel;
	
	/** The dragon mode panel. */
	private JPanel dragonModePanel;
	
	/** The buttons panel. */
	private JPanel buttonsPanel;

	/** The cells symbols group button. */
	private ButtonGroup cellsSymbolsGrp;

	/** The wall button. */
	private JToggleButton wallButton;
	
	/** The path button. */
	private JToggleButton pathButton;
	
	/** The dragon button. */
	private JToggleButton dragonButton;
	
	/** The sword button. */
	private JToggleButton swordButton;
	
	/** The hero button. */
	private JToggleButton heroButton;
	
	/** The exit button. */
	private JToggleButton exitButton;

	/** The dragon mode label. */
	private JLabel dragonModeLabel;
	
	/** The static radio button. */
	private JRadioButton staticRadioButton;
	
	/** The dynamic radio button. */
	private JRadioButton dynamicRadioButton;

	/** The play game button. */
	private JButton playGameButton;
	
	/** The save maze button. */
	private JButton saveMazeButton;
	
	/** The cancel button. */
	private JButton cancelButton;
	
	/** The frame. */
	private JFrame frame;


	/**
	 * Instantiates a new maze editor dialog.
	 *
	 * @param frame the frame
	 * @param modal the modal
	 * @param myMessage the my message
	 */
	public MazeEditorDialog (JFrame frame, boolean modal, String myMessage){	
		super(frame,modal);
		getContentPane().setLayout(new BorderLayout(0,0));
		
		this.frame = frame;

		inputSizeDialog = new InputSizeJDialog(frame, true, "Maze Editor");

		if (inputSizeDialog.getSizeInput()){

			mazeSize = inputSizeDialog.getMazeSize();

			if (mazeSize > 25){
				xSize = 890;
				ySize = 780;
			} 

			getContentPane().setPreferredSize(new Dimension(xSize,ySize));

			/* Initialize an empty Maze */
			lab = new String[mazeSize][mazeSize];

			fillMaze(lab, mazeSize);

			createWidgets();
			addWidgets(getContentPane());

			pack();
			setLocationRelativeTo(frame);
			setVisible(true);

		} else {
			playGame = false;
			setVisible(false);
		}

	}

	
	/**
	 * Adds the widgets.
	 *
	 * @param contentPane the container pane
	 */
	private void addWidgets(Container contentPane) {

		contentPane.add(mazeViewer,BorderLayout.CENTER);

		cellsSymbolsGrp.add(wallButton);
		cellsSymbolsGrp.add(pathButton);
		cellsSymbolsGrp.add(dragonButton);
		cellsSymbolsGrp.add(heroButton);
		cellsSymbolsGrp.add(swordButton);
		cellsSymbolsGrp.add(exitButton);

		cellsSymbolsPanel.add(wallButton);
		cellsSymbolsPanel.add(pathButton);
		cellsSymbolsPanel.add(dragonButton);
		cellsSymbolsPanel.add(heroButton);
		cellsSymbolsPanel.add(swordButton);
		cellsSymbolsPanel.add(exitButton);

		exitPanel.add(playGameButton);
		exitPanel.add(saveMazeButton);
		exitPanel.add(cancelButton);

		dragonModePanel.add(new JLabel(""));
		dragonModePanel.add(dragonModeLabel);
		dragonModePanel.add(staticRadioButton);
		dragonModePanel.add(dynamicRadioButton);

		buttonsPanel.add(cellsSymbolsPanel,BorderLayout.NORTH);
		buttonsPanel.add(dragonModePanel,BorderLayout.CENTER);
		buttonsPanel.add(exitPanel,BorderLayout.SOUTH);


		contentPane.add(buttonsPanel,BorderLayout.WEST);


	}

	
	/**
	 * Creates the widgets.
	 */
	private void createWidgets() {

		ImageIcon wallIco =  new ImageIcon("textures/wall01.gif");
		ImageIcon floorIco =  new ImageIcon("textures/floor01.gif");
		ImageIcon dragonIco =  new ImageIcon("textures/dragon.gif");
		ImageIcon heroEagleIco =  new ImageIcon("textures/knight_falcon.gif");
		ImageIcon swordIco =  new ImageIcon("textures/swor02.gif");
		ImageIcon exitIco =  new ImageIcon("textures/DungeonDoor.gif");

		mazeViewer = new MazeViewer();
		mazeViewer.setFocusable(true);

		// Exit Buttons
		exitPanel = new JPanel();
		exitPanel.setLayout(new GridLayout(3,1,3,3));

		// Button - Save Maze
		playGameButton = new JButton("Play Game");
		playGameButton.addActionListener(new PlayGameListener());

		// Button - Save Maze
		saveMazeButton = new JButton("Save Maze");
		saveMazeButton.addActionListener(new SaveMazeListener());

		// Button - Cancel
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new CancelListener());

		cellsSymbolsPanel = new JPanel();
		cellsSymbolsPanel.setLayout(new GridLayout(6,1,3,3));

		// Button - Wall
		wallButton = new JToggleButton("Wall",(Icon) wallIco);
		wallButton.addActionListener(new WallButtonListener());

		// Button - Path
		pathButton = new JToggleButton("Path",(Icon) floorIco);
		pathButton.addActionListener(new PathButtonListener());

		// Button - Dragon
		dragonButton = new JToggleButton("Dragon",(Icon) dragonIco);
		dragonButton.addActionListener(new DragonButtonListener());		

		// Button - Hero
		heroButton = new JToggleButton("Hero",(Icon) heroEagleIco);
		heroButton.addActionListener(new HeroButtonListener());		

		// Button - Sword
		swordButton = new JToggleButton("Sword",(Icon) swordIco);
		swordButton.addActionListener(new SwordButtonListener());

		// Button - Exit
		exitButton = new JToggleButton("Exit",(Icon) exitIco);
		exitButton.addActionListener(new ExitButtonListener());

		cellsSymbolsGrp = new ButtonGroup();

		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BorderLayout(3, 3));
		buttonsPanel. setSize(50, ySize);

		// Mode Panel
		dragonModePanel = new JPanel();
		dragonModePanel.setLayout(new GridLayout(7,1,3,1));


		dragonModeLabel = new JLabel("Dragon Mode:");
		dragonModeLabel.setHorizontalAlignment(JLabel.CENTER);
		
		staticRadioButton = new JRadioButton("Static");
		staticRadioButton.setSelected(false);
		dynamicRadioButton = new JRadioButton("Dynamic");
		dynamicRadioButton.setSelected(true);

	}

	
	/**
	 * Gets the play game.
	 *
	 * @return the play game
	 */
	public boolean getPlayGame(){
		return playGame;
	}
	
	
	/**
	 * Gets the game.
	 *
	 * @return the game
	 */
	public Game getGame(){
		return newGame;
	}

	
	/**
	 * Fill all the cells from a given maze with "XX" value.
	 *
	 * @param lab the maze
	 * @param n the size of maze
	 */
	private static void fillMaze(String[][] lab, int n){

		for (int i = 0; i < n; i++){

			for (int j = 0; j< n; j++) {

				lab[i][j] = "XX";

			}

		}

	}	


	/**
	 * Builds the game.
	 */
	private void buildGame() {
		
		if(staticRadioButton.isSelected() && dynamicRadioButton.isSelected())
		{
			mode = 3;
		}
		else if(!staticRadioButton.isSelected())
		{
			mode = 2;
		}
		else
		{
			mode = 1;
		}

		Maze newMaze = new Maze();
		newMaze.setBoard(lab);
		String cell;

		for (int i = 0 ; i < mazeSize; i++){
			for (int j = 0 ; j < mazeSize; j++){
				cell = lab[i][j];
				if (cell.equals("SS")){
					newMaze.setExit(new Position(i, j));
					newGame.setMaze(newMaze);
				} else if (cell.equals("Ha")){
					newGame.getPlayer().setPosition(new Position(i, j));
				} else if (cell.equals("E ")){
					newGame.getSword().setPosition(new Position(i,j));
				} else if (cell.equals("D ")){
					Dragon newDragon;
					if (mode == 1){
						newDragon = new Dragon(new Position (i,j), Mode.STATIC);
					} else if (mode == 2) {
						newDragon = new Dragon(new Position (i,j), Mode.DYNAMIC);
					} else {
						newDragon = new Dragon(new Position (i,j), Mode.MIXED);
					}
					newGame.addDragon(newDragon);
				}
			}
		}
	}

	
	/**
	 * The listener interface for receiving wallButton events.
	 * The class that is interested in processing a wallButton
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addWallButtonListener<code> method. When
	 * the wallButton event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see WallButtonEvent
	 */
	private class WallButtonListener implements ActionListener
	{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			symbolSelected = "XX";
		}
	}

	
	/**
	 * The listener interface for receiving pathButton events.
	 * The class that is interested in processing a pathButton
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addPathButtonListener<code> method. When
	 * the pathButton event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see PathButtonEvent
	 */
	private class PathButtonListener implements ActionListener
	{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			symbolSelected = "  ";
		}
	}

	
	/**
	 * The listener interface for receiving dragonButton events.
	 * The class that is interested in processing a dragonButton
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addDragonButtonListener<code> method. When
	 * the dragonButton event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see DragonButtonEvent
	 */
	private class DragonButtonListener implements ActionListener
	{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			symbolSelected = "D ";
		}
	}

	
	/**
	 * The listener interface for receiving heroButton events.
	 * The class that is interested in processing a heroButton
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addHeroButtonListener<code> method. When
	 * the heroButton event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see HeroButtonEvent
	 */
	private class HeroButtonListener implements ActionListener
	{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			symbolSelected = "Ha";
		}
	}

	
	/**
	 * The listener interface for receiving swordButton events.
	 * The class that is interested in processing a swordButton
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addSwordButtonListener<code> method. When
	 * the swordButton event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see SwordButtonEvent
	 */
	private class SwordButtonListener implements ActionListener
	{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			symbolSelected = "E ";
		}
	}

	
	/**
	 * The listener interface for receiving exitButton events.
	 * The class that is interested in processing a exitButton
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addExitButtonListener<code> method. When
	 * the exitButton event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see ExitButtonEvent
	 */
	private class ExitButtonListener implements ActionListener
	{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			symbolSelected = "SS";
		}
	}
	
	
	/**
	 * The listener interface for receiving playGame events.
	 * The class that is interested in processing a playGame
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addPlayGameListener<code> method. When
	 * the playGame event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see PlayGameEvent
	 */
	private class PlayGameListener implements ActionListener
	{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				
				if (nDragons == 0) throw new HeadlessException("d");
				if (nSword != 1) throw new HeadlessException("e");
				if (nHero != 1) throw new HeadlessException("h");
				if (nExit != 1) throw new HeadlessException("s");
				
				newGame = new Game();
				buildGame();
				playGame = true;
				setVisible(false);
				
			} catch (Exception e1) {
				if (e1.getMessage().equals("d")){
					JOptionPane.showMessageDialog(frame,"Must have at least one dragon.");
				} else if (e1.getMessage().equals("e")){
					JOptionPane.showMessageDialog(frame,"Must have one sword.");
				} else if (e1.getMessage().equals("h")){
					JOptionPane.showMessageDialog(frame,"Must have one hero.");
				} else if (e1.getMessage().equals("s")){
					JOptionPane.showMessageDialog(frame,"Must have one exit.");
				}
			}
		}
	}

	
	/**
	 * The listener interface for receiving saveMaze events.
	 * The class that is interested in processing a saveMaze
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addSaveMazeListener<code> method. When
	 * the saveMaze event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see SaveMazeEvent
	 */
	private class SaveMazeListener implements ActionListener
	{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {


			try {
				
				if (nDragons == 0) throw new HeadlessException("d");
				if (nSword != 1) throw new HeadlessException("e");
				if (nHero != 1) throw new HeadlessException("h");
				if (nExit != 1) throw new HeadlessException("s");
				
				newGame = new Game();
				buildGame();

				SaveLoadDialog inputDialog = new SaveLoadDialog(frame, true, "Save/Load Game");

				if(inputDialog.gameSaved())	{
					
					try{
						newGame.saveGame(inputDialog.getFilePath());
						JOptionPane.showMessageDialog(frame,"Game saved.");

					} catch (IOException i) {
						JOptionPane.showMessageDialog(frame,"Error writing the file.");
						i.printStackTrace();

					}		
				} 

				playGame = false;
				setVisible(false);
				
			} catch (HeadlessException e1) {
				if (e1.getMessage().equals("d")){
					JOptionPane.showMessageDialog(frame,"Must have at least one dragon.");
				} else if (e1.getMessage().equals("e")){
					JOptionPane.showMessageDialog(frame,"Must have one sword.");
				} else if (e1.getMessage().equals("h")){
					JOptionPane.showMessageDialog(frame,"Must have one hero.");
				} else if (e1.getMessage().equals("s")){
					JOptionPane.showMessageDialog(frame,"Must have one exit.");
				}			
			}			
		}
	}


	/**
	 * The listener interface for receiving cancel events.
	 * The class that is interested in processing a cancel
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addCancelListener<code> method. When
	 * the cancel event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see CancelEvent
	 */
	private class CancelListener implements ActionListener
	{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			playGame = false;
			setVisible(false);
		}
	}


	
	/**
	 * The Class MazeViewer.
	 */
	private class MazeViewer extends JPanel {

		/** The elem_size. */
		int elem_size;
		
		/** The ajust size. */
		int ajust;

		/** The wall img. */
		private BufferedImage wallImg;
		
		/** The floor img. */
		private BufferedImage floorImg;
		
		/** The dragon img. */
		private BufferedImage dragonImg;
		
		/** The dragon sleep img. */
		private BufferedImage dragonSleepImg;
		
		/** The sword img. */
		private BufferedImage swordImg;
		
		/** The hero eagle img. */
		private BufferedImage heroEagleImg;
		
		/** The exit image. */
		private BufferedImage exitImage;

		
		/**
		 * Instantiates a new maze viewer.
		 */
		public MazeViewer()
		{

			elem_size = ySize/mazeSize;
			ajust = (ySize%mazeSize)/2;

			try {
				wallImg = ImageIO.read(new File("textures/wall01.png"));
				floorImg = ImageIO.read(new File("textures/floor01.jpg"));
				dragonImg = ImageIO.read(new File("textures/dragon80x80.jpeg"));
				swordImg = ImageIO.read(new File("textures/swor02.png"));
				heroEagleImg = ImageIO.read(new File("textures/knight_falcon.png"));
				dragonSleepImg = ImageIO.read(new File("textures/sleeping-dragon.jpg"));
				exitImage = ImageIO.read(new File("textures/DungeonDoor.png"));

			} catch (IOException e) {}

			getContentPane().addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {}

				@Override
				public void mousePressed(MouseEvent e) {}

				@Override
				public void mouseExited(MouseEvent e) {}

				@Override
				public void mouseEntered(MouseEvent e) {}

				@Override
				public void mouseClicked(MouseEvent e) {

					int i = (e.getY()-ajust)/elem_size;
					int j = (e.getX()-ajust-buttonsPanel.getWidth())/elem_size;

					try {

						if ((( i == 0 || i == mazeSize-1 )  && (j > 0 && j < mazeSize-1)) ||
								(( i > 0 && i < mazeSize-1 )  && (j == 0 || j == mazeSize-1))){
							if (symbolSelected.equals("SS")) {
								if (nExit != 0) {
									throw new Exception("s0");
								} else {
									nExit++;
								}
							} else if (!symbolSelected.equals("XX")){
								throw  new Exception("b");
							}
						} else if (symbolSelected.equals("Ha")){
							if (nHero != 0) {
								throw new Exception("h");
							} else {
								nHero++;
							}
						} else if (symbolSelected.equals("E ")) {
							if (nSword != 0) {
								throw new Exception("e");
							} else {
								nSword++;
							}
						} else if (symbolSelected.equals("SS")) {
							throw new Exception("s1");
						} else if (symbolSelected.equals("D ")) {
							nDragons++;
						}					

						String cell = lab[i][j];

						if (cell.equals("Ha")) {
							nHero--;
						} else if (cell.equals("E ")) {
							nSword--;
						} else if (cell.equals("SS")) {
							nExit--;
						} else if (cell.equals("D ")) {
							nDragons--;
						}

						lab[i][j] = symbolSelected;

						repaint();						

					} catch (Exception h) {
						if (h.getMessage().equals("h")){
							JOptionPane.showMessageDialog(getParent(),"You can only have one Hero.");
						} else if (h.getMessage().equals("e")){
							JOptionPane.showMessageDialog(getParent(),"You can only have one Sword.");
						} else if (h.getMessage().equals("s0")){
							JOptionPane.showMessageDialog(getParent(),"You can only have one Exit.");
						} else if (h.getMessage().equals("s1")){
							JOptionPane.showMessageDialog(getParent(),"Exit must be in the maze bordes, except corners.");
						}else if (h.getMessage().equals("b")){
							JOptionPane.showMessageDialog(getParent(),"The border must be wall or exit.");
						}
					}	

				}
			});

		}

		
		/* (non-Javadoc)
		 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
		 */
		public void paintComponent(Graphics g) 
		{
			super.paintComponent(g);

			for (int i=0; i<mazeSize; i++) 
			{
				for (int j=0; j<mazeSize;j++) 
				{
					if(lab[i][j].equals("XX"))
					{
						g.drawImage(wallImg, j*elem_size+ajust, i*elem_size+ajust, (j*elem_size)+elem_size+ajust, (i*elem_size)+elem_size+ajust, 0, 0, 256, 256, null);
					}
					else if(lab[i][j].equals("  "))
					{
						g.drawImage(floorImg, j*elem_size+ajust, i*elem_size+ajust, (j*elem_size)+elem_size+ajust, (i*elem_size)+elem_size+ajust, 0, 0, 400, 400, null);
					}
					else if(lab[i][j].equals("D "))
					{
						g.drawImage(dragonImg, j*elem_size+ajust, i*elem_size+ajust, (j*elem_size)+elem_size+ajust, (i*elem_size)+elem_size+ajust, 0, 0, 74, 74, null);
					}
					else if(lab[i][j].equals("d "))
					{
						g.drawImage(dragonSleepImg, j*elem_size+ajust, i*elem_size+ajust, (j*elem_size)+elem_size+ajust, (i*elem_size)+elem_size+ajust, 0, 0, 300, 234, null);
					}
					else if(lab[i][j].equals("E "))
					{
						g.drawImage(swordImg, j*elem_size+ajust, i*elem_size+ajust, (j*elem_size)+elem_size+ajust, (i*elem_size)+elem_size+ajust, 0, 0, 500, 500, null);
					}
					else if(lab[i][j].equals("Ha"))
					{
						g.drawImage(heroEagleImg, j*elem_size+ajust, i*elem_size+ajust, (j*elem_size)+elem_size+ajust, (i*elem_size)+elem_size+ajust, 0, 0, 116, 128, null);
					}
					else if(lab[i][j].equals("SS"))
					{
						g.drawImage(exitImage, j*elem_size+ajust, i*elem_size+ajust, (j*elem_size)+elem_size+ajust, (i*elem_size)+elem_size+ajust, 0, 0, 512, 512, null);
					}
				}
			}
		}
	}	


	
	/**
	 * The Class InputSizeJDialog.
	 */
	private class InputSizeJDialog extends JDialog{

		/** The size input. */
		private boolean sizeInput = false;
		
		/** The input maze size. */
		private int inputMazeSize;

		/** The input panel. */
		private JPanel inputPanel;
		
		/** The input buttons panel. */
		private JPanel inputButtonsPanel;

		/** The input label. */
		private JLabel inputLabel;
		
		/** The input field. */
		private JTextField inputField;
		
		/** The ok input button. */
		private JButton okInputButton;
		
		/** The cancel input button. */
		private JButton cancelInputButton;


		/**
		 * Instantiates a new input size j dialog.
		 *
		 * @param frame the frame
		 * @param modal the modal
		 * @param myMessage the my message
		 */
		public InputSizeJDialog (JFrame frame, boolean modal, String myMessage){

			super(frame,modal);

			getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));

			createInputSizeWidgets();
			addInputSizeWidgets(getContentPane());

			pack();
			setLocationRelativeTo(frame);
			setVisible(true);

		}
		

		/**
		 * Adds the input size widgets.
		 *
		 * @param contentPane the content pane
		 */
		private void addInputSizeWidgets(Container contentPane) {

			inputPanel.add(inputLabel);
			inputPanel.add(inputField);

			contentPane.add(inputPanel);

			inputButtonsPanel.add(cancelInputButton);
			inputButtonsPanel.add(okInputButton);
			contentPane.add(inputButtonsPanel);

		}
		

		/**
		 * Creates the input size widgets.
		 */
		private void createInputSizeWidgets() {

			// Input Panel
			inputPanel = new JPanel();
			inputPanel.setLayout(new GridLayout(1,2,10,10));

			// Input Content
			inputLabel = new JLabel("Size of Maze [10-35]:");

			inputField = new JTextField();
			inputField.setEditable(true);

			// Button Panel
			inputButtonsPanel = new JPanel();
			inputButtonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,5,5));

			// Button - OK
			okInputButton = new JButton("OK");
			okInputButton.addActionListener(new OkInputListener());

			// Button - CANCEL
			cancelInputButton = new JButton("Cancel");	
			cancelInputButton.addActionListener(new CancelInputButton());

		}


		/**
		 * Gets the size input.
		 *
		 * @return the size input
		 */
		public boolean getSizeInput() {
			return sizeInput;
		}
		

		/**
		 * Gets the maze size.
		 *
		 * @return the maze size
		 */
		public int getMazeSize() {
			return inputMazeSize;
		}

		
		/**
		 * The listener interface for receiving okInput events.
		 * The class that is interested in processing a okInput
		 * event implements this interface, and the object created
		 * with that class is registered with a component using the
		 * component's <code>addOkInputListener<code> method. When
		 * the okInput event occurs, that object's appropriate
		 * method is invoked.
		 *
		 * @see OkInputEvent
		 */
		private class OkInputListener implements ActionListener
		{

			/** The temp size. */
			int tempSize;

			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					tempSize = Integer.parseInt(inputField.getText());
				}
				catch (NumberFormatException n) {
					JOptionPane.showMessageDialog(getParent(), "Size must be an Integer");
					return;
				}
				try {
					if (tempSize > 9 && tempSize < 36){
						inputMazeSize = tempSize;
						sizeInput = true;
						setVisible(false);
					} else {
						throw new Exception();
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(getParent(), "Size must be between 10 and 35");
				}
			}
		}
		

		/**
		 * The Class CancelInputButton.
		 */
		private class CancelInputButton implements ActionListener
		{
			
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {

				inputMazeSize = 0;
				sizeInput = false;
				setVisible(false);

			}
		}
	}	
}
