package maze.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

// TODO: Auto-generated Javadoc
/**
 * The Class MazeEditorPanel.
 */
public class MazeEditorPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public MazeEditorPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel mazePanel = new JPanel();
		add(mazePanel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

	}

}
