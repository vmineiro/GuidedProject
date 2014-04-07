package maze.cli;

import maze.logic.*;
import maze.logic.Character.Direction;

import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class CLInterface.
 */
public class CLInterface {
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		boolean validOption = false;
		System.out.println("MAZE - GAME");
		System.out.println();
		System.out.println("1 - Command Line Interface");
		System.out.println("2 - Graphic User Interface");
		System.out.println();

		Scanner sc0 = new Scanner(System.in);
		
		while(!validOption)
		{
			System.out.println("Select your option:");
			
			int menuOption = sc0.nextInt();

			if(menuOption == 1)
			{
				startGame();
				validOption = true;
			}
			else if(menuOption==2)
			{
				//maze.gui.StarGameWindow.startGame();
				validOption = true;
			}
		}
		
	}


	/**
	 * Main menu.
	 *
	 * @param game the game
	 */
	public static void mainMenu(Game game)
	{
		boolean validOption = false;
		System.out.println();
		System.out.println("1 - Standard Maze");
		System.out.println("2 - Random generated Maze");
		System.out.println();

		Scanner sc0 = new Scanner(System.in);

		
		while(!validOption)
		{
			System.out.println("Select your option:");
			
			int menuOption = sc0.nextInt();

			if(menuOption==1)
			{
				game.initGame(0,1,1);
				validOption = true;
			}
			else if(menuOption==2)
			{
				randomOption(game);
				validOption = true;
			}
		}

	}


	/**
	 * Random option.
	 *
	 * @param game the game
	 */
	public static void randomOption(Game game)
	{

		int mazeSize;
		Scanner sc1 = new Scanner(System.in);
		boolean validOption = false;

		while (!validOption){

			System.out.println();
			System.out.println("Enter N for NxN Maze [10 - 30]:");
			mazeSize = sc1.nextInt();

			if (mazeSize < 10 || mazeSize > 30) {
				System.out.println("Invalid Size.");
			} else {

				int mode;

				System.out.println();
				System.out.println("Dragon Mode:");
				System.out.println("1 - Static");
				System.out.println("2 - Dinamic");
				System.out.println("3 - Mixed");
				System.out.println();
				System.out.println("Select your option:");

				while(!validOption)	{

					mode = sc1.nextInt();

					if (mode > 0 && mode < 4){

						while(!validOption){
							
							System.out.println();
							System.out.println("Number of Dragons [1-15]:");

							int nD = sc1.nextInt();

							if (nD > 0 && nD < 16){
								validOption = true;
								game.initGame(mazeSize, mode, nD);
							} else {
								System.out.println("Invalid number of Dragons.");
							}

						}

					} else {
						System.out.println("Invalid Mode. Choose another mode.");
					}
				}
			}
		}
		
		//sc1.close();
	}


	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void startGame() {
		
		Game game = new Game();

		mainMenu(game);

		game.updatePositions();
		printMaze(game);

		boolean gameEnd = false;

		while(!gameEnd){
			
			gameEnd = playerMove(game);

			if (!gameEnd ) {

				game.eagleMove();

				game.dragonsMove();

				game.updatePositions();
				printMaze(game);
				gameEnd = game.gameOver();

			} else
				System.out.println("\nExit");
		}
	}


	/**
	 * Read the player input and when it is a valid input update the player position and clear the previous position in the maze.
	 *
	 * @param game the game
	 * @return true, if successful
	 */
	public static boolean playerMove(Game game) {

		System.out.println("\nMove (w-up; a-left; s-down; d-right; e- launch eagle; f- don't move; q-quit):");

		Scanner moveInput = new Scanner(System.in);
		String move = moveInput.nextLine();
		
		
		switch (move) {
		case "a":
			game.movePlayer(Direction.LEFT);
			break;
		case "s":
			game.movePlayer(Direction.DOWN);
			break;
		case "d":
			game.movePlayer(Direction.RIGHT);
			break;
		case "w":
			game.movePlayer(Direction.UP);
			break;
		case "e":
			game.eagleLaunched();
			break;
		case "q":
			return true;
		default:
			break;
		}	
		return false;

	}
	
	/**
	 * Prints the maze.
	 * @param game 
	 */
	public static void printMaze(Game game) {
		
		String [][] maze =game.getMaze().getMaze();
		
		System.out.println();
		
		int n = maze.length;
		for (int i=0; i<n; i++) {
			for (int j=0; j<n;j++) {
				System.out.print(maze[i][j]);
			}
			System.out.println();
		}
	}

}
