package maze.cli;

import maze.logic.*;
import maze.logic.Character.Direction;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;


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
					//maze.gui.StarGameWindow.startGame();
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

						loadGame(game);
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

		int mazeBuilder = selectMazeBuilder();
		int mazeSize = selectMazeSize();
		int numDragons = selectNumberDragons();
		int dragonsMode = selectDragonsMode();

		game.initGame(mazeSize, dragonsMode, numDragons, mazeBuilder);

	}


	/**
	 * Select maze builder.
	 *
	 * @return the id of the builder selected
	 */
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
	 * @return the int
	 */
	private static int selectMazeSize() {

		int mazeSize;
		Scanner sc1 = new Scanner(System.in);
		boolean validOption = false;

		System.out.println();

		while (!validOption) {

			try {

				System.out.println("Enter maze size (N) for a NxN Maze [10 - 30]:");

				mazeSize = sc1.nextInt();

				if (mazeSize > 9 || mazeSize < 31) {

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
	 * @return the number of dragons
	 */
	private static int selectNumberDragons() {

		int numDragons;
		Scanner sc1 = new Scanner(System.in);
		boolean validOption = false;

		System.out.println();

		while(!validOption) {

			try {

				System.out.println("Number of Dragons [1-15]:");
				numDragons = sc1.nextInt();

				if ((numDragons > 0) && (numDragons < 16)) {

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
	 * @return the dragon mode selected
	 */
	private static int selectDragonsMode() {

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
		
		Game game = new Game();

		/* Game Options */
		mainMenu(game);

		/* Update Maze Cells */
		game.updatePositions();

		/* Draw the maze */
		printMaze(game);

		/* Game loop */
		while( !game.gameOver() && !playerMove(game) ){

			game.eagleMove();

			game.dragonsMove();

			printMaze(game);

		}

	}


	/**
	 * Read the player input.
	 *
	 * @param game the game
	 * @return false, if a valid move was introduced
	 * @return true, if player want to exit the game
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


	/**
	 * Game menu.
	 *
	 * @param game the game
	 * @return true, if successful
	 * @return false, if exit option was selected
	 */
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

				switch (opt) {
				case 1:
					return false;
				case 2:
					newGame(game);
					validOption = true;
					break;
				case 3:
					saveGame(game);
					validOption = true;
					break;
				case 4:
					try {				
						loadGame(game);
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
	 *
	 * @param game the game
	 */
	private static void newGame(Game game) {

		Game tempGame = new Game();

		mainMenu(tempGame);

		game.setGame(tempGame);

	}


	/**
	 * Load game.
	 *
	 * @param game the game
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static void loadGame(Game game) throws IOException {

		try	{

			String path =  "./saved_games/" + fileName();

			FileInputStream fileIn = new FileInputStream(path);
			ObjectInputStream is = new ObjectInputStream(fileIn);

			/* load the saved game in the file to the object tempGame */
			Game tempGame = (Game) is.readObject();

			is.close();
			fileIn.close();

			/* Change the Current Game */
			game.setGame(tempGame);

			System.out.printf("Load complete.");

		} catch (IOException i) {

			i.printStackTrace();

		} catch (ClassNotFoundException c) {

			System.out.println("Game class not found");
			c.printStackTrace();

		}

	}


	/**
	 * Save game.
	 *
	 * @param game the game
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static void saveGame(Game game) throws IOException {

		try	{		

			String path = "./saved_games/" + fileName();

			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream os = new ObjectOutputStream(fileOut);

			/* Write the game in a file */
			os.writeObject(game);

			fileOut.close();
			os.close();

			System.out.printf("Serialized data is saved in " + path);

		} catch (IOException i) {
			
			i.printStackTrace();
			
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
	 *
	 * @param game the game
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
