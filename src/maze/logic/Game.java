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

	public Game() {	
		maze = new Maze();
		player = new Hero(1,1);
		dragon = new Dragon(3,1, Dragon.Mode.DINAMIC);
		//Dragon dragonTemp = new Dragon(3,1, Dragon.Mode.STATIC);
		//dragons.add(dragonTemp);
		sword = new Sword(8,1);
	}

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

	private void setPlayerPosition() {
		maze.setCellValue(player.getLine(), player.getCol(), player.getSymbol());
	}

	private void setSwordPosition() {
		maze.setCellValue(sword.getLine(), sword.getCol(), sword.getSymbol());
	}

	private void setDragonPosition() {
		maze.setCellValue(dragon.getLine(), dragon.getCol(), dragon.getSymbol());

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

	public boolean playerMove() {

		boolean validMove = false;

		while (!validMove){

			System.out.println("\nMove (w-up; a-left; s-down; d-right; e- don't move; q-quit):");

			Scanner moveInput = new Scanner(System.in);
			String move = moveInput.nextLine();

			switch (move) {
			case "a":
				if (checkCellPlayer(player.getLine(),player.getCol()-1)) {
					maze.clearCell(player.getLine(), player.getCol());
					player.moveLeft();
					validMove = true;
				}
				break;
			case "s":
				if (checkCellPlayer(player.getLine()+1,player.getCol())) {
					maze.clearCell(player.getLine(), player.getCol());
					player.moveDown();
					validMove = true;
				}
				break;
			case "d":
				if (checkCellPlayer(player.getLine(),player.getCol()+1)) {
					maze.clearCell(player.getLine(), player.getCol());
					player.moveRight();
					validMove = true;
				}
				break;
			case "w":
				if (checkCellPlayer(player.getLine()-1,player.getCol())) {
					maze.clearCell(player.getLine(), player.getCol());
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
			if (player.getLine()==sword.getLine() && player.getCol()==sword.getCol()) {
				player.getArmed();
				sword.pickSword();
			}
		}

		return false;
	}

	public boolean checkCellPlayer(int line,int col) {
		if (line == maze.getExitLine() && col == maze.getExitCol()){
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
		if (maze.getCellValue(line, col) == 'X') return false;
		return true;
	}

	private void dragonMove() {
		int moves[] = {0,0,0,0};
		int move;
		boolean validMove = false;
		Random pickMove = new Random();

		if (maze.cellIsEmpty(dragon.getLine(), dragon.getCol()-1)) moves[0]=1;
		if (maze.cellIsEmpty(dragon.getLine()+1, dragon.getCol())) moves[1]=1;
		if (maze.cellIsEmpty(dragon.getLine(), dragon.getCol()+1)) moves[2]=1;
		if (maze.cellIsEmpty(dragon.getLine()-1, dragon.getCol())) moves[3]=1;

		while (!validMove){
			move = pickMove.nextInt(5);
			switch (move) {
			case 0:
				if (moves[0] == 1) {
					maze.clearCell(dragon.getLine(), dragon.getCol());
					dragon.moveLeft();
					validMove = true;
				}
				break;
			case 1:
				if (moves[1] == 1) {
					maze.clearCell(dragon.getLine(), dragon.getCol());
					dragon.moveDown();
					validMove = true;
				}
				break;		
			case 2:
				if (moves[2] == 1) {
					maze.clearCell(dragon.getLine(), dragon.getCol());
					dragon.moveRight();
					validMove = true;
				}
				break;
			case 3:
				if (moves[3] == 1) {
					maze.clearCell(dragon.getLine(), dragon.getCol());
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

	private void checkKill() {
		if (!player.isArmed()) {
			if ((player.getLine()==dragon.getLine() && (player.getCol()==dragon.getCol()-1 || player.getCol()==dragon.getCol()+1))||
					(player.getCol()==dragon.getCol() && (player.getLine()==dragon.getLine()-1 || player.getLine()==dragon.getLine()+1))) {
				player.dye();
			}
		} else {
			if ((player.getLine()==dragon.getLine() && (player.getCol()==dragon.getCol()-1 || player.getCol()==dragon.getCol()+1))||
					(player.getCol()==dragon.getCol() && (player.getLine()==dragon.getLine()-1 || player.getLine()==dragon.getLine()+1))) {
				dragon.dye();
			}
		}
	}

	private void refreshMaze(){
		if (dragon.getLine() == sword.getLine() && dragon.getCol() == sword.getCol())
			maze.setCellValue(dragon.getLine(), dragon.getCol(), 'F');
		else {
			setDragonPosition();
			setSwordPosition();
			//setDragonsPosition(dragons,maze);
		}
		setPlayerPosition();	
	}

	private boolean gameOver() {
		if (dragon.isDead()){
			if (player.getLine()==maze.getExitLine() && player.getCol() == maze.getExitCol()) {
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
