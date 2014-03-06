package maze.logic;

import java.util.Random;
import java.util.Scanner;

//import maze.logic.Dragon.Mode;
//import java.util.ArrayList;

public class Game {

	private Maze maze;
	private Hero player;
	//	private ArrayList<Dragon> dragons = null;
	private Sword sword;
	private Dragon dragon;

	/**
	 * 
	 * Game Constructor: initializes a maze, one player, one dragon and a sword
	 * 
	 * */
	public Game() {	
		maze = new Maze();
		player = new Hero(1,1);
		dragon = new Dragon(3,1, Dragon.Mode.DINAMIC);
		//Dragon dragonTemp = new Dragon(3,1, Dragon.Mode.STATIC);
		//dragons.add(dragonTemp);
		sword = new Sword(8,1);
	}
	
	/**
	 * 
	 * Game Loop
	 * Initializes a Standard Game by calling the constructor of Game
	 * 
	 * */
	public static void main(String[] args) {

		Game game = new Game();
		game.refreshMaze();
		game.maze.printMaze();

		boolean gameEnd = false;

		while (!gameEnd) {

			gameEnd = game.playerMove();
			game.checkKill();
			game.refreshMaze();
			if (!gameEnd ) {
				if (game.dragon.getMode() != Dragon.Mode.STATIC || !game.dragon.isAsleep() || !game.dragon.isDead()) {
					game.dragonMove();
					game.checkKill();
					game.refreshMaze();
				}
				game.maze.printMaze();
				gameEnd = game.gameOver();
			} else
				System.out.println("\nExit");
		}
	}	

	/**
	 * 
	 * Set the symbol of the player (H - unarmed; A- armed) in his position
	 * 
	 * */
//	private void setPlayerPosition() {
//		maze.setCellValue(player.getLine(), player.getCol(), player.getSymbol());
//	}
	private void setPlayerPosition() {
	maze.setCellValue(player.getPosition(), player.getSymbol());
}

	/**
	 * 
	 * Set the symbol of the dragon (D - awake; d- asleep) in his position
	 * 
	 * */
//	private void setSwordPosition() {
//		maze.setCellValue(sword.getLine(), sword.getCol(), sword.getSymbol());
//	}
	private void setSwordPosition() {
	maze.setCellValue(sword.getPosition(), sword.getSymbol());
	}

	/**
	 * 
	 * Set the symbol of the sword (E) in its position
	 * 
	 * */
//	private void setDragonPosition() {
//		maze.setCellValue(dragon.getLine(), dragon.getCol(), dragon.getSymbol());
//	}
	private void setDragonPosition() {
		maze.setCellValue(dragon.getPosition(), dragon.getSymbol());
	}
	

	//	private void setDragonsPosition(ArrayList<Dragon> dragons, Maze maze) {
	//		for (Dragon dragon : dragons) {
	//			if (!dragon.isDead()) {
	//				if (dragon.isAsleep()) {
	//					maze.setCellValue(dragon.getLine(), dragon.getCol(), 'd');
	//				} else {
	//					maze.setCellValue(dragon.getLine(), dragon.getCol(), 'D');
	//				}		
	//			}
	//		}
	//	}

	/**
	 * 
	 * Read the player input and when it is a valid input update the player position and clear the previous position in the maze
	 * 
	 * */
	public boolean playerMove() {

		boolean validMove = false;

		while (!validMove){

			System.out.println("\nMove (w-up; a-left; s-down; d-right; e- don't move; q-quit):");

			Scanner moveInput = new Scanner(System.in);
			String move = moveInput.nextLine();

			switch (move) {
			case "a":
//				if (checkPlayerPosition(player.getLine(),player.getCol()-1)) {
//					maze.clearCell(player.getLine(), player.getCol());
//					player.moveLeft();
//					validMove = true;
//				}
				if (checkPlayerPosition(player.getPosition().leftPosition())) {
					maze.clearCell(player.getPosition());
					player.moveLeft();
					validMove = true;
				}
				break;
			case "s":
				if (checkPlayerPosition(player.getPosition().bottomPosition())) {
					maze.clearCell(player.getPosition());
					player.moveDown();
					validMove = true;
				}
				break;
			case "d":
				if (checkPlayerPosition(player.getPosition().rightPosition())) {
					maze.clearCell(player.getPosition());
					player.moveRight();
					validMove = true;
				}
				break;
			case "w":
				if (checkPlayerPosition(player.getPosition().upperPosition())) {
					maze.clearCell(player.getPosition());
					player.moveUp();
					validMove = true;
				}
				break;
			case "q":
				validMove = true;
				moveInput.close();
				return true;
			case "e":
				validMove = true;
				break;
			default:
				break;
			}
			if (validMove == false) {
				System.out.println("\nInvalid Move!");
			}
		}

		if (sword.isActive()){
			if (player.getPosition().equals(sword.getPosition())) {
				player.getArmed();
				sword.pickSword();
			}
		}

		return false;
	}

	/**
	 * 
	 * Check if the move is valid or not
	 * 
	 * */
//	public boolean checkPlayerPosition(int line,int col) {
//		if (line == maze.getExitLine() && col == maze.getExitCol()){
//			if (!player.isArmed()) {
//				System.out.println("\nYou need to get the sword (E) and kill the Dragon (D / d)");
//				return false;
//			} else {
//				if (!dragon.isDead()) {
//					System.out.println("\nYou need to kill the Dragon (D / d)");
//					return false;
//				} else
//					return true;
//			}
//		}
//		if (maze.getCellValue(line, col) == 'X') return false;
//		return true;
//	}
	public boolean checkPlayerPosition(Position pos) {
		if (pos.equals(maze.getExit())){
			if (!player.isArmed()) {
				System.out.println("\nYou need to get the sword (E) and kill the Dragon (D / d)");
				return false;
			} else {
				if (!dragon.isDead()) {
					System.out.println("\nYou need to kill the Dragon (D / d)");
					return false;
				} else
					return true;
			}
		}
		if (maze.getPositionValue(pos) == 'X') return false;
		return true;
	}

	/**
	 * 
	 * Check if the left, bottom, right and upper cells are valid cells and select a possible move
	 * 
	 * */
	private void dragonMove() {
		int moves[] = {0,0,0,0};
		int move;
		boolean validMove = false;
		Random pickMove = new Random();

//		if (maze.cellIsEmpty(dragon.getLine(), dragon.getCol()-1)) moves[0]=1;
//		if (maze.cellIsEmpty(dragon.getLine()+1, dragon.getCol())) moves[1]=1;
//		if (maze.cellIsEmpty(dragon.getLine(), dragon.getCol()+1)) moves[2]=1;
//		if (maze.cellIsEmpty(dragon.getLine()-1, dragon.getCol())) moves[3]=1;

		while (!validMove){
			move = pickMove.nextInt(5);
			switch (move) {
			case 0:
				if (maze.cellIsEmpty(dragon.getPosition().leftPosition())) {
					maze.clearCell(dragon.getPosition());
					dragon.moveLeft();
					validMove = true;
				}
				break;
			case 1:
				if (maze.cellIsEmpty(dragon.getPosition().bottomPosition())) {
//					maze.clearCell(dragon.getLine(), dragon.getCol());
					maze.clearCell(dragon.getPosition());
					dragon.moveDown();
					validMove = true;
				}
				break;		
			case 2:
				if (maze.cellIsEmpty(dragon.getPosition().rightPosition())) {
					maze.clearCell(dragon.getPosition());
					dragon.moveRight();
					validMove = true;
				}
				break;
			case 3:
				if (maze.cellIsEmpty(dragon.getPosition().upperPosition())) {
					maze.clearCell(dragon.getPosition());
					dragon.moveUp();
					validMove = true;
				}
				break;
			case 4:
				validMove = true;
			default:
				break;
			}
		}

	}

	/**
	 * 
	 * Check if the player kills the dragon or vice-versa
	 * 
	 * */
//	private void checkKill() {
//		if (!player.isArmed()) {
//			if ((player.getLine()==dragon.getLine() && (player.getCol()==dragon.getCol()-1 || player.getCol()==dragon.getCol()+1))||
//					(player.getCol()==dragon.getCol() && (player.getLine()==dragon.getLine()-1 || player.getLine()==dragon.getLine()+1))) {
//				player.dye();
//			}
//		} else {
//			if ((player.getLine()==dragon.getLine() && (player.getCol()==dragon.getCol()-1 || player.getCol()==dragon.getCol()+1))||
//					(player.getCol()==dragon.getCol() && (player.getLine()==dragon.getLine()-1 || player.getLine()==dragon.getLine()+1))) {
//				dragon.dye();
//			}
//		}
//	}
	private void checkKill() {
	if (!player.isArmed()) {
		if (player.getPosition().equals(dragon.getPosition().leftPosition()) ||
				player.getPosition().equals(dragon.getPosition().bottomPosition()) ||
				player.getPosition().equals(dragon.getPosition().rightPosition()) ||
				player.getPosition().equals(dragon.getPosition().upperPosition())) {
			player.dye();
		}
	} else {
		if (player.getPosition().equals(dragon.getPosition().leftPosition()) ||
				player.getPosition().equals(dragon.getPosition().bottomPosition()) ||
				player.getPosition().equals(dragon.getPosition().rightPosition()) ||
				player.getPosition().equals(dragon.getPosition().upperPosition())) {
			dragon.dye();
		}
	}
}

	/**
	 * 
	 * Update the positions of the player, the dragon and sword in the maze
	 * 
	 * */
//	private void refreshMaze(){
//		if (dragon.getLine() == sword.getLine() && dragon.getCol() == sword.getCol())
//			maze.setCellValue(dragon.getLine(), dragon.getCol(), 'F');
//		else {
//			setDragonPosition();
//			setSwordPosition();
//			//setDragonsPosition(dragons,maze);
//		}
//		setPlayerPosition();	
//	}
	private void refreshMaze(){
	if (dragon.getPosition().equals(sword.getPosition()))
		maze.setCellValue(dragon.getPosition(), 'F');
	else {
		setDragonPosition();
		setSwordPosition();
		//setDragonsPosition(dragons,maze);
	}
	setPlayerPosition();	
}
	

	/**
	 * 
	 * Check if the state of the maze is a end state (Player dead or Dragon dead and the player at cave exit)
	 * 
	 * */
//	private boolean gameOver() {
//		if (dragon.isDead()){
//			if (player.getLine()==maze.getExitLine() && player.getCol() == maze.getExitCol()) {
//				System.out.println("\nCongratulations you Win!!!");
//				return true;
//			} else
//				return false;
//		} 
//		if (player.isDead()) {
//			System.out.println("\nGame Over you Lost!!!");
//			return true;
//		}
//		return false;
//	}
	private boolean gameOver() {
		if (dragon.isDead()){
			if (player.getPosition().equals(maze.getExit())) {
				System.out.println("\nCongratulations you Win!!!");
				return true;
			} else
				return false;
		} 
		if (player.isDead()) {
			System.out.println("\nGame Over you Lost!!!");
			return true;
		}
		return false;
	}

}
