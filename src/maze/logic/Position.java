package maze.logic;

// TODO: Auto-generated Javadoc
/**
 * The Class Position.
 */
public class Position {

	/** The line. */
	private int line;
	
	/** The col. */
	private int col;
	
	/**
	 * Instantiates a new position.
	 *
	 * @param line the line
	 * @param col the col
	 */
	public Position(int line, int col) {
		this.line = line;
		this.col = col;
	}
	
	/**
	 * Set a new position.
	 *
	 * @param nLin the n lin
	 * @param nCol the new column
	 */
	public void setCoord(int nLin, int nCol){
		this.line = nLin;
		this.col = nCol;
	}
	
	/**
	 * Gets the line.
	 *
	 * @return the line
	 */
	public int getLine() {
		return line;
	}
	
	/**
	 * Gets the col.
	 *
	 * @return the col
	 */
	public int getCol() {
		return col;
	}
	
	/**
	 * Move up.
	 */
	public void moveUp(){
		line--;
	}
	
	/**
	 * Move down.
	 */
	public void moveDown(){
		line++;
	}
	
	/**
	 * Move left.
	 */
	public void moveLeft(){
		col--;
	}
	
	/**
	 * Move right.
	 */
	public void moveRight(){
		col++;
	}
	
	/**
	 * Upper position.
	 *
	 * @return the position
	 */
	public Position upperPosition(){
		Position temp = new Position (line,col);
		temp.moveUp();
		return temp;
	}
	
	/**
	 * Bottom position.
	 *
	 * @return the position
	 */
	public Position bottomPosition(){
		Position temp = new Position (line,col);
		temp.moveDown();
		return temp;
	}
	
	/**
	 * Left position.
	 *
	 * @return the position
	 */
	public Position leftPosition(){
		Position temp = new Position (line,col);
		temp.moveLeft();
		return temp;
	}
	
	/**
	 * Right position.
	 *
	 * @return the position
	 */
	public Position rightPosition(){
		Position temp = new Position (line,col);
		temp.moveRight();
		return temp;
	}
	
	/**
	 * Equals.
	 *
	 * @param pos1 the pos1
	 * @return true, if successful
	 */
	public boolean equals(Position pos1){
		if (this.line == pos1.getLine() && this.col == pos1.getCol()) return true;
		return false;
	}
	
	/**
	 * Prints the position.
	 */
	public void printPosition(){
		System.out.println("(" + line + ", " + col + ")");
	}

}
