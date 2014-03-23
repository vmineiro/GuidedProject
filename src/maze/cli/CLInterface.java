package maze.cli;

import maze.logic.*;

import java.util.Scanner;


public class CLInterface {

	public static void mainMenu(Game game)
	{
		boolean validOption = false;
		System.out.println("MAZE - GAME");
		System.out.println();
		System.out.println("1 - Standard Maze");
		System.out.println("2 - Random generated Maze");

		while(!validOption)
		{
			System.out.println("Select your option:");
			Scanner sc0 = new Scanner(System.in);
			int menuOption = sc0.nextInt();

			if(menuOption==1)
			{
				game.initGame(0);
				validOption = true;
			}
			else if(menuOption==2)
			{
				randomOption(game);
				validOption = true;
			}
		}

	}

	public static void standardOption(Game game)
	{
		game.initGame(0);
	}

	public static void randomOption(Game game)
	{
		int mazeSize;
		System.out.println("Enter N for NxN Maze:");
		Scanner sc1 = new Scanner(System.in);
		mazeSize = sc1.nextInt();

		game.initGame(mazeSize);
	}

	public static void main(String[] args) 
	{
		Game game = new Game();

		mainMenu(game);
		
		game.updatePositions();
		game.getMaze().printMaze();
		
		boolean gameEnd = false;

		while(!gameEnd){
			gameEnd = game.playerMove();
			game.checkKill();
			game.updatePosition(game.getPlayer());
			if (!gameEnd ) {
				if (game.getDragon().getMode() != Dragon.Mode.STATIC || !game.getDragon().isAsleep() || !game.getDragon().isDead()) {
					game.dragonMove();
					game.checkKill();
					game.refreshMaze();
				}
				game.getMaze().printMaze();
				gameEnd = game.gameOver();
			} else
				System.out.println("\nExit");
		}
	}
}