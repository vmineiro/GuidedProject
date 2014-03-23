package maze.logic;

public class Maze2 {

	private char board [][];

	//===========================================================

	public Maze2(){}

	//===========================================================

	public void setBoard(char[][] nBoard)
	{
		this.board = nBoard;
	}

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