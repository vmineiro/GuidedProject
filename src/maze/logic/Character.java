package maze.logic;

// TODO: Auto-generated Javadoc
/**
 * The Class Character.
 */
public class Character {
	

	
	/** The position. */
	private Position position;
	
	/** The active. */
	private boolean active;
	
	/**
	 * Instantiates a new character.
	 *
	 * @param line the line of the maze
	 * @param col the column of the maze
	 */
	public Character(int line, int col) {
		position = new Position(line,col);
		active = true;
	}
	
	/**
	 * Gets the position.
	 *
	 * @return the position of the character
	 */
	public Position getPosition(){
		return position;
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

}
