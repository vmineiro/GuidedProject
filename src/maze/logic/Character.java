package maze.logic;


/**
 * The Class Character.
 */
public class Character {
	
	
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
	 *
	 * @return the actual position
	 */
	public Position getPosition(){
		return position;
	}

	
	/**
	 * Gets the left position.
	 *
	 * @return the left position of the character
	 */
	public Position getLeftPosition(){
		return position.leftPosition();
	}
	
	/**
	 * Gets the bottom position.
	 *
	 * @return the bottom position of the character
	 */
	public Position getBottomPosition(){
		return position.bottomPosition();
	}
	
	
	/**
	 * Gets the right position.
	 *
	 * @return the right position of the character
	 */
	public Position getRightPosition(){
		return position.rightPosition();
	}
	
	
	/**
	 * Gets the upper position.
	 *
	 * @return the upper position of the character
	 */
	public Position getUpperPosition(){
		return position.upperPosition();
	}

	
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
	 * Move up.
	 */
	public void moveUp(){
		position.moveUp();
	}
	
	
	/**
	 * Move down.
	 */
	public void moveDown(){
		position.moveDown();
	}
	
	
	/**
	 * Move left.
	 */
	public void moveLeft(){
		position.moveLeft();
	}
	
	
	/**
	 * Move right.
	 */
	public void moveRight(){
		position.moveRight();
	}

}
