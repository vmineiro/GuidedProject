package maze.logic;

import java.util.Random;
import java.util.Stack;

// TODO: Auto-generated Javadoc
/**
 * The Class MazeGenerator.
 */
public class MazeGenerator {

	private Maze maze;

	/**
	 * Instantiates a new maze generator.
	 *
	 * @param n the n
	 */
	public MazeGenerator(int n){
		maze = new Maze();
		/* Generate the Maze Exit */
		Position mazeExit = generateMazeExit(n); 
		maze.setExit(mazeExit);
		maze.setMaze(generateMaze(n, mazeExit));
	}

	public MazeGenerator() {
		maze = new Maze();
		String[][] lab = {
				{"XX","XX","XX","XX","XX","XX","XX","XX","XX","XX"},
				{"XX","  ","  ","  ","  ","  ","  ","  ","  ","XX"},
				{"XX","  ","XX","XX","  ","XX","  ","XX","  ","XX"},
				{"XX","  ","XX","XX","  ","XX","  ","XX","  ","XX"},
				{"XX","  ","XX","XX","  ","XX","  ","XX","  ","XX"},
				{"XX","  ","  ","  ","  ","  ","  ","XX","  ","SS"},
				{"XX","  ","XX","XX","  ","XX","  ","XX","  ","XX"},
				{"XX","  ","XX","XX","  ","XX","  ","XX","  ","XX"},
				{"XX","  ","XX","XX","  ","  ","  ","  ","  ","XX"},
				{"XX","XX","XX","XX","XX","XX","XX","XX","XX","XX"}
		};
		maze.setExit(new Position(5, 9));
		maze.setMaze(lab);
	}
	
	public Maze getMaze(){
		return maze;
	}

	/**
	 * Generate a random square maze.
	 *
	 * @param n number of columns and lines of the maze
	 * @return a valid maze
	 */
	public static String[][] generateMaze(int n, Position mazeExit){
		/* Initialize an empty Maze */
		String[][] lab = new String[n][n];

		/* create a bound variable */
		int bound = n-1;

		/* Fill maze positions as Wall  */
		fillMaze(lab, n);

		/* Set the Maze Exit */
		lab[mazeExit.getLine()][mazeExit.getCol()] = "SS";

		/* Generate maze paths */
		//visitedMaze[mazeExit.getLine()][mazeExit.getCol()] = true;
		if (mazeExit.getLine() == 0) 
			generatePath(mazeExit.bottomPosition(),lab,bound);
		else if (mazeExit.getLine() == n-1)
			generatePath(mazeExit.upperPosition(),lab,bound);
		else if (mazeExit.getCol() == 0)
			generatePath(mazeExit.rightPosition(),lab,bound);
		else 
			generatePath(mazeExit.leftPosition(),lab,bound);

		return lab;
	}

	/**
	 * Generate path.
	 *
	 * @param pos the pos
	 * @param lab 
	 * @param bound the bound
	 */
	private static void generatePath(Position pos, String[][] lab, int bound) {

		/* check if the Position is a valid position */
		if(!validPathPosition(pos, lab, bound)) return;

		lab[pos.getLine()][pos.getCol()] = "  ";
		int[] positonsOrders; 
		positonsOrders = generatePositionsOrder();

		Stack<Position> nearPosition = new Stack<Position>();
		for (int i = 0 ;i < 4; i++){
			switch (positonsOrders[i]) {
			case 1:
				nearPosition.push(pos.leftPosition());
				break;
			case 2:
				nearPosition.push(pos.bottomPosition());
				break;
			case 3:
				nearPosition.push(pos.rightPosition());
				break;
			case 4:
				nearPosition.push(pos.upperPosition());
				break;
			default:
				break;
			}			
		}

		while(!nearPosition.empty()){
			Position nearPos = nearPosition.pop();
			generatePath(nearPos,lab,bound);
		}
	}

	/**
	 * Generate positions order.
	 *
	 * @return the int[]
	 */
	public static int[] generatePositionsOrder() {	
		Random randomNr = new Random();
		int randNr;
		int[] positonsOrders = new int[4];
		for (int i=0; i<4; i++){
			do
				randNr = randomNr.nextInt(4)+1;
			while (alreadyExist(positonsOrders, randNr));
			positonsOrders[i] = randNr;
		}
		return positonsOrders;
	}

	/**
	 * Already exist.
	 *
	 * @param positonsOrders the positons orders
	 * @param randNr the rand nr
	 * @return true, if successful
	 */
	private static boolean alreadyExist(int[] positonsOrders, int randNr) {
		for (int i : positonsOrders){
			if (i == randNr) return true;
		}
		return false;
	}

	/**
	 * Valid path position.
	 *
	 * @param pos the pos
	 * @param bound 
	 * @param lab 
	 * @param bound the bound
	 * @return true, if successful
	 */
	private static boolean validPathPosition(Position pos, String[][] lab, int bound) {
		int col = pos.getCol();
		int lin = pos.getLine();

		// check if the position is a bound of the maze
		if (lin == 0 || col == 0 || lin == bound || col == bound) return false;

		// check if the position is repeated
		if (lab[lin][col].equals("  ")) return false;

		// check  top left square 2x2
		if (lab[lin-1][col].equals("  ") && lab[lin-1][col-1].equals("  ") && lab[lin][col-1].equals("  ")) return false;

		// check  bottom left square 2x2
		if (lab[lin][col-1].equals("  ") && lab[lin+1][col-1].equals("  ") && lab[lin+1][col].equals("  ")) return false;

		// check  top right square 2x2
		if (lab[lin+1][col].equals("  ") && lab[lin+1][col+1].equals("  ") && lab[lin][col+1].equals("  ")) return false;

		// check  top right square 2x2
		if (lab[lin][col+1].equals("  ") && lab[lin-1][col+1].equals("  ") && lab[lin-1][col].equals("  ")) return false;

		// check  top left diagonal
		if (lab[lin-1][col].equals("  ") && lab[lin-1][col-1].equals("  ") && lab[lin][col-1].equals("  ")) return false;

		// check  bottom left diagonal
		if (lab[lin][col-1].equals("  ") && lab[lin+1][col-1].equals("  ") && lab[lin+1][col].equals("  ")) return false;

		// check  top right diagonal
		if (lab[lin+1][col].equals("  ") && lab[lin+1][col+1].equals("  ") && lab[lin][col+1].equals("  ")) return false;

		// check  top right diagonal
		if (lab[lin][col+1].equals("  ") && lab[lin-1][col+1].equals("  ") && lab[lin-1][col].equals("  ")) return false;

		return true;
	}

	/**
	 * Fill all the cells from a given maze with "XX" value.
	 * @param lab 
	 *
	 * @param n the n
	 */
	private static void fillMaze(String[][] lab, int n){
		for (int i = 0; i < n; i++){
			for (int j = 0; j< n; j++) {
				lab[i][j] = "XX";
			}
		}
	}

	/**
	 * Generate a random maze exit.
	 *
	 * @param n dimension of maze
	 * @return a valid maze exit
	 */
	private static Position generateMazeExit(int n){
		Random line = new Random();
		Random column = new Random();
		int lineExit, colExit;

		lineExit = line.nextInt(n);
		if (lineExit == 0 || lineExit == n-1)
			colExit = column.nextInt(n-2)+1;
		else
			colExit = column.nextInt(2)*(n-1);

		Position mazeExit = new Position(lineExit,colExit);

		return mazeExit;
	}

	/**
	 * Prints the maze.
	 */
	public void drawMaze() {
		maze.printMaze();
	}
}
