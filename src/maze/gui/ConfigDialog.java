package maze.gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JSlider;
import javax.swing.JRadioButton;


/**
 * The Class ConfigDialog.
 */
public class ConfigDialog extends JDialog
{
	
	/** The maze size panel. */
	private JPanel mazeSizePanel;
	
	/** The n dragons panel. */
	private JPanel nDragonsPanel;
	
	/** The mode panel. */
	private JPanel modePanel;
	
	/** The keys label panel. */
	private JPanel keysLabelPanel;
	
	/** The keys panel. */
	private JPanel keysPanel;
	
	/** The buttons panel. */
	private JPanel buttonsPanel;
	
	/** The builder panel. */
	private JPanel builderPanel;
	
	/** The yes button. */
	private JButton yesButton;
    
    /** The no button. */
    private JButton noButton;
    
    /** The size label. */
    private JLabel sizeLabel;
    
    /** The size slider. */
    private JSlider sizeSlider;
    
    /** The dragon label. */
    private JLabel dragonLabel;
    
    /** The dragon slider. */
    private JSlider dragonSlider;
    
    /** The mode label. */
    private JLabel modeLabel;
    
    /** The static radio button. */
    private JRadioButton staticRadioButton;
    
    /** The dynamic radio button. */
    private JRadioButton dynamicRadioButton;
    
    /** The btn up. */
    private JButton btnUp;
    
    /** The key label. */
    private JLabel keyLabel;
    
    /** The btn down. */
    private JButton btnDown;
    
    /** The btn left. */
    private JButton btnLeft;
    
    /** The btn right. */
    private JButton btnRight;
    
    /** The btn eagle. */
    private JButton btnEagle;
    
    /** The builder comb box. */
    private JComboBox<String> builderCombBox;
    
    /** The builder label. */
    private JLabel builderLabel;
    
    /** The save config. */
    private boolean saveConfig = false;
    
    /** The sel maze size. */
    private int selMazeSize;
    
    /** The sel n dragons. */
    private int selNDragons;
    
    /** The sel mode. */
    private int selMode;
    
    /** The sel builder. */
    private int selBuilder;
    
    /** The sel up key. */
    private int selUpKey = KeyEvent.VK_UP;
    
    /** The sel down key. */
    private int selDownKey = KeyEvent.VK_DOWN;
    
    /** The sel left key. */
    private int selLeftKey = KeyEvent.VK_LEFT;
    
    /** The sel right key. */
    private int selRightKey = KeyEvent.VK_RIGHT;
    
    /** The sel eagle key. */
    private int selEagleKey = KeyEvent.VK_E;
	
    
	/**
	 * Instantiates a new config dialog.
	 *
	 * @param frame the frame
	 * @param modal the modal
	 * @param myMessage the my message
	 */
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
	
	
	/**
	 * Creates the widgets.
	 */
	private void createWidgets()
	{
		// Maze Size Panel
		mazeSizePanel = new JPanel();
		mazeSizePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		sizeLabel = new JLabel("Enter maze size (N) for a NxN Maze:");
		
		sizeSlider = new JSlider();
		sizeSlider.setSnapToTicks(true);
		sizeSlider.setPaintTicks(true);
		sizeSlider.setMinorTickSpacing(1);
		sizeSlider.setMajorTickSpacing(5);
		sizeSlider.setValue(20);
		sizeSlider.setMaximum(35);
		sizeSlider.setMinimum(10);
		sizeSlider.setPaintLabels(true);	
		
		
		// Dragons Number Panel
		nDragonsPanel = new JPanel();
		nDragonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		dragonLabel = new JLabel("Number of Dragons:");
		
		dragonSlider = new JSlider();
		dragonSlider.setSnapToTicks(true);
		dragonSlider.setMinimum(1);
		dragonSlider.setMaximum(16);
		dragonSlider.setValue(7);
		dragonSlider.setMinorTickSpacing(1);
		dragonSlider.setMajorTickSpacing(5);
		dragonSlider.setPaintTicks(true);
		dragonSlider.setPaintLabels(true);
		
		// Mode Panel
		modePanel = new JPanel();
		modePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		modeLabel = new JLabel("Dragon Mode (Static + Dynamic = Mixed):");
		
		staticRadioButton = new JRadioButton("Static");
		staticRadioButton.setSelected(false);
		dynamicRadioButton = new JRadioButton("Dynamic");
		dynamicRadioButton.setSelected(true);

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
			
		// Builder Panel
		builderPanel = new JPanel();
		
		String builderName[] = {"One path to the exit","Multiple paths to the exit"};		
		builderCombBox = new JComboBox<>(builderName);
		builderCombBox.setSelectedIndex(0);
		builderCombBox.addActionListener(new BuilderComboBoxListener());
		
		builderLabel = new JLabel("Maze Builder:");
		
	}

	
	/**
	 * Adds the widgets.
	 *
	 * @param cont the cont
	 */
	private void addWidgets(Container cont)
	{
		
		// Builder Panel
		builderPanel.add(builderLabel);
		builderPanel.add(builderCombBox);
		cont.add(builderPanel);	
		
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

	
	/**
	 * Gets the config option.
	 *
	 * @return the config option
	 */
	public boolean getConfigOption()
	{
		return saveConfig;
	}

	
	/**
	 * Gets the sel maze size.
	 *
	 * @return the sel maze size
	 */
	public int getSelMazeSize()
	{
		return selMazeSize;
	}
	
	
	/**
	 * Gets the sel n dragons.
	 *
	 * @return the sel n dragons
	 */
	public int getSelNDragons()
	{
		return selNDragons;
	}
	
	
	/**
	 * Gets the sel mode.
	 *
	 * @return the sel mode
	 */
	public int getSelMode()
	{
		return selMode;
	}
	
	
	/**
	 * Gets the sel buider.
	 *
	 * @return the sel buider
	 */
	public int getSelBuider()
	{
		return selBuilder;
	}
	
	
	/**
	 * Gets the sel up key.
	 *
	 * @return the sel up key
	 */
	public int getSelUpKey()
	{
		return selUpKey;
	}
	
	
	/**
	 * Gets the sel down key.
	 *
	 * @return the sel down key
	 */
	public int getSelDownKey()
	{
		return selDownKey;
	}
	
	
	/**
	 * Gets the sel left key.
	 *
	 * @return the sel left key
	 */
	public int getSelLeftKey()
	{
		return selLeftKey;
	}
	
	
	/**
	 * Gets the sel right key.
	 *
	 * @return the sel right key
	 */
	public int getSelRightKey()
	{
		return selRightKey;
	}
	
	
	/**
	 * Gets the sel eagle key.
	 *
	 * @return the sel eagle key
	 */
	public int getSelEagleKey()
	{
		return selEagleKey;
	}
	
	
	/**
	 * The listener interface for receiving saveConfig events.
	 * The class that is interested in processing a saveConfig
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addsaveConfigListener<code> method. When
	 * the saveConfig event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see saveConfigEvent
	 */
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

	
	/**
	 * The listener interface for receiving disConfig events.
	 * The class that is interested in processing a disConfig
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>adddisConfigListener<code> method. When
	 * the disConfig event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see disConfigEvent
	 */
	private class disConfigListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			saveConfig = false;
			setVisible(false);
		}	
	}
	
	
	/**
	 * The listener interface for receiving builderComboBox events.
	 * The class that is interested in processing a builderComboBox
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addBuilderComboBoxListener<code> method. When
	 * the builderComboBox event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see BuilderComboBoxEvent
	 */
	private class BuilderComboBoxListener implements ActionListener
	{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			selBuilder = builderCombBox.getSelectedIndex() +1;
	    
		}	
	}
	
	
	/**
	 * The listener interface for receiving upConfig events.
	 * The class that is interested in processing a upConfig
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addUpConfigListener<code> method. When
	 * the upConfig event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see UpConfigEvent
	 */
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
	
	
	/**
	 * The listener interface for receiving downConfig events.
	 * The class that is interested in processing a downConfig
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addDownConfigListener<code> method. When
	 * the downConfig event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see DownConfigEvent
	 */
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
	
	
	/**
	 * The listener interface for receiving leftConfig events.
	 * The class that is interested in processing a leftConfig
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addLeftConfigListener<code> method. When
	 * the leftConfig event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see LeftConfigEvent
	 */
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
	
	
	/**
	 * The listener interface for receiving rightConfig events.
	 * The class that is interested in processing a rightConfig
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addRightConfigListener<code> method. When
	 * the rightConfig event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see RightConfigEvent
	 */
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
	
	
	/**
	 * The listener interface for receiving eagleConfig events.
	 * The class that is interested in processing a eagleConfig
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addEagleConfigListener<code> method. When
	 * the eagleConfig event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see EagleConfigEvent
	 */
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
