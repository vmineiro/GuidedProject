package maze.logic;


import maze.logic.Dragon.Mode;

import java.util.Random;


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
	/*	private ArrayList<Dragon> dragons = new ArrayList<Dragon>(); */


	/** The Eagle. */
	private Eagle eagle;


	/**
	 * Game Constructor: initializes a maze, one player, one dragon and a sword.
	 */
	public Game() {
		maze = new Maze();
		player = new Hero(new Position(0,0));
		dragon = new Dragon(new Position(0,0), Dragon.Mode.STATIC);
		sword = new Sword(new Position(0,0));
		eagle = new Eagle(player.getPosition());
	}


	/**
	 * Initializes the game according to the selected settings in the interface.
	 *
	 * @param opt the option selected (standard = 0  or random maze = size of maze)
	 * @param mode the mode of the dragon
	 */
	public void initGame(int opt, int mode) {

		/* initialize a temporary maze to be build */
		MazeGenerator tempMaze;

		if (opt == 0){

			/* set the standard maze */

			tempMaze = new MazeGenerator();		
			maze = tempMaze.getMaze();

			/* set the positions of character and the dragon mode */

			player.setPosition(new Position(1, 1));
			sword.setPosition(new Position(8, 1));
			dragon.setPosition(new Position(3, 1));
			dragon.setMode(Mode.STATIC);

		} else{

			/* set a random maze with size of "opt" */

			tempMaze = new MazeGenerator(opt);
			maze = tempMaze.getMaze();

			/* set the positions of character and the dragon mode */
			player.setPosition(maze.randomPosition());

			/* set the symbol of character in the maze to ensure an empty cell between the Hero and the dragon*/
			updatePosition(player);
			sword.setPosition(maze.randomPosition());
			updatePosition(sword);
			dragon.setPosition(maze.randomDragonPosition());

			/* set the dragon mode according to the selected option in the interface */
			if (mode == 1) {
				dragon.setMode(Mode.STATIC);
			} else if (mode == 2) {
				dragon.setMode(Mode.DINAMIC);
			} else {
				dragon.setMode(Mode.MIXED);
			}
			updatePosition(dragon);
		}
	}



	/**
	 * Prints the maze.
	 */
	public void printMaze() {
		maze.printMaze();	
	}


	/**
	 * Gets the maze.
	 *
	 * @return the maze
	 */
	public Maze getMaze(){
		return maze;
	}


	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public Hero getPlayer(){
		return player;
	}


	/**
	 * Gets the dragon.
	 *
	 * @return the dragon
	 */
	public Dragon getDragon(){
		return dragon;
	}


	/**
	 * Gets the eagle.
	 *
	 * @return the eagle
	 */
	public Eagle getEagle() {
		return eagle;
	}


	/**
	 * Gets the sword.
	 *
	 * @return the sword
	 */
	public Sword getSword() {
		return sword;
	}


	/**
	 * Set the symbol of the character (Hero, Dragon, Sword or Eagle) in his maze position.
	 *
	 * @param character the character
	 */
	public void updatePosition(Character character){
		maze.setCellValue(character.getPosition(), character.getSymbol());
	}


	/**
	 * Check if the player move is valid or not.
	 *
	 * @param pos the position where player want to move
	 * @return true, if is a valid position
	 */
	public boolean checkPlayerPosition(Position pos) {

		if (maze.getPositionValue(pos).equals("XX")) return false;

		if (pos.equals(maze.getExit())){
			if (!player.isArmed()) {

				/* Message if the player tries to exit without getting the sword and kill the dragon */

				System.out.println("\nYou need to get the sword (E) and kill the Dragon (D / d)");
				return false;

			} else {

				/* Message if the player tries to exit without getting the sword and kill the dragon */

				if (!dragon.isDead()) {
					System.out.println("\nYou need to kill the Dragon (D / d)");
					return false;
				} else
					return true;
			}		
		}

		return true;
	}


	/**
	 * Check if the left, bottom, right and upper cells are valid cells and select a possible move.
	 */
	public void dragonMove() {

		/* if dragon is STATIC won't move */

		if (dragon.getMode() == Mode.STATIC) return;

		/* Initializes a random number to generate a status change and then a random move */

		Random randomNr = new Random();

		if (dragon.getMode() == Mode.MIXED) {

			/* 33% probability of changing dragon sleep status */

			int changeStatus = randomNr.nextInt(10);
			if (changeStatus % 3 == 0) {
				dragon.changeStatus();
			}

		}

		/* if the dragon is asleep won't move */

		if (dragon.isAsleep()) return;

		int move;
		boolean validMove = false;

		while (!validMove){

			/* generate a random number between 0 and 4.
			   0 - dragon will move left
			   1 - dragon will move down
			   2 - dragon will move right
			   3 - dragon will move up
			   4 - dragon will stay in same position 
			   if the move is not valid (wall) the loop generates another number until get a valid option */

			move = randomNr.nextInt(5);
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

		/* check if the dragon kill any Character (hero or eagle) or if the dragon dies. */
		checkKill();

	}


	/**
	 * Eagle launch methods.
	 * */
	public void eagleLaunched() {
		player.launchEagle();
		System.out.println("Eagle Launched");
		eagle.setPosition(player.getPosition());
		eagle.getSword(sword.getPosition());
		eagleMove();

	}


	/**
	 * Eagle move.
	 */
	public void eagleMove() {
		
		if (!eagle.isOnWay() && !eagle.isReturning()){
			if (player.getPosition().equals(sword.getPosition())){
				player.getArmed();
				sword.picked();
				eagle.setInactive();
				player.pickEagle();
				return;
			}
		}

		/*  If the eagle is moving set the previous value on the eagle position, move one position,
			and store the maze value on lastCell field */

		if (eagle.isOnWay() || eagle.isReturning()) {

			maze.setCellValue(eagle.getPosition(), eagle.getLastCell());				
			eagle.move();

			/* if the eagle arrive the sword position, after pick the sword the cell value will be 
				an empty cell ("  ") */
			if (eagle.getPosition().equals(sword.getPosition())){			
				eagle.setLastCell("  ");
			} else {
				eagle.setLastCell(maze.getPositionValue(eagle.getPosition()));	
			}

		}

		if (sword.isActive()) {
			if (eagle.getPosition().equals(sword.getPosition())){
				sword.picked();
			}
		} else {
			if (!eagle.isReturning()) {
				sword.droped(eagle.getPosition());
			}
		}	

	}


	/**
	 * Check if the player kills the dragon or if the dragon kills the hero or the eagle.
	 */
	public void checkKill() {

		/* Hero kills the Dragon */
		if ( player.isArmed()){
			if (player.getPosition().equals(dragon.getLeftPosition()) ||
					player.getPosition().equals(dragon.getBottomPosition()) ||
					player.getPosition().equals(dragon.getRightPosition()) ||
					player.getPosition().equals(dragon.getUpperPosition())) {
				dragon.die();
			}
		}

		if (!dragon.isAsleep()){

			/* Dragon kills the Hero */
			if (!player.isArmed()) {
				if (player.getPosition().equals(dragon.getLeftPosition()) ||
						player.getPosition().equals(dragon.getBottomPosition()) ||
						player.getPosition().equals(dragon.getRightPosition()) ||
						player.getPosition().equals(dragon.getUpperPosition())) {
					player.die();
				}
			}
			/* Dragon kills the Eagle */
			if (eagle.isActive() && !eagle.isOnWay() && !eagle.isReturning()){
				if (eagle.getPosition().equals(dragon.getLeftPosition()) ||
						eagle.getPosition().equals(dragon.getBottomPosition()) ||
						eagle.getPosition().equals(dragon.getRightPosition()) ||
						eagle.getPosition().equals(dragon.getUpperPosition())) {
					eagle.die();
					sword.droped(eagle.getPosition());
				}
			}

		}

	}


	/**
	 * Update the positions of all characters.
	 */
	public void updatePositions() {

		updatePosition(dragon);

		if (sword.isActive()) {
			if (dragon.getPosition().equals(sword.getPosition()))
				maze.setCellValue(dragon.getPosition(), "F ");
			else
				updatePosition(sword);		
		}

		if (eagle.isActive()) {
			if (dragon.getPosition().equals(eagle.getPosition()) && !eagle.isReturning()){
				maze.setCellValue(dragon.getPosition(), "Da");
			} else if (player.getPosition().equals(eagle.getPosition())) {
				maze.setCellValue(player.getPosition(), "Ha");
			} else if (player.getPosition().equals(sword.getPosition())) {
				maze.setCellValue(player.getPosition(), "Ea");
			} else {
				updatePosition(eagle);
				updatePosition(player);
			}			
		} else {
			updatePosition(player);
		}

	}


	/**
	 * Check if the state of the maze is an end state (Player dead or Dragon dead and the player at maze exit).
	 *
	 * @return true, if successful
	 */
	public boolean gameOver() {

		if (dragon.isDead()){
			if (player.getPosition().equals(maze.getExit())) {			/* Player Wins */
				System.out.println("\nCongratulations you Win!!!");
				return true;											
			} else
				return false;											/* Game doesn't end */
		} 

		if (player.isDead()) {											/* Player loses */
			System.out.println("\nGame Over you Lost!!!");
			return true;												
		}

		return false;													/* Game doesn't end */
	}

}
