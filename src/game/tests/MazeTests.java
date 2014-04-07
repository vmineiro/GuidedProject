package game.tests;

import maze.logic.*;

import org.junit.Test;


/**
 * The Class MazeTests.
 */
public class MazeTests {

	@Test
<<<<<<< HEAD
	public void test() {
		
		
//		char maze5_1[][] = maze.logic.Maze.generateMaze(5);
//		char maze10_1[][] = maze.logic.Maze.generateMaze(10);
//		char maze10_2[][] = maze.logic.Maze.generateMaze(10);
//		char maze10_3[][] = maze.logic.Maze.generateMaze(10);
//		char maze15_1[][] = maze.logic.Maze.generateMaze(15);
//		char maze15_2[][] = maze.logic.Maze.generateMaze(15);
//		char maze15_3[][] = maze.logic.Maze.generateMaze(15);
//		 
//
//		for (char[] row : maze5_1){
//			for (char cell : row){
//				System.out.print(cell);
//			}
//			System.out.println();
//		}
//		System.out.println();
//		
//		/* ################ */
//		
//		for (char[] row : maze10_1){
//			for (char cell : row){
//				System.out.print(cell);
//			}
//			System.out.println();
//		}
//		System.out.println();
//		for (char[] row : maze10_2){
//			for (char cell : row){
//				System.out.print(cell);
//			}
//			System.out.println();
//		}
//		System.out.println();
//		for (char[] row : maze10_3){
//			for (char cell : row){
//				System.out.print(cell);
//			}
//			System.out.println();
//		}
//		System.out.println();
//		
//		/* ################ */
//		
//		for (char[] row : maze15_1){
//			for (char cell : row){
//				System.out.print(cell);
//			}
//			System.out.println();
//		}
//		System.out.println();
//		for (char[] row : maze15_2){
//			for (char cell : row){
//				System.out.print(cell);
//			}
//			System.out.println();
//		}
//		System.out.println();
//		for (char[] row : maze15_3){
//			for (char cell : row){
//				System.out.print(cell);
//			}
//			System.out.println();
//		}
		
=======
	public void generateExitTest(){
		Position exit;
		for (int i=0; i< 20;i++){
			exit = maze.logic.MazeGenerator.generateMazeExit(20);
			System.out.println("( " + exit.getLine() + ", " + exit.getCol() + ")");
		}		
	}

	/**
	 * Generate maze test.
	 */
	@Test
	public void generateMazeTest(){

		MazeGenerator maze01 = new MazeGenerator(5);
		maze01.getMaze().printMaze();
		MazeGenerator maze02 = new MazeGenerator(10);
		maze02.getMaze().printMaze();
		MazeGenerator maze03 = new MazeGenerator(15);
		maze03.getMaze().printMaze();
		MazeGenerator maze04 = new MazeGenerator(20);
		maze04.getMaze().printMaze();
		MazeGenerator maze05 = new MazeGenerator(20);
		maze05.getMaze().printMaze();
>>>>>>> Vitor
	}

	@Test
	public void generatePositionsTest(){
		int[] positonsOrders;
		for (int i = 0; i < 5; i++){
			positonsOrders = MazeGenerator.generatePositionsOrder();
			System.out.print("[");
			for (int j : positonsOrders){
				System.out.print(j+",");
			}
			System.out.println("]");
		}	
	}	
}
