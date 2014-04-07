package maze.logic;

// TODO: Combine with the Maze
/**
 * The Class Maze2.
 */
public class Maze2 {

	/** The board. */
	private char board [][];

	//===========================================================

	/**
	 * Instantiates a new maze2.
	 */
	public Maze2(){}

	//===========================================================

	/**
	 * Sets the board.
	 *
	 * @param nBoard the new board
	 */
	public void setBoard(char[][] nBoard)
	{
		this.board = nBoard;
	}

	//===========================================================

	/**
	 * Gets the board.
	 *
	 * @return the board
	 */
	public char [][] getBoard()
	{
		return board;
	}

	//===========================================================

	/**
	 * Draw pos.
	 *
	 * @param linDraw the lin draw
	 * @param colDraw the col draw
	 * @param cElem the c elem
	 */
	public void drawPos(int linDraw, int colDraw, char cElem)
	{
		this.board[linDraw][colDraw] = cElem;
	}

	//===========================================================

	/**
	 * Del pos.
	 *
	 * @param nLin the n lin
	 * @param nCol the n col
	 * @return true, if successful
	 */
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

	/**
	 * Mov elem.
	 *
	 * @param oLin the o lin
	 * @param oCol the o col
	 * @param nLin the n lin
	 * @param nCol the n col
	 * @param cElem the c elem
	 */
	public void movElem(int oLin, int oCol, int nLin, int nCol, char cElem)
	{
		if(board[oLin][oCol] != 'X' && board[nLin][nCol] != 'X')
		{
			delPos(oLin, oCol);
			drawPos(nLin, nCol, cElem);

		}
	}

	//===========================================================

	/**
	 * Check pos.
	 *
	 * @param nLin the n lin
	 * @param nCol the n col
	 * @return the char
	 */
	public char checkPos(int nLin, int nCol)
	{
		return board[nLin][nCol];
	}

	//===========================================================

	/**
	 * Prints the board.
	 */
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