package maze.logic;

import java.util.Random;
import java.util.Stack;

// TODO: Combine with Maze Generator - Two types of Maze
/**
 * The Class MazeBuilder.
 */
public class MazeBuilder {

	/** The maze. */
	private Maze2 maze;

	/** The visited cells. */
	private boolean visitedCells [][];
	
	/** The aux stack. */
	private Stack<Position> auxStack = new Stack<Position>();

	//===========================================================

	/**
	 * Instantiates a new maze builder.
	 */
	public MazeBuilder()
	{
		this.maze = new Maze2();
	}

	//===========================================================

	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public Maze2 getResult()
	{
		return maze;
	}

	//===========================================================

	/**
	 * Sets the standard maze.
	 */
	public void setStandardMaze()
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

		maze.setBoard(standard);
	}

	//===========================================================

	/**
	 * Sets the random maze.
	 *
	 * @param mazeSize the new random maze
	 */
	public void setRandomMaze(int mazeSize)
	{
		//FIX: Only use odd numbers for mazeSize
		if(mazeSize%2==0)
		{
			mazeSize = mazeSize+1;
		}

		maze.setBoard(new char[mazeSize][mazeSize]);
		visitedCells = new boolean[mazeSize][mazeSize];
		initBoards();

		//FIX: Random generation of maze exit position and current position near exit position
		Position currentPos = new Position(0,0);
		Position exitPos = new Position(0,0);
		generateExitPos(exitPos,currentPos);

		maze.drawPos(exitPos.getLine(),exitPos.getCol(),'S');
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

	/**
	 * Inits the boards.
	 */
	public void initBoards(){

		for(int i=0; i<maze.getBoard().length; i++)
		{
			for(int j=0; j<maze.getBoard().length; j++)
			{
				if((i%2==0) || (j%2==0))
				{
					maze.getBoard()[i][j]='X';
					visitedCells[i][j]=true;
				}
				else
				{
					maze.getBoard()[i][j]=' ';
					visitedCells[i][j]=false;
				}
			}
		}
	}	

	//===========================================================

	/**
	 * Mark visited cell.
	 *
	 * @param markPos the mark pos
	 */
	public void markVisitedCell(Position markPos)
	{
		visitedCells[markPos.getLine()][markPos.getCol()]=true;
	}

	//===========================================================

	/**
	 * Check unvisited cells.
	 *
	 * @return true, if successful
	 */
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

	/**
	 * Check neighbours.
	 *
	 * @param checkPos the check pos
	 * @return true, if successful
	 */
	public boolean checkNeighbours(Position checkPos){
		if(checkPos.getLine()+2<visitedCells.length && visitedCells[checkPos.getLine()+2][checkPos.getCol()]==false)
		{
			return true;
		}
		else if(checkPos.getLine()-2>0 && visitedCells[checkPos.getLine()-2][checkPos.getCol()]==false)
		{
			return true;
		}
		else if(checkPos.getCol()+2<visitedCells.length && visitedCells[checkPos.getLine()][checkPos.getCol()+2]==false)
		{
			return true;
		}
		else if(checkPos.getCol()-2>0 && visitedCells[checkPos.getLine()][checkPos.getCol()-2]==false)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	//===========================================================

	/**
	 * Choose random cell.
	 *
	 * @param currPos the curr pos
	 * @return the position
	 */
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
				randPos.setCoord(currPos.getLine()+2, currPos.getCol());
				break;
			case 2:
				randPos.setCoord(currPos.getLine()-2, currPos.getCol());
				break;
			case 3:
				randPos.setCoord(currPos.getLine(), currPos.getCol()+2);
				break;
			case 4:
				randPos.setCoord(currPos.getLine(), currPos.getCol()-2);
				break;
			default:
				break;
			}

			if(randPos.getLine()>0 && randPos.getLine()<maze.getBoard().length && randPos.getCol()>0 && randPos.getCol()<maze.getBoard().length)
			{
				if(visitedCells[randPos.getLine()][randPos.getCol()]==false)
				{
					endCycle = true;
				}
			}
		}

		return randPos;
	}

	//===========================================================

	/**
	 * Removes the wall.
	 *
	 * @param currentPos the current pos
	 * @param nextPos the next pos
	 */
	public void removeWall(Position currentPos, Position nextPos)
	{
		if(currentPos.getLine()==nextPos.getLine())
		{
			maze.getBoard()[currentPos.getLine()][(currentPos.getCol()+nextPos.getCol())/2]=' ';
		}
		else if(currentPos.getCol()==nextPos.getCol())
		{
			maze.getBoard()[(currentPos.getLine()+nextPos.getLine())/2][currentPos.getCol()]=' ';
		}
	}

	//===========================================================

	/**
	 * Rand unvisited cell.
	 *
	 * @return the position
	 */
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

	/**
	 * Generate exit pos.
	 *
	 * @param exitPos the exit pos
	 * @param currentPos the current pos
	 */
	public void generateExitPos(Position exitPos, Position currentPos)
	{
		Random rand = new Random();

		int exitSide = rand.nextInt(4)+1;
		int exitVal = 0;
		boolean oddNumFound = false;

		while(!oddNumFound)
		{
			exitVal = rand.nextInt(maze.getBoard().length-2)+1;

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
			exitPos.setCoord(maze.getBoard().length-1,exitVal);
			currentPos.setCoord(maze.getBoard().length-2,exitVal);
			break;
		case 3:
			exitPos.setCoord(exitVal,0);
			currentPos.setCoord(exitVal,1);
			break;
		case 4:
			exitPos.setCoord(exitVal,maze.getBoard().length-1);
			currentPos.setCoord(exitVal, maze.getBoard().length-2);
			break;
		default:
			break;
		}

	}
}