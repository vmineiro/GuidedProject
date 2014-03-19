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
				standardOption(game);
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
		
	}
	
	public static void randomOption(Game game)
	{
		
	}

	public static void main(String[] args) 
	{
		Game game = new Game();
		
		mainMenu(game);
	
	
		
		int mazeSize;
		System.out.println("Enter N for NxN Maze:");
		Scanner sc1 = new Scanner(System.in);
		mazeSize = sc1.nextInt();

		game.initGame(mazeSize);
		
		
		

		game.getMaze().printBoard();

		while(game.checkRunGame())
		{
			String keyIn;
			System.out.println("UP -> w | DOWN -> s | LEFT -> a | RIGHT -> d | QUIT GAME-> q");
			System.out.println("Next Move:");
			Scanner sc2 = new Scanner(System.in);
			keyIn = sc2.nextLine();
			
			switch(keyIn)
			{
				case "w":
					game.moveHeroUp();
					break;
				case "s":
					game.moveHeroDown();
					break;
				case "a":
					game.moveHeroLeft();
					break;
				case "d":
					game.moveHeroRight();
					break;
				case "q":
					game.setEndGame();
					break;
				default:
					break;
			}
			
			if(keyIn.equals("w") || keyIn.equals("s") || keyIn.equals("a") || keyIn.equals("d"))
			{
				game.updateDragon();
				game.updateGame();
				game.getMaze().printBoard();
			}
			
		}

		System.out.println("Game Over");

	}
}
