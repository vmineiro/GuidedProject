package maze.gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;

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
    private JButton btnUp;
    private JLabel keyLabel;
    private JButton btnDown;
    private JButton btnLeft;
    private JButton btnRight;
    private JButton btnEagle;
    
    private boolean saveConfig = false;
    private int selMazeSize;
    private int selNDragons;
    private int selMode;
    
    private int selUpKey = KeyEvent.VK_UP;
    private int selDownKey = KeyEvent.VK_DOWN;
    private int selLeftKey = KeyEvent.VK_LEFT;
    private int selRightKey = KeyEvent.VK_RIGHT;
    private int selEagleKey = KeyEvent.VK_E;
	
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
		sizeSlider.setSnapToTicks(true);
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
		dragonSlider.setSnapToTicks(true);
		dragonSlider.setMinimum(1);
		dragonSlider.setMaximum(15);
		dragonSlider.setValue(7);
		dragonSlider.setMinorTickSpacing(1);
		dragonSlider.setPaintTicks(true);
		dragonSlider.setPaintLabels(true);
		
		// Mode Panel
		modePanel = new JPanel();
		modePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		modeLabel = new JLabel("Dragon Mode (Static + Dynamic = Mixed):");
		
		staticRadioButton = new JRadioButton("Static");
		staticRadioButton.setSelected(true);
		dynamicRadioButton = new JRadioButton("Dynamic");

		// Keys Panel
		keysLabelPanel = new JPanel();
		keysLabelPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		keyLabel = new JLabel("Reassign Keyboard Keys - Click Button and Press Keyboard Key to Assign");
			
		keysPanel = new JPanel();
		keysPanel.setLayout(new BorderLayout(0, 0));
		
		btnUp = new JButton("Up");
		btnUp.addKeyListener(new UpConfigListener());
		
		btnDown = new JButton("Down");
		btnDown.addKeyListener(new DownConfigListener());
		
		btnLeft = new JButton("Left");
		btnLeft.addKeyListener(new LeftConfigListener());
		
		btnRight = new JButton("Right");
		btnRight.addKeyListener(new RightConfigListener());
		
		btnEagle = new JButton("Eagle");
		btnEagle.addKeyListener(new EagleConfigListener());
		
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
	
	public boolean getConfigOption()
	{
		return saveConfig;
	}
	
	public int getSelMazeSize()
	{
		return selMazeSize;
	}
	
	public int getSelNDragons()
	{
		return selNDragons;
	}
	
	public int getSelMode()
	{
		return selMode;
	}
	
	public int getSelUpKey()
	{
		return selUpKey;
	}
	
	public int getSelDownKey()
	{
		return selDownKey;
	}
	
	public int getSelLeftKey()
	{
		return selLeftKey;
	}
	
	public int getSelRightKey()
	{
		return selRightKey;
	}
	
	public int getSelEagleKey()
	{
		return selEagleKey;
	}
	
	private class saveConfigListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			saveConfig = true;
			
			selMazeSize = sizeSlider.getValue();
			selNDragons = dragonSlider.getValue();
			
			if(staticRadioButton.isSelected() && dynamicRadioButton.isSelected())
			{
				selMode = 3;
			}
			else if(!staticRadioButton.isSelected())
			{
				selMode = 2;
			}
			else
			{
				selMode = 1;
			}
			
			setVisible(false);
		}	
	}
	
	private class disConfigListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			saveConfig = false;
			setVisible(false);
		}	
	}
	
	private class UpConfigListener implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent e) 
		{
			selUpKey = e.getKeyCode();
		}

		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyReleased(KeyEvent e) {}
		
	}
	
	private class DownConfigListener implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent e) 
		{
			selDownKey = e.getKeyCode();
		}

		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyReleased(KeyEvent e) {}
		
	}
	
	private class LeftConfigListener implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent e) 
		{
			selLeftKey = e.getKeyCode();
		}

		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyReleased(KeyEvent e) {}
		
	}
	
	private class RightConfigListener implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent e) 
		{
			selRightKey = e.getKeyCode();
		}

		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyReleased(KeyEvent e) {}
		
	}
	
	private class EagleConfigListener implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent e) 
		{
			selEagleKey = e.getKeyCode();
		}

		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyReleased(KeyEvent e) {}
		
	}
}
