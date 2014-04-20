/*
 * Save and Load Game Interface
 */
package maze.gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * The Class SaveLoadDialog.
 */
public class SaveLoadDialog extends JDialog{

	/** The save game. */
	private boolean saveGame = false;
	
	/** The load game. */
	private boolean loadGame = false;

	/** The path. */
	private String path;

	/** The text panel. */
	private JPanel textPanel;
	
	/** The path input panel. */
	private JPanel pathInputPanel;
	
	/** The buttons panel. */
	private JPanel buttonsPanel;

	/** The path input label1. */
	private JLabel pathInputLabel1;
	
	/** The path input label2. */
	private JLabel pathInputLabel2;
	
	/** The path input field. */
	private JTextField pathInputField;
	
	/** The save button. */
	private JButton saveButton;
	
	/** The load button. */
	private JButton loadButton;
	
	/** The cancel button. */
	private JButton cancelButton;

	
	/**
	 * Instantiates a new save load dialog.
	 *
	 * @param frame the frame
	 * @param modal the modal
	 * @param myMessage the my message
	 */
	public SaveLoadDialog(JFrame frame, boolean modal, String myMessage)
	{

		super(frame,modal);
		getContentPane().setLayout(new GridLayout(3,1,5,5));

		createWidgets();
		addWidgets(getContentPane());

		pack();
		setLocationRelativeTo(frame);
		setVisible(true);

	}

	
	/**
	 * Adds the widgets.
	 *
	 * @param contentPane the content pane
	 */
	private void addWidgets(Container contentPane) {

		textPanel.add(pathInputLabel1);
		contentPane.add(textPanel);
		
		pathInputPanel.add(pathInputLabel2);
		pathInputPanel.add(pathInputField);
		contentPane.add(pathInputPanel);

		buttonsPanel.add(saveButton);
		buttonsPanel.add(loadButton);
		buttonsPanel.add(cancelButton);
		contentPane.add(buttonsPanel);

	}

	
	/**
	 * Creates the widgets.
	 */
	private void createWidgets() {

		// Input Panel
		textPanel = new JPanel();
		textPanel.setLayout(new FlowLayout(FlowLayout.LEFT,5,3));

		// Input Panel
		pathInputPanel = new JPanel();
		pathInputPanel.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));

		pathInputLabel1 = new JLabel("All the files are saved in and load from the folder './saved_games/'");
		pathInputLabel2 = new JLabel("Insert file name:");

		pathInputField = new JTextField();
		pathInputField.setEditable(true);
		pathInputField.setColumns(25);

		// Buttons Panel
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 3));

		// Button - Save Game
		saveButton = new JButton("Save Game");
		saveButton.addActionListener(new SaveGameListener());

		// Button - Load Game
		loadButton = new JButton("Load Game");
		loadButton.addActionListener(new LoadGameListener());

		// Button - Cancel
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new CancelListener());

	}
	

	/**
	 * Game saved.
	 *
	 * @return true, if successful
	 */
	public boolean gameSaved() {
		return saveGame;
	}
	

	/**
	 * Gets the file path.
	 *
	 * @return the file path
	 */
	public String getFilePath() {
		return path;
	}
	

	/**
	 * Game loaded.
	 *
	 * @return true, if successful
	 */
	public boolean gameLoaded() {
		return loadGame;
	}

	
	/**
	 * The listener interface for receiving loadGame events.
	 * The class that is interested in processing a loadGame
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addLoadGameListener<code> method. When
	 * the loadGame event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see LoadGameEvent
	 */
	private class LoadGameListener implements ActionListener
	{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			loadGame = true;

			String name = pathInputField.getText();
			name.concat(".dat");

			path = "./saved_games/" + name;

			setVisible(false);

		}

	}
	

	/**
	 * The listener interface for receiving saveGame events.
	 * The class that is interested in processing a saveGame
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addSaveGameListener<code> method. When
	 * the saveGame event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see SaveGameEvent
	 */
	private class SaveGameListener implements ActionListener
	{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			saveGame = true;			 

			String name = pathInputField.getText();
			name.concat(".dat");

			path = "./saved_games/" + name;

			setVisible(false);

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

			saveGame = false;
			loadGame = false;
			path = null;

			setVisible(false);

		}

	}
	

	/**
	 * Sets the save game.
	 *
	 * @param b the new save game
	 */
	public void setSaveGame(boolean b) {
		saveGame = b;
		
	}

	
	/**
	 * Sets the load game.
	 *
	 * @param b the new load game
	 */
	public void setLoadGame(boolean b) {
		loadGame = b;
		
	}


}
