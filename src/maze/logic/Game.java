package maze.logic;

import java.util.Random;
import java.util.Scanner;

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
		dragon = new Dragon(new Position(3,1), Dragon.Mode.DINAMIC);
		sword = new Sword(new Position(8,1));
		eagle = new Eagle(player.getPosition());
	}

	public void initGame(int opt) {
		MazeGenerator tempMaze;
		if (opt == 0){
			tempMaze = new MazeGenerator();
			maze = tempMaze.getMaze();
			player.setPosition(new Position(1, 1));
			dragon.setPosition(new Position(3, 1));
			sword.setPosition(new Position(8, 1));
		} else{
			tempMaze = new MazeGenerator(opt);
			maze = tempMaze.getMaze();
			player.setPosition(maze.randomPosition());
			dragon.setPosition(maze.randomDragonPosition());
			sword.setPosition(maze.randomPosition());
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
	 * Read the player input and when it is a valid input update the player position and clear the previous position in the maze.
	 *
	 * @return true, if successful
	 */
	public boolean playerMove() {

		boolean validMove = false;

		while (!validMove){

			System.out.println("\nMove (w-up; a-left; s-down; d-right; e- launch eagle; f- don't move; q-quit):");

			Scanner moveInput = new Scanner(System.in);
			String move = moveInput.nextLine();

			switch (move) {
			case "a":
				if (checkPlayerPosition(player.getLeftPosition())) {
					maze.clearCell(player.getPosition());
					player.moveLeft();
					validMove = true;
				}
				break;
			case "s":
				if (checkPlayerPosition(player.getBottomPosition())) {
					maze.clearCell(player.getPosition());
					player.moveDown();
					validMove = true;
				}
				break;
			case "d":
				if (checkPlayerPosition(player.getRightPosition())) {
					maze.clearCell(player.getPosition());
					player.moveRight();
					validMove = true;
				}
				break;
			case "w":
				if (checkPlayerPosition(player.getUpperPosition())) {
					maze.clearCell(player.getPosition());
					player.moveUp();
					validMove = true;
				}
				break;
			case "e":
					player.launchEagle();
					eagle.setPosition(player.getPosition());
					eagle.setPath(sword.getPosition());
					validMove = true;
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

		if (sword.isActive()){
			if (player.getPosition().equals(sword.getPosition())) {
				player.getArmed();
				sword.pickSword();
			}
		}

		return false;
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
	}


	/**
	 * Check if the player kills the dragon or vice-versa.
	 */

	public void checkKill() {
		if (!player.isArmed()) {
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
			//setDragonsPosition(dragons,maze);
			updatePositions();
		}
		setPlayerPosition();	
	}


	/**
	 * Update positions.
	 */
	public void updatePositions() {
		updatePosition(player);
		//updatePosition(eagle);

		// será necessário atualizar a posição da espada???
		updatePosition(sword);
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







}
