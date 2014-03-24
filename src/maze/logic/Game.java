package maze.logic;

import java.util.Random;
import java.util.Scanner;

import maze.logic.Dragon.Mode;

// TODO: Auto-generated Javadoc
//import maze.logic.Dragon.Mode;
//import java.util.ArrayList;

/**
 * The Class Game.
 */
public class Game {

	/** The maze. */
	private Maze maze;

	/** The player. */
	private Hero player;

	/** The sword. */
	private Sword sword;

	/** The dragon. */
	private Dragon dragon;
	//	private ArrayList<Dragon> dragons = new ArrayList<Dragon>();

	/** The Eagle. */
	private Eagle eagle;

	/**
	 * Game Constructor: initializes a maze, one player, one dragon and a sword.
	 */
	public Game() {
		maze = new Maze();
		player = new Hero(new Position(1,1));
		dragon = new Dragon(new Position(3,1), Dragon.Mode.STATIC);
		sword = new Sword(new Position(8,1));
		eagle = new Eagle(player.getPosition());
	}

	public void initGame(int opt, int mode) {
		MazeGenerator tempMaze;
		if (opt == 0){
			tempMaze = new MazeGenerator();
			maze = tempMaze.getMaze();
			player.setPosition(new Position(1, 1));
			dragon.setPosition(new Position(3, 1));
			dragon.setMode(Mode.STATIC);
			sword.setPosition(new Position(8, 1));
		} else{
			tempMaze = new MazeGenerator(opt);
			maze = tempMaze.getMaze();
			player.setPosition(maze.randomPosition());
			sword.setPosition(maze.randomPosition());
			dragon.setPosition(maze.randomDragonPosition());
			if (mode == 1) {
				dragon.setMode(Mode.STATIC);
			} else if (mode == 2) {
				dragon.setMode(Mode.DINAMIC);
			} else {
				dragon.setMode(Mode.MIXED);
			}
		}
	}


	public Maze getMaze(){
		return maze;
	}


	public Hero getPlayer(){
		return player;
	}


	public Dragon getDragon(){
		return dragon;
	}

	public Eagle getEagle() {
		return eagle;
	}

	public Sword getSword() {
		return sword;
	}

	/**
	 * Set the symbol of the player (H - unarmed; A- armed) in his position.
	 */
	private void setPlayerPosition() {
		maze.setCellValue(player.getPosition(), player.getSymbol());
	}


	/**
	 * Set the symbol of the dragon (D - awake; d- asleep) in his position.
	 */
	private void setSwordPosition() {
		maze.setCellValue(sword.getPosition(), sword.getSymbol());
	}


	/**
	 * Set the symbol of the sword (E) in its position.
	 */
	private void setDragonPosition() {
		maze.setCellValue(dragon.getPosition(), dragon.getSymbol());
	}


	/**
	 * Set the symbol of the sword (E) in its position.
	 *
	 * @param character the character
	 */
	public void updatePosition(Character character){
		maze.setCellValue(character.getPosition(), character.getSymbol());
	}


	/**
	 * Check if the move is valid or not.
	 *
	 * @param pos the position where player want to move
	 * @return true, if successful
	 */
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
		if (maze.getPositionValue(pos).equals("XX")) return false;
		return true;
	}


	/**
	 * Check if the left, bottom, right and upper cells are valid cells and select a possible move.
	 */
	public void dragonMove() {

		if (dragon.getMode() == Mode.STATIC) return;

		Random randomNr = new Random();

		if (dragon.getMode() == Mode.MIXED) {
			int changeStatus = randomNr.nextInt(10);
			if (changeStatus % 3 == 0) {
				dragon.changeStatus();
			}
		}


		if (dragon.isAsleep()) return;

		int move;
		boolean validMove = false;
		Random pickMove = new Random();

		while (!validMove){
			move = pickMove.nextInt(5);
			switch (move) {
			case 0:
				if (maze.cellIsEmpty(dragon.getLeftPosition())) {
					maze.clearCell(dragon.getPosition());
					dragon.moveLeft();
					validMove = true;
				}
				break;
			case 1:
				if (maze.cellIsEmpty(dragon.getBottomPosition())) {
					maze.clearCell(dragon.getPosition());
					dragon.moveDown();
					validMove = true;
				}
				break;		
			case 2:
				if (maze.cellIsEmpty(dragon.getRightPosition())) {
					maze.clearCell(dragon.getPosition());
					dragon.moveRight();
					validMove = true;
				}
				break;
			case 3:
				if (maze.cellIsEmpty(dragon.getUpperPosition())) {
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

		checkKill();
	}


	/**
	 * Check if the player kills the dragon or vice-versa.
	 */

	public void checkKill() {

		if (!player.isArmed() && !dragon.isAsleep()) {
			if (player.getPosition().equals(dragon.getLeftPosition()) ||
					player.getPosition().equals(dragon.getBottomPosition()) ||
					player.getPosition().equals(dragon.getRightPosition()) ||
					player.getPosition().equals(dragon.getUpperPosition())) {
				player.die();
			}
		} else {
			if (player.getPosition().equals(dragon.getLeftPosition()) ||
					player.getPosition().equals(dragon.getBottomPosition()) ||
					player.getPosition().equals(dragon.getRightPosition()) ||
					player.getPosition().equals(dragon.getUpperPosition())) {
				dragon.die();
			}
		}
		if (!eagle.onWay() && eagle.isActive()){
			if (eagle.getPosition().equals(dragon.getLeftPosition()) ||
					eagle.getPosition().equals(dragon.getBottomPosition()) ||
					eagle.getPosition().equals(dragon.getRightPosition()) ||
					eagle.getPosition().equals(dragon.getUpperPosition())) {
				eagle.die();
			}
		}
	}


	/**
	 * Update the positions of the player, the dragon and sword in the maze.
	 */
	public void refreshMaze(){
		if (dragon.getPosition().equals(sword.getPosition()))
			maze.setCellValue(dragon.getPosition(), "F ");
		else {
			setDragonPosition();
			setSwordPosition();
			updatePositions();
		}
		setPlayerPosition();	
	}


	/**
	 * Update positions.
	 */
	public void updatePositions() {
		updatePosition(player);
		if (sword.isActive()) updatePosition(sword);
		if (eagle.isActive()) updatePosition(eagle);
		updatePosition(dragon);
	}


	/**
	 * Check if the state of the maze is a end state (Player dead or Dragon dead and the player at cave exit).
	 *
	 * @return true, if successful
	 */
	public boolean gameOver() {
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

	public void eagleMove() {

		if (!eagle.onWay()) {
			sword.setPosition(eagle.getPosition());
			sword.setActive();
		} else {
			if (eagle.getPosition().equals(sword.getPosition())){
				sword.picked();
			}
			maze.setCellValue(eagle.getPosition(), eagle.getLastCell());
			eagle.move();
			eagle.setLastCell(maze.getPositionValue(eagle.getPosition()));
		}

	}

	public void printMaze() {
		maze.printMaze();	
	}


}
