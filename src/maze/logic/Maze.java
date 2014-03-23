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

	/** The maze. */
	private static char maze [][];
	
	/** The maze exit. */
	private Position mazeExit;
	
	public Maze(){
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
	 * Set the board - It isn't used .
	 *
	 * @return the maze
	 */
	public void setMaze(char[][] lab){
		maze = lab;
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
	 * Return Cave Exit Position.
	 *
	 * @return the exit
	 */
	public void setExit(Position pos){
		mazeExit = pos;
	}

	/**
	 * Prints the maze.
	 */
	public void printMaze() {
		int n = maze.length;
		for (int i=0; i<n; i++) {
			for (int j=0; j<n;j++) {
				System.out.print(maze[i][j]);
			}
			System.out.println();
		}
	}

	public Position randomPosition() {
		Random number = new Random();
		int mazeSize = maze.length;
		int linePos, colPos;
		boolean validPos = false;
		Position temp;
		
		do {
			linePos = number.nextInt(mazeSize-2)+1;
			colPos = number.nextInt(mazeSize-2)+1;
			temp = new Position(linePos,colPos);
			if (getPositionValue(temp) == ' ') validPos = true;
		} while (!validPos);
		
		return temp;
	}

	public Position randomDragonPosition() {
		Position temp;
		boolean validPos = false;
		do {
			temp = randomPosition();
			if (getPositionValue(temp.leftPosition()) != 'H' && getPositionValue(temp.bottomPosition()) != 'H' && getPositionValue(temp.rightPosition()) != 'H' && getPositionValue(temp.upperPosition()) != 'H') validPos = true;
		} while (!validPos);
		return temp;
	}
	
}
