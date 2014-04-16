package maze.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MazePanel extends JPanel implements MouseListener, MouseMotionListener
{
	private int x1=0, y1=0, x2=0, y2=0;
	
	public MazePanel()
	{
		addMouseListener(this);
		addMouseMotionListener(this); 
	}
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g); 
		g.setColor(Color.BLUE);
		g.fillOval(x1, y1, x2 - x1 + 1, y2 - y1 + 1);
	}
	
	public void mousePressed(MouseEvent e) 
	{
		x2 = x1 = e.getX();
		y2 = y1 = e.getY();
		repaint();
	}
	
	public void mouseDragged(MouseEvent e) 
	{ 
		x2 = e.getX();
		y2 = e.getY();
		repaint();
	}
	
	public void mouseReleased(MouseEvent e) {}
	public void mouseMoved(MouseEvent arg0) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
