package maze.gui;

import maze.logic.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MazePanel extends JPanel implements KeyListener
{
	private Game game;
	private int mazeSize = 0;
	private int mode = 1;
	private int nDragons = 1;
	private int builder = 0;	
	
	private BufferedImage wallImg;
	private BufferedImage floorImg;
	private BufferedImage dragonImg;
	private BufferedImage swordImg;
	private BufferedImage heroImg;
	private BufferedImage heroEagleImg;
	private BufferedImage heroArmImg;
	private BufferedImage eagleImg;
	
	public MazePanel()
	{
		
		launchNewGame();
		
		addKeyListener(this);
		
		try {
			wallImg = ImageIO.read(new File("textures/Grass_1.png"));
			floorImg = ImageIO.read(new File("textures/floor01.jpg"));
			dragonImg = ImageIO.read(new File("textures/dragon80x80.jpeg"));
			swordImg = ImageIO.read(new File("textures/swor02.png"));
			heroImg = ImageIO.read(new File("textures/templar02.jpg"));
			heroEagleImg = ImageIO.read(new File("textures/knight_falcon.png"));
			heroArmImg = ImageIO.read(new File("textures/templar01.jpg"));
			eagleImg = ImageIO.read(new File("textures/eagle.jpg"));
			
		} catch (IOException e) {}
		
	}
	
	public void launchNewGame()
	{
		this.game = new Game();
		game.initGame(mazeSize, mode, nDragons, builder);
		game.updatePositions();
	}
	
	public void setMazeSize(int size)
	{
		this.mazeSize = size;
		setBuilder(2);
	}
	
	public void setModeGame(int mode_g)
	{
		this.mode = mode_g;
		setBuilder(2);
	}
	
	public void setNDragons(int n_dragons)
	{
		this.nDragons = n_dragons;
		setBuilder(2);
	}

	public void setBuilder(int n_builder)
	{
		this.builder = n_builder;
	}
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		int n = game.getMaze().getBoard().length;
		int elem_size = 50;
		
		for (int i=0; i<n; i++) 
		{
			for (int j=0; j<n;j++) 
			{
				if(game.getMaze().getBoard()[i][j].equals("XX"))
				{
					g.drawImage(wallImg, j*elem_size, i*elem_size, (j*elem_size)+elem_size, (i*elem_size)+elem_size, 0, 0, 256, 256, null);
				}
				else if(game.getMaze().getBoard()[i][j].equals("  ") || game.getMaze().getBoard()[i][j].equals("SS"))
				{
					g.drawImage(floorImg, j*elem_size, i*elem_size, (j*elem_size)+elem_size, (i*elem_size)+elem_size, 0, 0, 400, 400, null);
				}
				else if(game.getMaze().getBoard()[i][j].equals("D ") || game.getMaze().getBoard()[i][j].equals("d ") || 
						game.getMaze().getBoard()[i][j].equals("F "))
				{
					g.drawImage(dragonImg, j*elem_size, i*elem_size, (j*elem_size)+elem_size, (i*elem_size)+elem_size, 0, 0, 74, 74, null);
				}
				else if(game.getMaze().getBoard()[i][j].equals("E "))
				{
					g.drawImage(swordImg, j*elem_size, i*elem_size, (j*elem_size)+elem_size, (i*elem_size)+elem_size, 0, 0, 500, 500, null);
				}
				else if(game.getMaze().getBoard()[i][j].equals("Ha"))
				{
					g.drawImage(heroEagleImg, j*elem_size, i*elem_size, (j*elem_size)+elem_size, (i*elem_size)+elem_size, 0, 0, 116, 128, null);
				}
				else if(game.getMaze().getBoard()[i][j].equals("H "))
				{
					g.drawImage(heroImg, j*elem_size, i*elem_size, (j*elem_size)+elem_size, (i*elem_size)+elem_size, 0, 0, 300, 343, null);
				}
				else if(game.getMaze().getBoard()[i][j].equals(" a") || game.getMaze().getBoard()[i][j].equals("Xa") || 
						game.getMaze().getBoard()[i][j].equals("Da") || game.getMaze().getBoard()[i][j].equals("da") || 
						game.getMaze().getBoard()[i][j].equals("Fa") || game.getMaze().getBoard()[i][j].equals("Ea"))
				{
					g.drawImage(eagleImg, j*elem_size, i*elem_size, (j*elem_size)+elem_size, (i*elem_size)+elem_size, 0, 0, 100, 100, null);
				}
				else if(game.getMaze().getBoard()[i][j].equals("Aa") || game.getMaze().getBoard()[i][j].equals("A "))
				{
					g.drawImage(heroArmImg, j*elem_size, i*elem_size, (j*elem_size)+elem_size, (i*elem_size)+elem_size, 0, 0, 910, 963, null);
				}
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) 
	{
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_UP)
		{
			game.movePlayer(maze.logic.Character.Direction.UP);
		}
		else if(key == KeyEvent.VK_DOWN)
		{
			game.movePlayer(maze.logic.Character.Direction.DOWN);
		}
		else if (key == KeyEvent.VK_LEFT)
		{
			game.movePlayer(maze.logic.Character.Direction.LEFT);
		}
		else if(key == KeyEvent.VK_RIGHT)
		{
			game.movePlayer(maze.logic.Character.Direction.RIGHT);
		}
		else if(key == KeyEvent.VK_E)
		{
			game.eagleLaunched();
		}
		
		game.eagleMove();

		game.dragonsMove();
		
		if(game.gameOver())
		{
			System.exit(0);
		}
		
		repaint();
	}
	
	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
	
}
