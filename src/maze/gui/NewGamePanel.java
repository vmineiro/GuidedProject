package maze.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JRadioButton;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JSeparator;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ButtonGroup;

public class NewGamePanel extends JPanel {
	private final ButtonGroup gameTypeButtonGroup = new ButtonGroup();

	/**
	 * Create the panel.
	 */
	public NewGamePanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {432};
		gridBagLayout.rowHeights = new int[] {70, 110, 70, 30};
		gridBagLayout.columnWeights = new double[]{0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		JPanel gameType = new JPanel();
		GridBagConstraints gbc_gameType = new GridBagConstraints();
		gbc_gameType.insets = new Insets(0, 0, 5, 0);
		gbc_gameType.fill = GridBagConstraints.BOTH;
		gbc_gameType.gridx = 0;
		gbc_gameType.gridy = 0;
		add(gameType, gbc_gameType);
		gameType.setLayout(new GridLayout(0, 2, 0, 0));
		
		JRadioButton rdbtnStandardGame = new JRadioButton("Standard Game");
		gameTypeButtonGroup.add(rdbtnStandardGame);
		rdbtnStandardGame.setHorizontalAlignment(SwingConstants.CENTER);
		gameType.add(rdbtnStandardGame);
		
		JRadioButton rdbtnPersonalizedGame = new JRadioButton("Personalized Game");
		gameTypeButtonGroup.add(rdbtnPersonalizedGame);
		rdbtnPersonalizedGame.setHorizontalAlignment(SwingConstants.LEFT);
		gameType.add(rdbtnPersonalizedGame);
		
		JPanel mazeSettings = new JPanel();
		GridBagConstraints gbc_mazeSettings = new GridBagConstraints();
		gbc_mazeSettings.insets = new Insets(0, 0, 5, 0);
		gbc_mazeSettings.fill = GridBagConstraints.BOTH;
		gbc_mazeSettings.gridx = 0;
		gbc_mazeSettings.gridy = 1;
		add(mazeSettings, gbc_mazeSettings);
		mazeSettings.setLayout(new BorderLayout(0, 0));
		
		JLabel lblMazeSettings = new JLabel("Maze Settings");
		lblMazeSettings.setHorizontalAlignment(SwingConstants.CENTER);
		mazeSettings.add(lblMazeSettings, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		mazeSettings.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {67, 240};
		gbl_panel.rowHeights = new int[] {40, 40};
		gbl_panel.columnWeights = new double[]{0.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		JLabel lblBuider = new JLabel("Buider");
		lblBuider.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblBuider = new GridBagConstraints();
		gbc_lblBuider.insets = new Insets(0, 0, 5, 5);
		gbc_lblBuider.gridx = 0;
		gbc_lblBuider.gridy = 0;
		panel.add(lblBuider, gbc_lblBuider);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		panel.add(comboBox, gbc_comboBox);
		
		JLabel lblSize = new JLabel("Size");
		GridBagConstraints gbc_lblSize = new GridBagConstraints();
		gbc_lblSize.insets = new Insets(0, 0, 0, 5);
		gbc_lblSize.gridx = 0;
		gbc_lblSize.gridy = 1;
		panel.add(lblSize, gbc_lblSize);
		
		JSlider slider = new JSlider();
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.fill = GridBagConstraints.HORIZONTAL;
		gbc_slider.gridx = 1;
		gbc_slider.gridy = 1;
		panel.add(slider, gbc_slider);
		
		JPanel dragonSettings = new JPanel();
		GridBagConstraints gbc_dragonSettings = new GridBagConstraints();
		gbc_dragonSettings.insets = new Insets(0, 0, 5, 0);
		gbc_dragonSettings.fill = GridBagConstraints.BOTH;
		gbc_dragonSettings.gridx = 0;
		gbc_dragonSettings.gridy = 2;
		add(dragonSettings, gbc_dragonSettings);
		dragonSettings.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDragonsSettings = new JLabel("Dragons Settings");
		lblDragonsSettings.setHorizontalAlignment(SwingConstants.CENTER);
		dragonSettings.add(lblDragonsSettings, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		dragonSettings.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {100, 80, 100, 120};
		gbl_panel_1.rowHeights = new int[] {40};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gbl_panel_1.rowWeights = new double[]{0.0};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNumberOfDragons = new JLabel("Nr. of Dragons");
		GridBagConstraints gbc_lblNumberOfDragons = new GridBagConstraints();
		gbc_lblNumberOfDragons.insets = new Insets(0, 0, 0, 5);
		gbc_lblNumberOfDragons.gridx = 0;
		gbc_lblNumberOfDragons.gridy = 0;
		panel_1.add(lblNumberOfDragons, gbc_lblNumberOfDragons);
		
		JSpinner spinner_1 = new JSpinner();
		GridBagConstraints gbc_spinner_1 = new GridBagConstraints();
		gbc_spinner_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_1.insets = new Insets(0, 0, 0, 5);
		gbc_spinner_1.gridx = 1;
		gbc_spinner_1.gridy = 0;
		panel_1.add(spinner_1, gbc_spinner_1);
		
		JLabel lblDragonsMode = new JLabel("Dragons Mode");
		GridBagConstraints gbc_lblDragonsMode = new GridBagConstraints();
		gbc_lblDragonsMode.insets = new Insets(0, 0, 0, 5);
		gbc_lblDragonsMode.gridx = 2;
		gbc_lblDragonsMode.gridy = 0;
		panel_1.add(lblDragonsMode, gbc_lblDragonsMode);
		
		JComboBox comboBox_1 = new JComboBox();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 3;
		gbc_comboBox_1.gridy = 0;
		panel_1.add(comboBox_1, gbc_comboBox_1);
		
		JPanel navigationOptions = new JPanel();
		FlowLayout flowLayout = (FlowLayout) navigationOptions.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		GridBagConstraints gbc_navigationOptions = new GridBagConstraints();
		gbc_navigationOptions.insets = new Insets(0, 0, 5, 0);
		gbc_navigationOptions.fill = GridBagConstraints.HORIZONTAL;
		gbc_navigationOptions.gridx = 0;
		gbc_navigationOptions.gridy = 3;
		add(navigationOptions, gbc_navigationOptions);
		
		JButton btnMazeEditor = new JButton("Maze Editor");
		navigationOptions.add(btnMazeEditor);
		
		Component horizontalStrut = Box.createHorizontalStrut(110);
		navigationOptions.add(horizontalStrut);
		
		JButton btnBack = new JButton("Back");
		navigationOptions.add(btnBack);
		
		JButton btnStarGame = new JButton("Star Game");
		navigationOptions.add(btnStarGame);

	}

}
