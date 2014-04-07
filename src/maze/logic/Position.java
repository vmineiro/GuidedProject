package maze.logic;

<<<<<<< HEAD
public class Position {
	
	private int linPos;
	private int colPos;
	
	public Position(int linPos, int colPos){
		this.linPos = linPos;
		this.colPos = colPos;
	}
	
	public void setLinPos(int nLin){
		this.linPos = nLin;
	}
	
	public void setColPos(int nCol){
		this.colPos = nCol;
	}
	
	public int getLinPos(){
		return linPos;
	}
	
	public int getColPos(){
		return colPos;
	}
	
	public void setCoord(int nLin, int nCol){
		this.linPos = nLin;
		this.colPos = nCol;
	}
	
=======

/**
 * The Class Position.
 */
public class Position {

	
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
	 * Get the upper position.
	 *
	 * @return the position
	 */
	public Position upperPosition(){
		Position temp = new Position (line,col);
		temp.moveUp();
		return temp;
	}
	
	
	/**
	 * Get the  bottom position.
	 *
	 * @return the position
	 */
	public Position bottomPosition(){
		Position temp = new Position (line,col);
		temp.moveDown();
		return temp;
	}
	
	
	/**
	 * Get the left position.
	 *
	 * @return the position
	 */
	public Position leftPosition(){
		Position temp = new Position (line,col);
		temp.moveLeft();
		return temp;
	}
	
	
	/**
	 * Get the right position.
	 *
	 * @return the position
	 */
	public Position rightPosition(){
		Position temp = new Position (line,col);
		temp.moveRight();
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

>>>>>>> Vitor
}
