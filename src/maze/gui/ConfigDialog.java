package maze.gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JSpinner;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JSlider;
import javax.swing.JRadioButton;

public class ConfigDialog extends JDialog
{
	private JPanel mazeSizePanel;
	private JPanel nDragonsPanel;
	private JPanel modePanel;
	private JPanel keysLabelPanel;
	private JPanel keysPanel;
	private JPanel buttonsPanel;
	
	private JButton yesButton;
    private JButton noButton;
    private JLabel sizeLabel;
    private JSlider sizeSlider;
    private JLabel dragonLabel;
    private JSlider dragonSlider;
    private JLabel modeLabel;
    private JRadioButton staticRadioButton;
    private JRadioButton dynamicRadioButton;
    private JRadioButton mixedRadioButton;
    private JButton btnUp;
    private JLabel keyLabel;
    private JButton btnDown;
    private JButton btnLeft;
    private JButton btnRight;
    private JButton btnEagle;
	
	public ConfigDialog(JFrame frame, boolean modal, String myMessage)
	{
		super(frame,modal);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
	
		createWidgets();
		addWidgets(getContentPane());

		pack();
		setLocationRelativeTo(frame);
		setVisible(true);
	}
	
	private void createWidgets()
	{
		// Maze Size Panel
		mazeSizePanel = new JPanel();
		mazeSizePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		sizeLabel = new JLabel("Enter maze size (N) for a NxN Maze [10 - 30]:");
		
		sizeSlider = new JSlider();
		sizeSlider.setPaintLabels(true);
		sizeSlider.setPaintTicks(true);
		sizeSlider.setMinorTickSpacing(5);
		sizeSlider.setValue(20);
		sizeSlider.setMaximum(30);
		sizeSlider.setMinimum(10);
		
		// Dragons Number Panel
		nDragonsPanel = new JPanel();
		nDragonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		dragonLabel = new JLabel("Number of Dragons [1-15]:");
		
		dragonSlider = new JSlider();
		dragonSlider.setMinimum(1);
		dragonSlider.setMaximum(15);
		dragonSlider.setValue(7);
		dragonSlider.setMinorTickSpacing(1);
		dragonSlider.setPaintTicks(true);
		dragonSlider.setPaintLabels(true);
		
		// Mode Panel
		modePanel = new JPanel();
		modePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		modeLabel = new JLabel("Dragon Mode:");
		
		staticRadioButton = new JRadioButton("Static");
		dynamicRadioButton = new JRadioButton("Dynamic");
		mixedRadioButton = new JRadioButton("Mixed");		

		// Keys Panel
		keysLabelPanel = new JPanel();
		keysLabelPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		keyLabel = new JLabel("Reassign Keyboard Keys:");
			
		keysPanel = new JPanel();
		keysPanel.setLayout(new BorderLayout(0, 0));
		
		btnUp = new JButton("Up");
		btnDown = new JButton("Down");
		btnLeft = new JButton("Left");
		btnRight = new JButton("Right");
		btnEagle = new JButton("Eagle");
		
		// Buttons Panel
		buttonsPanel = new JPanel();
		
		// Button - Save Configuration
		yesButton = new JButton("Save Configuration");
		yesButton.addActionListener(new saveConfigListener());

		// Button - Discard Configuration
		noButton = new JButton("Discard Configuration");	
		noButton.addActionListener(new disConfigListener());
		
	}
	
	private void addWidgets(Container cont)
	{
		// Maze Size Panel
		mazeSizePanel.add(sizeLabel);
		mazeSizePanel.add(sizeSlider);
		cont.add(mazeSizePanel);
		
		// Dragons Number Panel
		nDragonsPanel.add(dragonLabel);
		nDragonsPanel.add(dragonSlider);
		cont.add(nDragonsPanel);
		
		// Mode Panel
		modePanel.add(modeLabel);
		modePanel.add(staticRadioButton);
		modePanel.add(dynamicRadioButton);
		modePanel.add(mixedRadioButton);
		cont.add(modePanel);
		
		// Keys Panel
		keysLabelPanel.add(keyLabel);
		keysPanel.add(btnUp, BorderLayout.NORTH);
		keysPanel.add(btnDown, BorderLayout.SOUTH);
		keysPanel.add(btnLeft, BorderLayout.WEST);
		keysPanel.add(btnRight, BorderLayout.EAST);
		keysPanel.add(btnEagle);		
		cont.add(keysLabelPanel);
		cont.add(keysPanel);
		
		// Buttons Panel
		buttonsPanel.add(yesButton);
		buttonsPanel.add(noButton);
		cont.add(buttonsPanel);		
	}
	
	private class saveConfigListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}	
	}
	
	private class disConfigListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}	
	}
	
	
}
