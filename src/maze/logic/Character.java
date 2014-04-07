package maze.logic;

<<<<<<< HEAD
public class Character {
	
	private Position positionChar;
	private char symbolChar;
	
	public Character(int linChar, int colChar, char symChar)
	{
		this.positionChar = new Position(linChar,colChar);
		this.symbolChar = symChar;
	}
	
	public int getCharLin()
	{
		return positionChar.getLinPos();
	}
	
	public int getCharCol()
	{
		return positionChar.getColPos();
	}
	
	public void setCharCoord(int nLin, int nCol)
	{
		this.positionChar = new Position(nLin, nCol);
	}
	
	public char getCharSymbol()
	{
		return symbolChar;
	}
	
	public void setCharSymbol(char symChar){
		this.symbolChar = symChar;
	}
	
=======

/**
 * The Class Character.
 */
public class Character {


	/**
	 * The Enumeration of directions.
	 */
	public enum Direction {
		/** The left direction. */ LEFT,
		/** The right direction. */ DOWN,
		/** The down direction. */ RIGHT,
		/** The up direction. */ UP
	};


	/** The position of Character. */
	private Position position;


	/** The status of Character. */
	private boolean active;


	/** The symbol to represent the Character in the Maze. */
	protected String symbol;


	/**
	 * Instantiates a new character.
	 *
	 * @param pos the initial position of character
	 */
	public Character(Position pos) {
		position = pos;
		active = true;
	}


	/**
	 * Set position.
	 *
	 * @param pos the new position
	 */
	public void setPosition(Position pos){
		position = pos;
	}


	/**
	 * Gets the position.
	 * @param dir 
	 *
	 * @return the actual position
	 */
	public Position getPosition(Direction dir){
		switch (dir){
		case LEFT:
			return position.leftPosition();
		case DOWN:
			return position.bottomPosition();
		case RIGHT:
			return position.rightPosition();
		case UP:
			return position.upperPosition();
		default:
			break;
		}
		return position;
	}
	
	
	/**
	 * Gets the position. 
	 *
	 * @return the actual position
	 */
	public Position getPosition(){
		return position;
	}


//	/**
//	 * Gets the left position.
//	 *
//	 * @return the left position of the character
//	 */
//	public Position getLeftPosition(){
//		return position.leftPosition();
//	}
//
//	/**
//	 * Gets the bottom position.
//	 *
//	 * @return the bottom position of the character
//	 */
//	public Position getBottomPosition(){
//		return position.bottomPosition();
//	}
//
//
//	/**
//	 * Gets the right position.
//	 *
//	 * @return the right position of the character
//	 */
//	public Position getRightPosition(){
//		return position.rightPosition();
//	}
//
//
//	/**
//	 * Gets the upper position.
//	 *
//	 * @return the upper position of the character
//	 */
//	public Position getUpperPosition(){
//		return position.upperPosition();
//	}


	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}


	/**
	 * Die.
	 */
	public void die() {
		symbol = "  ";
		setInactive();
	}


	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive(){
		return active;
	}


	/**
	 * Sets character inactive.
	 */
	public void setInactive(){
		active = false;
	}


	/**
	 * Sets character active.
	 */
	public void setActive(){
		active = true;
	}


	/**
	 * Move character in a given position.
	 * 
	 * @param dir the move direction
	 */
	public void move(Direction dir){
		switch (dir){
		case LEFT:
			position.moveLeft();
			break;
		case DOWN:
			position.moveDown();
			break;
		case RIGHT:
			position.moveRight();
			break;
		case UP:
			position.moveUp();
			break;
		default:
			break;
		}
	}


//	/**
//	 * Move up.
//	 */
//	public void moveUp(){
//		position.moveUp();
//	}
//
//
//	/**
//	 * Move down.
//	 */
//	public void moveDown(){
//		position.moveDown();
//	}
//
//
//	/**
//	 * Move left.
//	 */
//	public void moveLeft(){
//		position.moveLeft();
//	}
//
//
//	/**
//	 * Move right.
//	 */
//	public void moveRight(){
//		position.moveRight();
//	}

>>>>>>> Vitor
}
