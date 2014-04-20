package maze.cli;

import maze.logic.*;
import maze.logic.Character.Direction;

import java.io.IOException;
import java.util.Scanner;


/**
 * The Class CLInterface.
 */
public class CLInterface {
	
	/** The game. */
	private static Game game;

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
				else if (menuOption==2)
				{					
					maze.gui.GUInterface.startGameGUI();
					validOption = true;					
				} 
				else
				{		
					throw new Exception();
				}

			} catch (Exception e) {

				System.out.println("Invalid option.");
				sc0.next();
				continue;

			}

		}

	}


	/**
	 * Main menu.
	 *
	 * @param game the game
	 */
	public static void mainMenu(Game game) {

		Scanner sc0 = new Scanner(System.in);
		boolean validOption = false;

		System.out.println();
		System.out.println("1 - Standard Maze");
		System.out.println("2 - Random generated Maze");
		System.out.println("3 - Load Game");
		System.out.println();

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
				else if (menuOption == 3)
				{
					try {

						loadGame();
						validOption = true;

					} catch (Exception e) {

						System.out.println("Load game error.");

					}
				}
				else
				{
					throw new Exception(); 
				}
			} catch (Exception e){

				System.out.println("Invalid option.");
				sc0.next();
				continue;

			}

		}

	}

	
	/**
	 * Maze settings.
	 *
	 * @param game the game
	 */
	private static void mazeSettings(Game game) {

		int mazeBuilder = selectMazeBuilder(game);
		int mazeSize = selectMazeSize(game);
		int numDragons = selectNumberDragons(game);
		int dragonsMode = selectDragonsMode(game);

		game.initGame(mazeSize, dragonsMode, numDragons, mazeBuilder);

	}


	/**
	 * Select maze builder.
	 *
	 * @param game the game
	 * @return the id of the builder selected
	 */
	private static int selectMazeBuilder(Game game) {

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

				if ((builder == 1) || (builder == 2)) {

					return builder;

				} else {

					throw new Exception();

				}

			} catch (Exception e) {

				System.out.println("Invalid Option.");
				sc0.next();
				continue;

			}

		}

		return 1;

	}


	/**
	 * Select maze size.
	 *
	 * @param game the game
	 * @return the int
	 */
	private static int selectMazeSize(Game game) {

		int mazeSize;
		Scanner sc1 = new Scanner(System.in);
		boolean validOption = false;

		System.out.println();

		while (!validOption) {

			try {

				System.out.println("Enter maze size (N) for a NxN Maze [10 - 35]:");

				mazeSize = sc1.nextInt();

				if (mazeSize > 9 || mazeSize < 36) {

					return mazeSize;

				} else {

					throw new Exception();

				}

			} catch (Exception e) {

				System.out.println("Invalid Size.");
				sc1.next();
				continue;

			}

		}

		return 10;

	}


	/**
	 * Select number dragons.
	 *
	 * @param game the game
	 * @return the number of dragons
	 */
	private static int selectNumberDragons(Game game) {

		int numDragons;
		Scanner sc1 = new Scanner(System.in);
		boolean validOption = false;

		System.out.println();

		while(!validOption) {

			try {

				System.out.println("Number of Dragons [1-16]:");
				numDragons = sc1.nextInt();

				if ((numDragons > 0) && (numDragons < 17)) {

					return numDragons;

				} else {

					throw new Exception();

				}

			} catch (Exception e) {

				System.out.println("Invalid number of Dragons.");
				sc1.next();
				continue;

			}

		}

		return 1;

	}


	/**
	 * Select dragons mode.
	 *
	 * @param game the game
	 * @return the dragon mode selected
	 */
	private static int selectDragonsMode(Game game) {

		int mode;
		Scanner sc1 = new Scanner(System.in);
		boolean validOption = false;

		System.out.println();
		System.out.println("Dragon Mode:");
		System.out.println("1 - Static");
		System.out.println("2 - Dynamic");
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
				sc1.next();
				continue;

			}

		}

		return 1;	
	}


	/**
	 * Game loop.
	 *
	 * @throws Exception the exception
	 */
	public static void startGame() throws Exception{
		
		game = new Game();

		/* Game Options */
		mainMenu(game);

		/* Update Maze Cells */
		game.updatePositions();

		/* Draw the maze */
		printMaze();

		/* Game loop */
		while( !game.gameOver() && !playerMove() ){

			game.eagleMove();

			game.dragonsMove();

			printMaze();

		}
		
		endGame();

	}


	/**
	 * End game.
	 */
	private static void endGame() {
		
		if (!game.getPlayer().isDead() && game.getDragonsAlive() > 0){
			return;
		} else if (game.getPlayer().isDead()){
			System.out.println("You Lost.");
		} else {
			System.out.println("You Win.");
		}
		
		Scanner input = new Scanner(System.in);
		try {
			System.out.println("New Game (y/n)?");
			String opt = input.nextLine();
			opt.toLowerCase();
			
			if (opt.equals("y")){
				startGame();
			} else if (opt.equals("n")){
				return;
			} else 
				throw new Exception();
			
		} catch (Exception e){
			System.out.println("Invalid input");
		}
	
		
	}


	/**
	 * Read the player input.
	 *
	 * @return false, if a valid move was introduced
	 * true, if player want to exit the game
	 */
	public static boolean playerMove() {

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
			return gameMenu();
		default:
			break;
		}	
		return false;

	}


	/**
	 * Game menu.
	 *
	 * @return true, if successful
	 * false, if exit option was selected
	 */
	private static boolean gameMenu() {

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

				switch (opt) {
				case 1:
					return false;
				case 2:
					newGame();
					validOption = true;
					break;
				case 3:
					saveGame();
					validOption = true;
					break;
				case 4:
					try {				
						loadGame();
						validOption = true;
					} catch (Exception e) {
						System.out.println("Load game error.");
					}
					break;
				case 5:
					return true;
				default:
					break;
				}
			} catch (Exception e) {

				System.out.println("Invalid Option.");
				sc1.next();
				continue;

			}

		}

		return false;

	}


	/**
	 * New game.
	 */
	private static void newGame() {

		Game tempGame = new Game();

		mainMenu(tempGame);

		game.setGame(tempGame);

	}


	/**
	 * Load game.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static void loadGame() throws IOException {

		try	{

			String path =  "./saved_games/" + fileName();
			
			game.loadGame(path);

			System.out.printf("Load complete.");

		} catch (IOException i) {

			System.out.printf("File not supported.");

		} catch (ClassNotFoundException c) {

			System.out.println("Game class not found");

		}

	}


	/**
	 * Save game.
	 */
	private static void saveGame() {

		try	{		

			String path = "./saved_games/" + fileName();
			
			game.saveGame(path);

			System.out.printf("Serialized data is saved in " + path);

		} catch (IOException i) {
				
			System.out.printf("File not supported.");
			
		}

	}


	/**
	 * File name.
	 *
	 * @return the string
	 */
	private static String fileName() {

		Scanner sc0 = new Scanner(System.in);

		System.out.println("Files are saved/loaded in the folder: './saved_games/'");
		System.out.println("Name of file excluding extension:");

		String name = sc0.next();
		name.concat(".dat");
		
		return name;
		
	}


	/**
	 * Prints the maze.
	 */
	public static void printMaze() {

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
