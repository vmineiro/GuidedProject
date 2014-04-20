/*
 * Position
 */
package maze.logic;

import java.io.Serializable;

import maze.logic.Character.Direction;


/**
 * The Class Position.
 */
public class Position implements Serializable {
	
	/** The line index. */
	private int line;
	
	/** The column index. */
	private int col;
	
	
	/**
	 * Instantiates a new position.
	 *
	 * @param line the line index
	 * @param col the column index
	 */
	public Position(int line, int col) {
		this.line = line;
		this.col = col;
	}
	
	
	/**
	 * Set new coordinates.
	 *
	 * @param nLin the line index
	 * @param nCol the column index
	 */
	public void setCoord(int nLin, int nCol){
		this.line = nLin;
		this.col = nCol;
	}
	
	
	/**
	 * Gets the line index.
	 *
	 * @return the line
	 */
	public int getLine() {
		return line;
	}
	
	
	/**
	 * Gets the column index.
	 *
	 * @return the col
	 */
	public int getCol() {
		return col;
	}
	
	
	/**
	 * Move.
	 *
	 * @param dir the dir
	 */
	public void move(Direction dir){
		
		switch (dir){
		case LEFT:
			col--;
			break;
		case DOWN:
			line++;
			break;
		case RIGHT:
			col++;
			break;
		case UP:
			line--;
			break;
		default:
			break;
		}
	
	}
	
	
	/**
	 * Get the upper position.
	 *
	 * @return the position
	 */
	public Position upperPosition(){
		
		Position temp = new Position (line,col);
		temp.move(Direction.UP);
		
		return temp;
		
	}
	
	
	/**
	 * Get the  bottom position.
	 *
	 * @return the position
	 */
	public Position bottomPosition(){
		
		Position temp = new Position (line,col);
		temp.move(Direction.DOWN);
		
		return temp;
		
	}
	
	
	/**
	 * Get the left position.
	 *
	 * @return the position
	 */
	public Position leftPosition(){
		
		Position temp = new Position (line,col);
		temp.move(Direction.LEFT);
		
		return temp;
		
	}
	
	
	/**
	 * Get the right position.
	 *
	 * @return the position
	 */
	public Position rightPosition(){
		
		Position temp = new Position (line,col);
		temp.move(Direction.RIGHT);
		
		return temp;
		
	}
	
	
	/**
	 * Compare this position with Position pos1 and if the line index and the column index have the same value return true.
	 *
	 * @param pos1 the pos1
	 * @return true, if successful
	 */
	public boolean equals(Position pos1){
		
		if (this.line == pos1.getLine() && this.col == pos1.getCol()) return true;
		
		return false;
		
	}
	
	
	/**
	 * Prints the position. Used for debugging
	 */
	public void printPosition(){
		System.out.println("(" + line + ", " + col + ")");
	}
}
