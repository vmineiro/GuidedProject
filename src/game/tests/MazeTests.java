/*
 * Maze Tests
 */
package game.tests;

import maze.logic.*;

import org.junit.Test;


/**
 * The Class MazeTests.
 */
public class MazeTests {

	/**
	 * Test maze exit generation.
	 */
	@Test
	public void generateExitTest(){

		/*
		Position exit;
		for (int i=0; i< 20;i++){
			exit = maze.logic.MazeGenerator.generateMazeExit(20);
			System.out.println("( " + exit.getLine() + ", " + exit.getCol() + ")");
		}
		 */
		
	}

	/**
	 * Test maze generation.
	 */
	@Test
	public void generateMazeTest(){
		
		/*
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
		*/
		
	}

	/**
	 * Test the creation of an array with no repeated numbers, between 0 and 3, which will represent the 
	 * direction (left, down, right, up) order to create the path in the maze creation.
	 */
	@Test
	public void generatePositionsTest(){
		
		/*
		int[] positonsOrders;
		for (int i = 0; i < 5; i++){
		
			positonsOrders = MazeGenerator.generatePositionsOrder();
			System.out.print("[");
			
			for (int j : positonsOrders){
			
				System.out.print(j+",");
				
			}
			
			System.out.println("]");
		}	
		*/
		
	}	
	
}
