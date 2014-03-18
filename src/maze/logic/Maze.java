/*
 * 
 */
package maze.logic;

import java.util.List;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class Maze.
 */
public class Maze {

	/** The maze exit. */
	private static Position mazeExit;

	/** The maze. */
	private static char maze [][] = {
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

	private static boolean[][] visitedMaze;

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
	 * @param pos the position
	 * @param value the value
	 */
	public void setCellValue(Position pos, char value){
		maze[pos.getLine()][pos.getCol()] = value;
	}	

	/**
	 * Change the value of the cell with the line "line" and column "col" to the value ' ' 
	 *  
	 *
	 * @param pos the pos
	 */
	public void clearCell(Position pos){
		maze[pos.getLine()][pos.getCol()] = ' ';
	}

	/**
	 * Returns the value of the cell with the line "line" and column "col".
	 *
	 * @param pos the pos
	 * @return the position value
	 */
	public char getPositionValue(Position pos){
		return maze[pos.getLine()][pos.getCol()];
	}

	/**
	 * Check if the cell with the line "line" and column "col" is valid to be occupied by the dragon/player.
	 *
	 * @param pos the position
	 * @return true, if successful
	 */
	public boolean cellIsEmpty(Position pos){
		if (maze[pos.getLine()][pos.getCol()] == ' ') return true;
		else if (maze[pos.getLine()][pos.getCol()] == 'E') return true;
		else return false;
	}

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

	/**
	 * Generate a random square maze
	 * 
	 * @param n number of columns and lines of the maze
	 * @return a valid maze
	 * */
	public static char[][] generateMaze(int n){
		/* Initialize an empty Maze */
		maze = new char[n][n];
		visitedMaze = new boolean[n][n];

		/* Fill maze positions as Wall  */
		fillMaze(maze,visitedMaze);

		/* Generate the Maze Exit */
		mazeExit = generateMazeExit(n);
		
		/* Set the Maze Exit */
		maze[mazeExit.getLine()][mazeExit.getCol()] = 'S';
		
		/* Generate maze paths */
		visitedMaze[mazeExit.getLine()][mazeExit.getCol()] = true;
		if (mazeExit.getLine() == 0) 
			maze = generatePath(mazeExit.bottomPosition());
		else if (mazeExit.getLine() == n-1)
			maze= generatePath(mazeExit.upperPosition());
		else if (mazeExit.getCol() == 0)
			maze = generatePath(mazeExit.rightPosition());
		else 
			maze = generatePath(mazeExit.leftPosition());
			
		return maze;
	}
	
	private static boolean unvisitedCells(boolean[][] visitedMaze) {
		for (boolean[] line : visitedMaze){
			for (boolean cell : line){
				if (!cell) return true;
			}
		}
		return false;
	}

	private static char[][] generatePath(Position pos) {
		visitedMaze[pos.getLine()][pos.getLine()] = true;
		while (unvisitedCells(visitedMaze)){
			int nearCells = unvisitedCellNear(pos);
			if (nearCells > 0){
				
			}
			
		}
		return maze;
	}

	private static int unvisitedCellNear(Position pos) {
		int line = pos.getLine();
		int col = pos.getCol();
		int nCells = 0;
		if (!visitedMaze[line][col-1]) nCells++;
		if (!visitedMaze[line+1][col]) nCells++;
		if (!visitedMaze[line][col+1]) nCells++;
		if (!visitedMaze[line+1][col]) nCells++;
		return nCells;
	}

	/**
	 * Fill all the cells from a given maze with 'X' value
	 * 
	 * @param maze empty maze to fill
	 * */
	private static void fillMaze(char[][] maze, boolean [][] visited){
		int n = maze.length;
		for (int i = 0; i < n; i++){
			for (int j = 0; j< n; j++) {
				maze[i][j] = 'X';
				visited[i][j] = false;
			}
		}
	}
	
	/**
	 * Generate a random maze exit
	 * 
	 * @param n dimension of maze
	 * @return a valid maze exit
	 * */
	public static Position generateMazeExit(int n){
		Random line = new Random();
		Random column = new Random();
		int lineExit, colExit;

		lineExit = line.nextInt(n);
		if (lineExit == 0 || lineExit == n-1)
			colExit = column.nextInt(n-2)+1;
		else
			colExit = column.nextInt(2)*(n-1);
		
		mazeExit = new Position(lineExit,colExit);
	
		return mazeExit;
	}
	
	

	
}
