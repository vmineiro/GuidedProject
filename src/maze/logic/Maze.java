package maze.logic;

import java.util.Random;
import java.util.Stack;


public class Maze {
	
	private char board [][];
	private boolean visitedCells [][];
	private Stack<Position> auxStack = new Stack<Position>();
	
	//===========================================================
	
	public Maze()
	{
		
	}
	
	//===========================================================
	
	public void generateMaze(int mazeSize){
		
		//FIX: Only use odd numbers for mazeSize
		if(mazeSize%2==0)
		{
			mazeSize = mazeSize+1;
		}
		
		board = new char[mazeSize][mazeSize];
		visitedCells = new boolean[mazeSize][mazeSize];
		initBoards();
		
		//FIX: Random generation of maze exit position and current position near exit position
		Position currentPos = new Position(0,0);
		Position exitPos = new Position(0,0);
		generateExitPos(exitPos,currentPos);
	
		drawPos(exitPos.getLinPos(),exitPos.getColPos(),'S');
		markVisitedCell(currentPos);
		
		Position nextPos;
		
		while(checkUnvisitedCells())
		{
			if(checkNeighbours(currentPos))
			{
				nextPos = chooseRandomCell(currentPos);
				removeWall(currentPos,nextPos);
				auxStack.push(currentPos);
				currentPos = nextPos;
				markVisitedCell(currentPos);
			}
			else if(!auxStack.empty())
			{
				currentPos = auxStack.pop();
			}
			else
			{
				currentPos = randUnvisitedCell();
				markVisitedCell(currentPos);
			}
		}
		
		
	}
	
	//===========================================================
	
	public void standardMaze()
	{
		char [][] standard = {
				{'X','X','X','X','X','X','X','X','X','X'},
				{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X',' ',' ',' ',' ',' ',' ','X',' ','S'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X',' ','X','X',' ','X',' ','X',' ','X'},
				{'X',' ','X','X',' ',' ',' ',' ',' ','X'},
				{'X','X','X','X','X','X','X','X','X','X'}
		};
		
		board = standard;		
	}

	//===========================================================
	
	public void initBoards(){
		
		for(int i=0; i<board.length; i++)
		{
			for(int j=0; j<board.length; j++)
			{
				if((i%2==0) || (j%2==0))
				{
					board[i][j]='X';
					visitedCells[i][j]=true;
				}
				else
				{
					board[i][j]=' ';
					visitedCells[i][j]=false;
				}
			}
		}
	}
	
	//===========================================================
	
	public void markVisitedCell(Position markPos){
		visitedCells[markPos.getLinPos()][markPos.getColPos()]=true;
	}
	
	//===========================================================
	
	public boolean checkUnvisitedCells(){
		for(int i=0; i<visitedCells.length; i++){
			for(int j=0; j<visitedCells.length; j++){
				if(visitedCells[i][j]==false)
				{
					return true;
				}
			}
		}
		return false;	
	}
	
	//===========================================================
	
	public boolean checkNeighbours(Position checkPos){
		if(checkPos.getLinPos()+2<visitedCells.length && visitedCells[checkPos.getLinPos()+2][checkPos.getColPos()]==false)
		{
			return true;
		}
		else if(checkPos.getLinPos()-2>0 && visitedCells[checkPos.getLinPos()-2][checkPos.getColPos()]==false)
		{
			return true;
		}
		else if(checkPos.getColPos()+2<visitedCells.length && visitedCells[checkPos.getLinPos()][checkPos.getColPos()+2]==false)
		{
			return true;
		}
		else if(checkPos.getColPos()-2>0 && visitedCells[checkPos.getLinPos()][checkPos.getColPos()-2]==false)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//===========================================================
	
	public Position chooseRandomCell(Position currPos)
	{
		Random rand = new Random();
		Position randPos = new Position(0,0);
		boolean endCycle = false;
		
		while(!endCycle)
		{
			int randCell = rand.nextInt(4)+1;
			
			switch(randCell) 
			{
				case 1:
					randPos.setCoord(currPos.getLinPos()+2, currPos.getColPos());
					break;
				case 2:
					randPos.setCoord(currPos.getLinPos()-2, currPos.getColPos());
					break;
				case 3:
					randPos.setCoord(currPos.getLinPos(), currPos.getColPos()+2);
					break;
				case 4:
					randPos.setCoord(currPos.getLinPos(), currPos.getColPos()-2);
					break;
				default:
					break;
			}
			
			if(randPos.getLinPos()>0 && randPos.getLinPos()<board.length && randPos.getColPos()>0 && randPos.getColPos()<board.length)
			{
				if(visitedCells[randPos.getLinPos()][randPos.getColPos()]==false)
				{
					endCycle = true;
				}
			}
		}
		
		return randPos;
	}
	
	//===========================================================
	
	public void removeWall(Position currentPos, Position nextPos)
	{
		if(currentPos.getLinPos()==nextPos.getLinPos())
		{
			board[currentPos.getLinPos()][(currentPos.getColPos()+nextPos.getColPos())/2]=' ';
		}
		else if(currentPos.getColPos()==nextPos.getColPos())
		{
			board[(currentPos.getLinPos()+nextPos.getLinPos())/2][currentPos.getColPos()]=' ';
		}
	}
	
	//===========================================================
	
	public Position randUnvisitedCell()
	{
		Position unvisitedCell = new Position(0,0);
		
		for(int i=1; i<visitedCells.length; i=i+2)
		{
			for(int j=1; j<visitedCells.length; j=j+2)
			{
				if(visitedCells[i][j]==false)
				{
					return new Position(i,j);
				}
			}
		}
		
		return unvisitedCell;
	}
	
	//===========================================================
	
	public void generateExitPos(Position exitPos, Position currentPos)
	{
		Random rand = new Random();
		
		int exitSide = rand.nextInt(4)+1;
		int exitVal = 0;
		boolean oddNumFound = false;
		
		while(!oddNumFound)
		{
			exitVal = rand.nextInt(board.length-2)+1;
			
			if(exitVal%2!=0)
			{
				oddNumFound = true;
			}
		}
		
		switch (exitSide) 
		{
			case 1:
				exitPos.setCoord(0,exitVal);
				currentPos.setCoord(1,exitVal);
				break;
			case 2:
				exitPos.setCoord(board.length-1,exitVal);
				currentPos.setCoord(board.length-2,exitVal);
				break;
			case 3:
				exitPos.setCoord(exitVal,0);
				currentPos.setCoord(exitVal,1);
				break;
			case 4:
				exitPos.setCoord(exitVal,board.length-1);
				currentPos.setCoord(exitVal, board.length-2);
				break;
			default:
				break;
		}
		
	}
	
	//===========================================================
	
	public char [][] getMaze()
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
	
	public int getMazeSideSize()
	{
		return board.length;
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
