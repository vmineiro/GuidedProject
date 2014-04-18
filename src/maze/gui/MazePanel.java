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

	private int keyUp = KeyEvent.VK_UP;
	private int keyDown = KeyEvent.VK_DOWN;
	private int keyLeft = KeyEvent.VK_LEFT;
	private int keyRight = KeyEvent.VK_RIGHT;
	private int keyEagle = KeyEvent.VK_E;

	private BufferedImage wallImg;
	private BufferedImage floorImg;
	private BufferedImage dragonImg;
	private BufferedImage dragonSleepImg;
	private BufferedImage swordImg;
	private BufferedImage heroImg;
	private BufferedImage heroEagleImg;
	private BufferedImage heroArmImg;
	private BufferedImage eagleImg;
	private BufferedImage exitImage;

	public MazePanel()
	{

		launchNewGame();

		addKeyListener(this);

		try {
			wallImg = ImageIO.read(new File("textures/wall01.png"));
			floorImg = ImageIO.read(new File("textures/floor01.jpg"));
			dragonImg = ImageIO.read(new File("textures/dragon80x80.jpeg"));
			swordImg = ImageIO.read(new File("textures/swor02.png"));
			heroImg = ImageIO.read(new File("textures/templar02.jpg"));
			heroEagleImg = ImageIO.read(new File("textures/knight_falcon.png"));
			heroArmImg = ImageIO.read(new File("textures/templar01.jpg"));
			eagleImg = ImageIO.read(new File("textures/eagle.jpg"));
			dragonSleepImg = ImageIO.read(new File("textures/sleeping-dragon.jpg"));
			exitImage = ImageIO.read(new File("textures/DungeonDoor.png"));

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
		//setBuilder(2);
	}

	public void setModeGame(int mode_g)
	{
		this.mode = mode_g;
		//setBuilder(2);
	}

	public void setNDragons(int n_dragons)
	{
		this.nDragons = n_dragons;
		//setBuilder(2);
	}

	public void setBuilder(int n_builder)
	{
		this.builder = n_builder;
	}

	public void setUpKey(int upK)
	{
		this.keyUp = upK;
	}

	public void setDownKey(int downK)
	{
		this.keyDown = downK;
	}

	public void setLeftKey(int leftK)
	{
		this.keyLeft = leftK;
	}

	public void setRightKey(int rightK)
	{
		this.keyRight = rightK;
	}

	public void setEagleKey(int eagleK)
	{
		this.keyEagle = eagleK;
	}

	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);

		int n = game.getMaze().getBoard().length;
		
		int elem_size = getParent().getWidth()/n;
		int ajust = (getParent().getWidth()%n)/2;

		for (int i=0; i<n; i++) 
		{
			for (int j=0; j<n;j++) 
			{
				if(game.getMaze().getBoard()[i][j].equals("XX"))
				{
					g.drawImage(wallImg, j*elem_size+ajust, i*elem_size+ajust, (j*elem_size)+elem_size+ajust, (i*elem_size)+elem_size+ajust, 0, 0, 256, 256, null);
				}
				else if(game.getMaze().getBoard()[i][j].equals("  "))
				{
					g.drawImage(floorImg, j*elem_size+ajust, i*elem_size+ajust, (j*elem_size)+elem_size+ajust, (i*elem_size)+elem_size+ajust, 0, 0, 400, 400, null);
				}
				else if(game.getMaze().getBoard()[i][j].equals("D ") || game.getMaze().getBoard()[i][j].equals("F "))
				{
					g.drawImage(dragonImg, j*elem_size+ajust, i*elem_size+ajust, (j*elem_size)+elem_size+ajust, (i*elem_size)+elem_size+ajust, 0, 0, 74, 74, null);
				}
				else if(game.getMaze().getBoard()[i][j].equals("d "))
				{
					g.drawImage(dragonSleepImg, j*elem_size+ajust, i*elem_size+ajust, (j*elem_size)+elem_size+ajust, (i*elem_size)+elem_size+ajust, 0, 0, 300, 234, null);
				}

				else if(game.getMaze().getBoard()[i][j].equals("E "))
				{
					g.drawImage(swordImg, j*elem_size+ajust, i*elem_size+ajust, (j*elem_size)+elem_size+ajust, (i*elem_size)+elem_size+ajust, 0, 0, 500, 500, null);
				}
				else if(game.getMaze().getBoard()[i][j].equals("Ha"))
				{
					g.drawImage(heroEagleImg, j*elem_size+ajust, i*elem_size+ajust, (j*elem_size)+elem_size+ajust, (i*elem_size)+elem_size+ajust, 0, 0, 116, 128, null);
				}
				else if(game.getMaze().getBoard()[i][j].equals("H "))
				{
					g.drawImage(heroImg, j*elem_size+ajust, i*elem_size+ajust, (j*elem_size)+elem_size+ajust, (i*elem_size)+elem_size+ajust, 0, 0, 300, 343, null);
				}
				else if(game.getMaze().getBoard()[i][j].equals(" a") || game.getMaze().getBoard()[i][j].equals("Xa") || 
						game.getMaze().getBoard()[i][j].equals("Da") || game.getMaze().getBoard()[i][j].equals("da") || 
						game.getMaze().getBoard()[i][j].equals("Fa") || game.getMaze().getBoard()[i][j].equals("Ea"))
				{
					g.drawImage(eagleImg, j*elem_size+ajust, i*elem_size+ajust, (j*elem_size)+elem_size+ajust, (i*elem_size)+elem_size+ajust, 0, 0, 100, 100, null);
				}
				else if(game.getMaze().getBoard()[i][j].equals("Aa") || game.getMaze().getBoard()[i][j].equals("A "))
				{
					g.drawImage(heroArmImg, j*elem_size+ajust, i*elem_size+ajust, (j*elem_size)+elem_size+ajust, (i*elem_size)+elem_size+ajust, 0, 0, 910, 963, null);
				}
				else if(game.getMaze().getBoard()[i][j].equals("SS"))
				{
					g.drawImage(exitImage, j*elem_size+ajust, i*elem_size+ajust, (j*elem_size)+elem_size+ajust, (i*elem_size)+elem_size+ajust, 0, 0, 512, 512, null);
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		int key = e.getKeyCode();

		if(key == keyUp)
		{
			game.movePlayer(maze.logic.Character.Direction.UP);
			repaint();
		}
		else if(key == keyDown)
		{
			game.movePlayer(maze.logic.Character.Direction.DOWN);
			repaint();
		}
		else if (key == keyLeft)
		{
			game.movePlayer(maze.logic.Character.Direction.LEFT);
			repaint();
		}
		else if(key == keyRight)
		{
			game.movePlayer(maze.logic.Character.Direction.RIGHT);
			repaint();
		}
		else if(key == keyEagle)
		{
			game.eagleLaunched();
			repaint();
		}
		else
		{
			repaint();
		}

		game.eagleMove();

		game.dragonsMove();

		if(game.gameOver())
		{

			repaint();
			
			String gameOverMsg;

			if (game.getPlayer().isDead())
			{
				gameOverMsg = "You Lost. New Game?";			
			}
			else
			{
				gameOverMsg = "You Win. New Game?";
			}

			int reply = JOptionPane.showConfirmDialog(getParent(),gameOverMsg,"Game Over",JOptionPane.YES_NO_OPTION);

			if(reply == JOptionPane.YES_OPTION)
			{
				launchNewGame();
			}
			else if(reply == JOptionPane.NO_OPTION || reply == JOptionPane.CLOSED_OPTION)
			{
				System.exit(0);
			}
			
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	public Game getGame(){
		return game;
	}

	public void loadGame(String path) throws ClassNotFoundException, IOException {
		game.loadGame(path);
	}

}


