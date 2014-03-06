package maze.logic;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Maze.
 */
public class Maze {
	
	/** The maze exit. */
	private Position mazeExit;
	
	/** The maze. */
	private char maze [][] = {
		{'X','X','X','X','X','X','X','X','X','X'},
		{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
		{'X',' ','X','X',' ','X',' ','X',' ','X'},
		{'X',' ','X','X',' ','X',' ','X',' ','X'},
		{'X',' ','X','X',' ','X',' ','X',' ','X'},
		{'X',' ',' ',' ',' ',' ',' ','X',' ','X'},
		{'X',' ','X','X',' ','X',' ','X',' ','X'},
		{'X',' ','X','X',' ','X',' ','X',' ','X'},
		{'X',' ','X','X',' ',' ',' ',' ',' ','X'},
		{'X','X','X','X','X','X','X','X','X','X'}
		};	
	
	/**
	 * Instantiates a new maze.
	 */
	public Maze(){
		// set maze exit
		//setCellValue(mazeExit[0], mazeExit[1], 'S');
		mazeExit = new Position(5, 9);
		setCellValue(mazeExit, 'S');
	}
	
	/**
	 * Return the board - It isn't used .
	 *
	 * @return the maze
	 */
	public char[][] getMaze(){
		return maze;
	}
	
	/**
	 * Change the value of the cell with the line "line" and column "col" to the value "value".
	 *
	 * @param pos the pos
	 * @param value the value
	 */
//	public void setCellValue(int line, int col, char value){
//		maze[line][col] = value;
//	}
	public void setCellValue(Position pos, char value){
		maze[pos.getLine()][pos.getCol()] = value;
	}	
	
	/**
	 * Change the value of the cell with the line "line" and column "col" to the value ' ' 
	 *  
	 *
	 * @param pos the pos
	 */
//	public void clearCell(int line, int col){
//		maze[line][col] = ' ';
//	}
	public void clearCell(Position pos){
	maze[pos.getLine()][pos.getCol()] = ' ';
}
	
	/**
	 * Returns the value of the cell with the line "line" and column "col".
	 *
	 * @param pos the pos
	 * @return the position value
	 */
//	public char getPositionValue(int line,int col){
//		return maze[line][col];
//	}
	public char getPositionValue(Position pos){
		return maze[pos.getLine()][pos.getCol()];
	}
	
	/**
	 * Check if the cell with the line "line" and column "col" is valid to be occupied by the dragon/player.
	 *
	 * @param pos the pos
	 * @return true, if successful
	 */
//	public boolean cellIsEmpty(int line,int col){
//		if (maze[line][col] == ' ') return true;
//		else if (maze[line][col] == 'E') return true;
//		else return false;
//	}
	public boolean cellIsEmpty(Position pos){
	if (maze[pos.getLine()][pos.getCol()] == ' ') return true;
	else if (maze[pos.getLine()][pos.getCol()] == 'E') return true;
	else return false;
}
	
	
//	public int getExitLine(){
//		return mazeExit[0];
//	}
//	
//	public int getExitCol(){
//		return mazeExit[1];
//	}
	/**
 * Return Cave Exit Position.
 *
 * @return the exit
 */
	public Position getExit(){
		return mazeExit;
	}
	
	/**
	 * Prints the maze.
	 */
	public void printMaze() {
		for (int i=0; i<10; i++) {
			for (int j=0; j<10;j++) {
				System.out.print(maze[i][j]);
			}
			System.out.println();
		}
	}
	
//	public List<Position> getEmptyCells(){
//		
//		List <Position> emptyCells = null;
//		
//		for (int i=0; i < maze.length;i++) {
//			for (int j=0; j < maze[0].length;j++){
//				if (getPositionValue(i, j) == ' ') {
//					Position temp = new Position(i,j);
//					emptyCells.add(temp);
//				}
//			}
//		}
//		return emptyCells;
//	}
	
//	public List<Position> getEmptyCellsNextTo(int line, int col){
//		
//		List <Position> emptyCells = null;
//		
//		for (int i=0; i < maze.length;i++) {
//			for (int j=0; j < maze[0].length;j++){
//				if (getPositionValue(i, j) == ' ') {
//					Position temp = new Position(i,j);
//					emptyCells.add(temp);
//				}
//			}
//		}
//		return emptyCells;
//	}
	
	
}
