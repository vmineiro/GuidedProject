package maze.logic;


import java.util.Random;
import java.util.Stack;


/**
 * The Class MazeGenerator.
 */
public class MazeGenerator {

	/** The maze. */
	private Maze maze;

	
	/**
	 * Instantiates a Standard maze.
	 */
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
		maze.setBoard(lab);
		
	}
	
	
	/**
	 * Instantiates a new maze generator.
	 *
	 * @param n the n
	 */
	public MazeGenerator(int n){
		
		maze = new Maze();
		
		/* Generate the Position for the Exit */
		Position mazeExit = generateMazeExit(n); 
		maze.setExit(mazeExit);
		
		/* Generate a Maze with size of n and the exit previous generated */
		maze.setBoard(generateMaze(n, mazeExit));
		
	}
	
	
	/**
	 * Gets the maze.
	 *
	 * @return the maze
	 */
	public Maze getMaze(){
		return maze;
	}
	

	/**
	 * Generate a random square maze.
	 *
	 * @param n number of columns and lines of the maze
	 * @param mazeExit the maze exit
	 * @return a valid maze
	 */
	public static String[][] generateMaze(int n, Position mazeExit){
		
		/* Initialize an empty Maze */
		String[][] lab = new String[n][n];

		/* create a bound variable */
		int bound = n-1;

		/* Fill maze positions as Wall */
		fillMaze(lab, n);

		/* Set the Maze Exit */
		lab[mazeExit.getLine()][mazeExit.getCol()] = "SS";

		/* Generate maze paths */
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
	 * @param pos the actual position
	 * @param lab the maze
	 * @param bound the bound index of maze
	 */
	private static void generatePath(Position pos, String[][] lab, int bound) {

		/* check if the Position is not a valid position (backtracking) */
		if(!validPathPosition(pos, lab, bound)) return;

		/* Clear the Cell */
		lab[pos.getLine()][pos.getCol()] = "  ";
		
		/* Generate an Array with number between 1 and 4 in a random order to select which position will be cleared next */
		int[] positonsOrders; 
		positonsOrders = generatePositionsOrder();

		/* Put the left, bottom, right, upper position in a stack (for backtracking) according to the number in the array generated previously */
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

		/* Recursive calling for all the positions in the stack, until is empty */
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
	 * Already exist. Method used to check if a number already exist in a given array.
	 *
	 * @param positonsOrders the positions orders (Positions represented as integers)
	 * @param randNr the random number generated
	 * @return true, if successful
	 */
	private static boolean alreadyExist(int[] positonsOrders, int randNr) {
		
		for (int i : positonsOrders){
			
			if (i == randNr) return true;
			
		}
		return false;
		
	}

	
	/**
	 * Check if a given position valid is valid to be cleared according to the guide requirements.
	 *
	 * @param pos the actual position
	 * @param lab the maze
	 * @param bound the bound index of maze
	 * @return true, if successful
	 */
	private static boolean validPathPosition(Position pos, String[][] lab, int bound) {
		
		int col = pos.getCol();
		int lin = pos.getLine();

		/* check if the position is a bound of the maze */
		if (lin == 0 || col == 0 || lin == bound || col == bound) return false;
		
		/* check if the position is repeated */
		if (lab[lin][col].equals("  ")) return false;
		
//		/* check  top wall square 3x3 */
//		if (lin > 1 && col > 1 && lin < (bound -2) && col < (bound - 8)){
//			/* check  top left square 3x3 */
//			if (lab[lin-1][col].equals("  ") && lab[lin-1][col-1].equals("  ") && lab[lin][col-1].equals("  ")) return false;
//		}

		/* check  top left square 2x2 */
		if (lab[lin-1][col].equals("  ") && lab[lin-1][col-1].equals("  ") && lab[lin][col-1].equals("  ")) return false;

		/* check  bottom left square 2x2 */
		if (lab[lin][col-1].equals("  ") && lab[lin+1][col-1].equals("  ") && lab[lin+1][col].equals("  ")) return false;

		/* check  top right square 2x2 */
		if (lab[lin+1][col].equals("  ") && lab[lin+1][col+1].equals("  ") && lab[lin][col+1].equals("  ")) return false;

		/* check  top right square 2x2 */
		if (lab[lin][col+1].equals("  ") && lab[lin-1][col+1].equals("  ") && lab[lin-1][col].equals("  ")) return false;

		/* check  top left diagonal */
		if (lab[lin-1][col].equals("  ") && lab[lin-1][col-1].equals("  ") && lab[lin][col-1].equals("  ")) return false;

		/* check  bottom left diagonal */
		if (lab[lin][col-1].equals("  ") && lab[lin+1][col-1].equals("  ") && lab[lin+1][col].equals("  ")) return false;

		/* check  top right diagonal */
		if (lab[lin+1][col].equals("  ") && lab[lin+1][col+1].equals("  ") && lab[lin][col+1].equals("  ")) return false;

		/* check  top right diagonal */
		if (lab[lin][col+1].equals("  ") && lab[lin-1][col+1].equals("  ") && lab[lin-1][col].equals("  ")) return false;

		return true;
		
	}

	
	/**
	 * Fill all the cells from a given maze with "XX" value.
	 *
	 * @param lab the maze
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
	public static Position generateMazeExit(int n){
		
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

}
