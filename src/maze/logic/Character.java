/*
 * Character
 */
package maze.logic;

import java.io.Serializable;


/**
 * The Class Character.
 */
public class Character implements Serializable{

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
	 *
	 * @param dir the direction
	 * @return the position on Direction dir
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
		position.move(dir);
	}

}
