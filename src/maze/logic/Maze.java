package maze.logic;

<<<<<<< HEAD
public class Maze {
	
	private char board [][];
	
	//===========================================================
	
	public Maze(){}
	
	//===========================================================
	
	public void setBoard(char[][] nBoard)
	{
		this.board = nBoard;
=======

import java.util.Random;


/**
 * The Class Maze.
 */
public class Maze {

	
	/** The maze. */
	private static String maze [][];
	
	
	/** The maze exit. */
	private Position mazeExit;
	
	
	/**
	 * Instantiates a new maze.
	 */
	public Maze(){
	}

	
	/**
	 * Set the board - Used on Maze Generator.
	 *
	 * @param lab the new maze
	 * @return the maze
	 */
	public void setMaze(String[][] lab){
		maze = lab;
	}
	
	
	/**
	 * Set the board - Used on Maze Generator.
	 *
	 * @param lab the new maze
	 * @return the maze
	 */
	public String[][] getMaze(){
		return maze;
	}
	
	
	/**
	 * Change the value of the cell with the line "line" and column "col" to the value "value".
	 *
	 * @param pos the position
	 * @param value the value
	 */
	public void setCellValue(Position pos, String value){
		maze[pos.getLine()][pos.getCol()] = value;
	}	

	
	/**
	 * Change the value of the cell with the line "line" and column "col" to the value ' '  
	 *
	 * @param pos the position to clear
	 */
	public void clearCell(Position pos){
		maze[pos.getLine()][pos.getCol()] = "  ";
	}

	
	/**
	 * Returns the value of the cell with the line "line" and column "col".
	 *
	 * @param pos the pos
	 * @return the position value
	 */
	public String getPositionValue(Position pos){
		return maze[pos.getLine()][pos.getCol()];
>>>>>>> Vitor
	}
	

<<<<<<< HEAD
	//===========================================================
	
	public char [][] getBoard()
	{
		return board;
	}
	
	//===========================================================
	
	public void drawPos(int linDraw, int colDraw, char cElem)
	{
		this.board[linDraw][colDraw] = cElem;
	}
	
	//===========================================================
	
	public boolean delPos(int nLin, int nCol)
	{
		if(board[nLin][nCol] != 'X')
		{
			this.board[nLin][nCol] = ' ';
			return true;
		}
		else
		{
			return false;
		}
	}

	//===========================================================
	
	public void movElem(int oLin, int oCol, int nLin, int nCol, char cElem)
	{
		if(board[oLin][oCol] != 'X' && board[nLin][nCol] != 'X')
		{
			delPos(oLin, oCol);
			drawPos(nLin, nCol, cElem);

		}
	}
	
	//===========================================================
	
	public char checkPos(int nLin, int nCol)
	{
		return board[nLin][nCol];
=======
	/**
	 * Check if the cell with the line "line" and column "col" is valid to be occupied by the dragon/player.
	 *
	 * @param pos the position
	 * @return true, if successful
	 */
	public boolean cellIsEmpty(Position pos){
		if (maze[pos.getLine()][pos.getCol()].equals("  ")) return true;
		else if (maze[pos.getLine()][pos.getCol()].equals("E ")) return true;
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
	 * @param pos the new exit
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
	

	/**
	 * Pick an empty random position.
	 *
	 * @return a valid position
	 */
	public Position randomPosition() {
		
		Random number = new Random();
		int mazeSize = maze.length;
		int linePos, colPos;
		boolean validPos = false;
		Position temp;													/* initialize a temporary position */
		
		do {
			linePos = number.nextInt(mazeSize-2)+1;						/* pick a random inner line */
			colPos = number.nextInt(mazeSize-2)+1;						/* pick a random inner column */
			temp = new Position(linePos,colPos);
			if (getPositionValue(temp).equals("  ")) validPos = true;	/* compare the value of the maze position */
		} while (!validPos);
		
		return temp;
	}

	
	/**
	 * Pick Random position for the dragon. The dragon can't be place near the player
	 *
	 * @return a valid position for the dragon
	 */
	public Position randomDragonPosition() {
		
		Position temp;															/* initialize a temporary position */
		boolean validPos = false;
		
		do {
			temp = randomPosition();
			if (!getPositionValue(temp.leftPosition()).equals("Ha") &&			/* left position check */
					!getPositionValue(temp.bottomPosition()).equals("Ha") &&	/* bottom position check */
					!getPositionValue(temp.rightPosition()).equals("Ha") &&		/* right position check */
					!getPositionValue(temp.upperPosition()).equals("Ha")){		/* upper position check */
				validPos = true;
			}
		} while (!validPos);
		
		return temp;
>>>>>>> Vitor
	}
	
	//===========================================================
	
	public void printBoard()
	{
		for(int i=0; i<board.length; i++)
		{
			for(int j=0; j<board.length; j++)
			{
				System.out.print(this.board[i][j]);
			}

			System.out.println();
		}
	}
}
