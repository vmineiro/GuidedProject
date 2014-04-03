package maze.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Insets;

public class MainMenuPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public MainMenuPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {440};
		gridBagLayout.rowHeights = new int[] {75, 59, 59, 30, 59};
		gridBagLayout.columnWeights = new double[]{0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		JLabel panelTitle = new JLabel("Object Oriented Programming Laboratory - \nGuidedProject");
		panelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_panelTitle = new GridBagConstraints();
		gbc_panelTitle.fill = GridBagConstraints.BOTH;
		gbc_panelTitle.insets = new Insets(0, 0, 5, 0);
		gbc_panelTitle.gridx = 0;
		gbc_panelTitle.gridy = 0;
		add(panelTitle, gbc_panelTitle);
		
		JButton btnNewButton = new JButton("New Game");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 1;
		add(btnNewButton, gbc_btnNewButton);
		
		JButton btnLoadGame = new JButton("Load Game");
		GridBagConstraints gbc_btnLoadGame = new GridBagConstraints();
		gbc_btnLoadGame.fill = GridBagConstraints.BOTH;
		gbc_btnLoadGame.insets = new Insets(0, 0, 5, 0);
		gbc_btnLoadGame.gridx = 0;
		gbc_btnLoadGame.gridy = 2;
		add(btnLoadGame, gbc_btnLoadGame);
		
		JButton btnExitGame = new JButton("Exit Game");
		GridBagConstraints gbc_btnExitGame = new GridBagConstraints();
		gbc_btnExitGame.fill = GridBagConstraints.BOTH;
		gbc_btnExitGame.gridx = 0;
		gbc_btnExitGame.gridy = 4;
		add(btnExitGame, gbc_btnExitGame);

	}

}
