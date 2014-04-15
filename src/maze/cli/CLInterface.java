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

		while(!validOption)	{	
			try {
				System.out.println("Select your option:");

				int menuOption = sc0.nextInt();

				if(menuOption == 1)
				{
					try {
						startGame();
					} catch (Exception e) {
						System.out.println("Game engine error.");
					}			
					validOption = true;
				}
				else if(menuOption==2)
				{
					//maze.gui.StarGameWindow.startGame();
					validOption = true;
				}
				else throw new Exception();
			} catch (Exception e){
				System.out.println("Invalid option.");
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
		System.out.println("3 - Load Game");
		System.out.println();

		Scanner sc0 = new Scanner(System.in);


		while(!validOption)
		{
			try {
				System.out.println("Select your option:");

				int menuOption = sc0.nextInt();

				if(menuOption==1)
				{
					game.initGame(0,1,1,0);
					validOption = true;
				}
				else if(menuOption==2)
				{
					mazeSettings(game);
					validOption = true;
				}
				else if (menuOption == 3) {
					loadGame(game);
					validOption = true;
				}
				else throw new Exception(); 
			} catch (Exception e){
				System.out.println("Invalid option.");
			}

		}

	}


	private static void loadGame(Game game) {
		// TODO Auto-generated method stub

	}


	private static void saveGame(Game game) {
		// TODO Auto-generated method stub

	}


	private static void mazeSettings(Game game) {

		int mazeBuilder = selectMazeBuilder();
		int mazeSize = selectMazeSize();
		int numDragons = selectNumberDragons();
		int dragonsMode = selectDragonsMode();

		game.initGame(mazeSize, dragonsMode, numDragons, mazeBuilder);

	}


	private static int selectMazeBuilder() {
		boolean validOption = false;
		int builder;
		Scanner sc0 = new Scanner(System.in);

		System.out.println();
		System.out.println("1 - Only One Path to Exit (Only odd sizes)");
		System.out.println("2 - Multiple Paths to Exit");
		System.out.println();

		while(!validOption){

			try {
				System.out.println("Select your option:");

				builder = sc0.nextInt();

				if ((builder == 1) || (builder == 2)){
					return builder;
				} else {
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println("Invalid Option.");
			}

		}
		return 1;
	}


	private static int selectMazeSize() {
		int mazeSize;
		Scanner sc1 = new Scanner(System.in);
		boolean validOption = false;

		System.out.println();

		while (!validOption){
			try {
				System.out.println("Enter maze size (N) for a NxN Maze [10 - 30]:");

				mazeSize = sc1.nextInt();

				if (mazeSize > 9 || mazeSize < 31) {
					return mazeSize;
				} else {
					throw new Exception();
				}
			} catch (Exception e){
				System.out.println("Invalid Size.");
			}

		}

		return 10;
	}



	private static int selectNumberDragons() {

		int numDragons;
		Scanner sc1 = new Scanner(System.in);
		boolean validOption = false;

		System.out.println();

		while(!validOption){

			try {
				System.out.println("Number of Dragons [1-15]:");
				numDragons = sc1.nextInt();

				if ((numDragons > 0) && (numDragons < 16)){
					return numDragons;
				} else {
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println("Invalid number of Dragons.");
			}


		}

		return 1;
	}


	private static int selectDragonsMode() {
		int mode;
		Scanner sc1 = new Scanner(System.in);
		boolean validOption = false;

		System.out.println();
		System.out.println("Dragon Mode:");
		System.out.println("1 - Static");
		System.out.println("2 - Dinamic");
		System.out.println("3 - Mixed");
		System.out.println();

		while(!validOption)	{
			try {
				System.out.println("Select your option:");
				mode = sc1.nextInt();

				if ((mode > 0) && (mode < 4)){
					return mode;
				} else {
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println("Invalid Mode.");
			}

		}

		return 1;	
	}


	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void startGame() throws Exception{

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

		System.out.println("\nMove (w-up; a-left; s-down; d-right; e- launch eagle; f- don't move; m-menu):");

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
		case "m":
			return gameMenu(game);
		default:
			break;
		}	
		return false;

	}



	private static boolean gameMenu(Game game) {

		Scanner sc1 = new Scanner(System.in);
		boolean validOption = false;
		int opt;

		System.out.println("Menu");
		System.out.println();
		System.out.println("1 - Resume");
		System.out.println("2 - New Game");
		System.out.println("3 - Save Game");
		System.out.println("4 - Load Game");
		System.out.println("5 - Exit Game");	
		System.out.println();

		while(!validOption)	{
			try {
				System.out.println("Select your option:");
				opt = sc1.nextInt();

				if (opt == 1){
					return false;
				} else if (opt == 2){
					newGame(game);
					validOption = true;
				} else if (opt == 3){
					saveGame(game);
					validOption = true;
				} else if (opt == 4){
					loadGame(game);
					validOption = true;
				} else if (opt == 5){
					return true;
				} else {
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println("Invalid Option.");
			}

		}

		return false;
	}


	private static void newGame(Game game) {

		Game tempGame = new Game();

		mainMenu(tempGame);

		game.setPlayer(tempGame.getPlayer());
		game.setMaze(tempGame.getMaze());
		game.setDragons(tempGame.getDragons());
		game.setEagle(tempGame.getEagle());
		game.setSword(tempGame.getSword());

	}


	/**
	 * Prints the maze.
	 * 
	 * @param game 
	 */
	public static void printMaze(Game game) {

		String [][] maze =game.getMaze().getBoard();

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
