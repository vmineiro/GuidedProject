/*
 * 
 */
package game.tests;

import maze.logic.*;

import static org.junit.Assert.*;

import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class MazeTests.
 */
public class MazeTests {

//	@Test
//	public void genereteMezeTest() {
//
//		char maze5_1[][] = maze.logic.Maze.generateMaze(5);
//		char maze10_1[][] = maze.logic.Maze.generateMaze(10);
//		char maze10_2[][] = maze.logic.Maze.generateMaze(10);
//		char maze10_3[][] = maze.logic.Maze.generateMaze(10);
//		char maze15_1[][] = maze.logic.Maze.generateMaze(15);
//		char maze15_2[][] = maze.logic.Maze.generateMaze(15);
//		char maze15_3[][] = maze.logic.Maze.generateMaze(15);
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
//	}

//	@Test
//	public void generateExitTest(){
//		int[] exit;
//		for (int i=0; i< 20;i++){
//			exit = maze.logic.Maze.generateMazeExit(20);
//			System.out.println("( " + exit[0] + ", " + exit[1]+")");
//		}		
//	}
	
	/**
 * Generate maze test.
 */
@Test
	public void generateMazeTest(){
		
		MazeGenerator maze01 = new MazeGenerator(5);
		maze01.drawMaze();
		MazeGenerator maze02 = new MazeGenerator(10);
		maze02.drawMaze();
		MazeGenerator maze03 = new MazeGenerator(15);
		maze03.drawMaze();
		MazeGenerator maze04 = new MazeGenerator(20);
		maze04.drawMaze();
		MazeGenerator maze05 = new MazeGenerator(20);
		maze05.drawMaze();		
	}
	
//	@Test
//	public void generatePositionsTest(){
//		int[] positonsOrders;
//		for (int i = 0; i < 5; i++){
//			positonsOrders = MazeGenerator.generatePositionsOrder();
//			System.out.print("[");
//			for (int j : positonsOrders){
//				System.out.print(j+",");
//			}
//			System.out.println("]");
//		}	
//	}	
}
