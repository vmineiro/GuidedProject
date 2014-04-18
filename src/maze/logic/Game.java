package maze.logic;


import maze.logic.Dragon.Mode;
import maze.logic.Character.Direction;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;


/**
 * The Class Game.
 */
public class Game implements Serializable{

	/** The maze. */
	private Maze maze;

	/** The player. */
	private Hero player;

	/** The sword. */
	private Sword sword;

	/** The dragon. */
	private ArrayList<Dragon> dragons;

	/** All */
	private int dragonsAlive;

	/** The Eagle. */
	private Eagle eagle;


	/**
	 * Game Constructor: initializes a maze, one player, one dragon and a sword.
	 */
	public Game() {
		maze = new Maze();
		player = new Hero(new Position(0,0));
		dragons = new ArrayList<Dragon>();
		dragonsAlive = 0;
		sword = new Sword(new Position(0,0));
		eagle = new Eagle(player.getPosition());
	}


	/**
	 * Initializes the game according to the selected settings in the interface.
	 *
	 * @param mazeSize the option selected (standard = 0  or random maze = size of maze)
	 * @param mode the mode of the dragon
	 * @param nDragons the n dragons
	 * @param builder the builder
	 */
	public void initGame(int mazeSize, int mode, int nDragons, int builder) {

		if (mazeSize == 0){

			/* initialize a temporary maze to be build */
			MazeGenerator tempMaze;

			/* set the standard maze */
			tempMaze = new MazeGenerator();		
			maze = tempMaze.getMaze();

			/* set the positions of character and the dragon mode */
			player.setPosition(new Position(1, 1));
			sword.setPosition(new Position(8, 1));
			Dragon dragon = new Dragon(new Position(3, 1), Mode.STATIC);
			dragon.setMode(Mode.STATIC);
			addDragon(dragon);
			dragonsAlive = dragons.size();

		} else {

			if (builder == 1) {

				/* initialize a temporary maze to be build */
				MazeBuilder tempMaze = new MazeBuilder();

				/* set a random maze with size of "mazeSize" */
				tempMaze.setRandomMaze(mazeSize);

				maze = tempMaze.getResult();

			} else {

				/* initialize a temporary maze to be build */
				MazeGenerator tempMaze;

				/* set the standard maze */
				tempMaze = new MazeGenerator(mazeSize);		
				maze = tempMaze.getMaze();

			}

			/* set the positions of character and the dragon mode */
			player.setPosition(maze.randomPosition());

			/* set the symbol of character in the maze to ensure an empty cell between the Hero and the dragon*/
			updatePosition(player);
			sword.setPosition(maze.randomPosition());
			updatePosition(sword);

			/* create dragons */
			for (int i = 0; i< nDragons; i++){

				Dragon dragon = new Dragon(new Position(0, 0), Mode.STATIC);
				dragon.setPosition(maze.randomDragonPosition());

				/* set the dragon mode according to the selected option in the interface */
				if (mode == 1) {
					dragon.setMode(Mode.STATIC);
				} else if (mode == 2) {
					dragon.setMode(Mode.DYNAMIC);
				} else {
					dragon.setMode(Mode.MIXED);
				}
				updatePosition(dragon);

				addDragon(dragon);

			}

			dragonsAlive = dragons.size();

		}
	}


	/**
	 * Add a dragon to the game.
	 * 
	 * @param dragon new dragon to be added to the game.
	 */
	public void addDragon(Dragon dragon) {
		dragons.add(dragon);	
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
	 * Gets the dragons.
	 *
	 * @return the dragons
	 */
	public ArrayList<Dragon> getDragons(){
		return dragons;
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
	 * Sets the player.
	 *
	 * @param player2 the new player
	 */
	public void setPlayer(Hero player2) {
		player = player2;	
	}


	/**
	 * Sets the maze.
	 *
	 * @param maze2 the new maze
	 */
	public void setMaze(Maze maze2) {

		maze = maze2;

	}


	/**
	 * Sets the dragons.
	 *
	 * @param dragons2 the new dragons
	 */
	public void setDragons(ArrayList<Dragon> dragons2) {
		dragons = dragons2;		
	}


	/**
	 * Sets the eagle.
	 *
	 * @param eagle2 the new eagle
	 */
	public void setEagle(Eagle eagle2) {
		eagle = eagle2;		
	}


	/**
	 * Sets the sword.
	 *
	 * @param sword2 the new sword
	 */
	public void setSword(Sword sword2) {
		sword = sword2;		
	}


	/**
	 * Update the positions of all characters.
	 */
	public void updatePositions() {

		for (Dragon dragon : dragons){
			updatePosition(dragon);
		}

		if (sword.isActive()) {

			if (maze.getPositionValue(sword.getPosition()).equals("D "))
				maze.setCellValue(sword.getPosition(), "F ");
			else
				updatePosition(sword);		

		}

		if (eagle.isActive()) {

			if (maze.getPositionValue(eagle.getPosition()).equals("D ") && !eagle.isReturning()){
				/* Eagle over an Awaken Dragon */
				maze.setCellValue(eagle.getPosition(), "Da");
			} else if (maze.getPositionValue(eagle.getPosition()).equals("d ") && !eagle.isReturning()){
				/* Eagle over an Asleep Dragon */
				maze.setCellValue(eagle.getPosition(), "da");
			} else if (maze.getPositionValue(eagle.getPosition()).equals("XX") && !eagle.isReturning()){
				/* Eagle over a Wall */
				maze.setCellValue(eagle.getPosition(), "Xa");
			} else if (maze.getPositionValue(eagle.getPosition()).equals("F ")) {
				/* Eagle over a Dragon the Sword */
				maze.setCellValue(eagle.getPosition(), "Fa");
			} else if (player.getPosition().equals(eagle.getPosition())) {
				/* Eagle over the Sword */
				maze.setCellValue(player.getPosition(), "Ha");
			} else if (eagle.getPosition().equals(sword.getPosition())) {
				/* Eagle over the Sword */
				maze.setCellValue(eagle.getPosition(), "Ea");
			} else {
				updatePosition(eagle);
				updatePosition(player);
			}	

		} else {
			updatePosition(player);
		}

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
	 * Move Hero in a given direction.
	 * 
	 * @param dir The move direction.
	 */
	public void movePlayer(Direction dir) {
		if (checkPlayerMove(player.getPosition(dir))) {

			maze.clearCell(player.getPosition());
			player.move(dir);
			pickSword();

			/* check if the dragon kill any Character (hero or eagle) or if the dragon dies. */
			checkKill();

			updatePosition(player);

		}

	}


	/**
	 * Pick the Sword.
	 */
	public void pickSword() {

		if (!sword.isActive()) return; 

		if (player.getPosition().equals(sword.getPosition())) {

			player.getArmed();
			sword.picked();

		}

	}


	/**
	 * Check if the player move is valid or not.
	 *
	 * @param pos the position where player want to move
	 * @return true, if is a valid position
	 */
	public boolean checkPlayerMove(Position pos) {

		if (maze.getPositionValue(pos).equals("XX")) return false;

		if (!player.isArmed()) {

			if (pos.equals(maze.getExit())){		

				/* Message if the player tries to exit without getting the sword and kill the dragon */

				//System.out.println("\nYou need to get the sword (E) and kill all the Dragons (D / d)");
				return false;

			}

			if (maze.getPositionValue(pos).equals("d ")) {
				//System.out.println("\nYou cannot pass a dragon without killing it!");
				return false;
			}


		} else {

			/* Message if the player tries to exit without getting the sword and kill the dragon */

			if (pos.equals(maze.getExit())){

				if (dragonsAlive > 0) {
					//System.out.println("\nYou need to kill all the Dragons (D / d)");
					return false;
				} else
					return true;
			}

		}		

		return true;

	}


	/**
	 * Move each dragon in the game.
	 */
	public void dragonsMove(){

		for (Dragon dragon : dragons){

			dragonMove(dragon);

		}

		if (dragonsAlive == 0) {
			maze.setCellValue(maze.getExit(), "  ");
		}
		/* check if the dragon kill any Character (hero or eagle) or if the dragon dies. */
		checkKill();	
	}


	/**
	 * Dragon strategy management 
	 * Check if the left, bottom, right and upper cells are valid cells and select a possible move.
	 *
	 * @param dragon the dragon
	 */
	public void dragonMove(Dragon dragon) {

		/* if dragon is STATIC won't move */

		if (dragon.getMode() == Mode.STATIC  || !dragon.isActive()) return;

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
				moveDragon(dragon,Direction.LEFT);
				validMove = true;
				break;
			case 1:
				moveDragon(dragon,Direction.DOWN);
				validMove = true;
				break;		
			case 2:
				moveDragon(dragon,Direction.RIGHT);
				validMove = true;
				break;
			case 3:
				moveDragon(dragon,Direction.UP);
				validMove = true;
				break;
			case 4:
				validMove = true;
			default:
				break;
			}
		}	

	}


	/**
	 * Dragon move.
	 *
	 * @param dragon the dragon
	 * @param dir the dir
	 */
	public void moveDragon(Dragon dragon, Direction dir) {

		if (maze.cellIsEmpty(dragon.getPosition(dir))) {

			maze.clearCell(dragon.getPosition());
			dragon.move(dir);

			updatePosition(dragon);

		}

	}


	/**
	 * Eagle launch methods.
	 * */
	public void eagleLaunched() {

		if (player.eagleLaunched() || player.isArmed()) return;

		player.launchEagle();

		eagle.setPosition(player.getPosition());
		eagle.getSword(sword.getPosition());

		eagleMove();

	}


	/**
	 * Eagle move.
	 */
	public void eagleMove() {

		if (!eagle.isActive()) return;

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
		if (player.isArmed()){
			for (Dragon dragon : dragons){
				if (!dragon.isDead()){
					if (player.getPosition().equals(dragon.getPosition(Direction.LEFT)) ||
							player.getPosition().equals(dragon.getPosition(Direction.DOWN)) ||
							player.getPosition().equals(dragon.getPosition(Direction.RIGHT)) ||
							player.getPosition().equals(dragon.getPosition(Direction.UP))) {
						dragon.die();
						dragonsAlive--;
					}
				}		
			}
		}

		/* Dragons kill */
		for (Dragon dragon : dragons){
			if (!dragon.isDead()){
				if (!dragon.isAsleep()){

					/* Dragon kills the Hero */
					if (!player.isArmed()) {
						if (player.getPosition().equals(dragon.getPosition(Direction.LEFT)) ||
								player.getPosition().equals(dragon.getPosition(Direction.DOWN)) ||
								player.getPosition().equals(dragon.getPosition(Direction.RIGHT)) ||
								player.getPosition().equals(dragon.getPosition(Direction.UP))) {
							player.die();
						}
					}

					/* Dragon kills the Eagle */
					if (eagle.isActive() && !eagle.isOnWay() && !eagle.isReturning()){
						if (eagle.getPosition().equals(dragon.getPosition()) ||
								eagle.getPosition().equals(dragon.getPosition(Direction.LEFT)) ||
								eagle.getPosition().equals(dragon.getPosition(Direction.DOWN)) ||
								eagle.getPosition().equals(dragon.getPosition(Direction.RIGHT)) ||
								eagle.getPosition().equals(dragon.getPosition(Direction.UP))
								) {
							eagle.die();
							sword.droped(eagle.getPosition());
						}
					}

				}
			}
		}

		updatePositions();

	}


	/**
	 * Check if the state of the maze is an end state (Player dead or Dragon dead and the player at maze exit).
	 *
	 * @return true, if successful
	 */
	public boolean gameOver() {

		if (player.isDead()) {										/* Player loses */
			return true;												
		}

		for (Dragon dragon : dragons){
			if (!dragon.isDead()) return false;						/* Game doesn't end */
		}

		if (player.getPosition().equals(maze.getExit())) {			/* Player Wins */
			return true;											
		} else
			return false;
	}


	/**
	 * Sets the game.
	 *
	 * @param tempGame the new game
	 */
	public void setGame(Game tempGame) {

		setMaze(tempGame.getMaze());
		setPlayer(tempGame.getPlayer());
		setDragons(tempGame.getDragons());
		
		int numDragonsAlive = 0;
		
		for (Dragon dragon : dragons) {
			if (!dragon.isDead()){
				numDragonsAlive++;
			}
		}
		
		dragonsAlive = numDragonsAlive;
		
		setEagle(tempGame.getEagle());
		setSword(tempGame.getSword());

		updatePositions();

	}


	public void saveGame(String path) throws IOException {


		FileOutputStream fileOut = new FileOutputStream(path);
		ObjectOutputStream os = new ObjectOutputStream(fileOut);

		/* Write the game in a file */
		os.writeObject(this);

		fileOut.close();
		os.close();	

	}


	public void loadGame(String path) throws IOException, ClassNotFoundException  {

		FileInputStream fileIn = new FileInputStream(path);
		ObjectInputStream is = new ObjectInputStream(fileIn);

		/* load the saved game in the file to the object tempGame */
		Game tempGame = (Game) is.readObject();

		is.close();
		fileIn.close();

		/* Change the Current Game */
		setGame(tempGame);

	}


	public int getDragonsAlive() {
		return dragonsAlive;
	}

}
