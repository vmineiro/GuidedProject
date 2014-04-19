package maze.gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class SaveLoadDialog extends JDialog{

	private boolean saveGame = false;
	private boolean loadGame = false;

	private String path;

	private JPanel textPanel;
	private JPanel pathInputPanel;
	private JPanel buttonsPanel;

	private JLabel pathInputLabel1;
	private JLabel pathInputLabel2;
	private JTextField pathInputField;
	private JButton saveButton;
	private JButton loadButton;
	private JButton cancelButton;

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
		
		//pathInputField.;

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

	public boolean gameSaved() {
		return saveGame;
	}

	public String getFilePath() {
		return path;
	}

	public boolean gameLoaded() {
		return loadGame;
	}

	private class LoadGameListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {

			loadGame = true;

			String name = pathInputField.getText();
			name.concat(".dat");

			path = "./saved_games/" + name;

			setVisible(false);

		}

	}

	private class SaveGameListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {

			saveGame = true;			 

			String name = pathInputField.getText();
			name.concat(".dat");

			path = "./saved_games/" + name;

			setVisible(false);

		}

	}

	private class CancelListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {

			saveGame = false;
			loadGame = false;
			path = null;

			setVisible(false);

		}

	}

	public void setSaveGame(boolean b) {
		saveGame = b;
		
	}

	public void setLoadGame(boolean b) {
		loadGame = b;
		
	}


}
