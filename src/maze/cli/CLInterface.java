package maze.cli;


import maze.logic.*;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class CLInterface.
 */
public class CLInterface {

	
	/**
	 * Main menu.
	 *
	 * @param game the game
	 */
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
		System.out.println("Enter N for NxN Maze:");
		Scanner sc1 = new Scanner(System.in);
		mazeSize = sc1.nextInt();
		
		boolean validOption = false;
		int mode;
		
		System.out.println("Select your option:");
		System.out.println("Dragon Mode:");
		System.out.println("1 - Static");
		System.out.println("2 - Dinamic");
		System.out.println("3 - Mixed");
		
		while(!validOption)	{
		
			mode = sc1.nextInt();
			if (mode > 0 && mode < 4){
				while(!validOption){
					System.out.println("Number of Dragons [1-10]:");
					int nD = sc1.nextInt();
					if (nD > 0 && nD < 11){
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

	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) 
	{
		Game game = new Game();

		mainMenu(game);

		game.updatePositions();
		game.printMaze();

		boolean gameEnd = false;

		while(!gameEnd){
			gameEnd = playerMove(game);
			game.checkKill();
			game.updatePosition(game.getPlayer());
			
			if (!gameEnd ) {

				if (game.getEagle().isActive()) game.eagleMove();
				
				game.dragonsMove();
				
				game.updatePositions();
				game.printMaze();
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

		boolean validMove = false;

		while (!validMove){

			System.out.println("\nMove (w-up; a-left; s-down; d-right; e- launch eagle; f- don't move; q-quit):");

			Scanner moveInput = new Scanner(System.in);
			String move = moveInput.nextLine();

			switch (move) {
			case "a":
				if (game.checkPlayerPosition(game.getPlayer().getLeftPosition())) {
					game.getMaze().clearCell(game.getPlayer().getPosition());
					game.getPlayer().moveLeft();
					validMove = true;
				}
				break;
			case "s":
				if (game.checkPlayerPosition(game.getPlayer().getBottomPosition())) {
					game.getMaze().clearCell(game.getPlayer().getPosition());
					game.getPlayer().moveDown();
					validMove = true;
				}
				break;
			case "d":
				if (game.checkPlayerPosition(game.getPlayer().getRightPosition())) {
					game.getMaze().clearCell(game.getPlayer().getPosition());
					game.getPlayer().moveRight();
					validMove = true;
				}
				break;
			case "w":
				if (game.checkPlayerPosition(game.getPlayer().getUpperPosition())) {
					game.getMaze().clearCell(game.getPlayer().getPosition());
					game.getPlayer().moveUp();
					validMove = true;
				}
				break;
			case "e":
				if (!game.getPlayer().eagleLaunched()) {
					game.eagleLaunched();
					validMove = true;
				}
				break;
			case "q":
				validMove = true;
				moveInput.close();
				return true;
			case "f":
				validMove = true;
				break;
			default:
				break;
			}
			if (validMove == false) {
				System.out.println("\nInvalid Move!");
			}
		}

		if (game.getSword().isActive()){
			if (game.getPlayer().getPosition().equals(game.getSword().getPosition())) {
				game.getPlayer().getArmed();
				game.getSword().picked();
			}
		}

		return false;
	}
	
}