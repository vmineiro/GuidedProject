package game.tests;

import maze.logic.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class MazeTests {

	@Test
	public void test() {

		char maze5_1[][] = maze.logic.Maze.generateMaze(5);
		char maze10_1[][] = maze.logic.Maze.generateMaze(10);
		char maze10_2[][] = maze.logic.Maze.generateMaze(10);
		char maze10_3[][] = maze.logic.Maze.generateMaze(10);
		char maze15_1[][] = maze.logic.Maze.generateMaze(15);
		char maze15_2[][] = maze.logic.Maze.generateMaze(15);
		char maze15_3[][] = maze.logic.Maze.generateMaze(15);

		for (char[] row : maze5_1){
			for (char cell : row){
				System.out.print(cell);
			}
			System.out.println();
		}
		System.out.println();
		
		/* ################ */
		
		for (char[] row : maze10_1){
			for (char cell : row){
				System.out.print(cell);
			}
			System.out.println();
		}
		System.out.println();
		for (char[] row : maze10_2){
			for (char cell : row){
				System.out.print(cell);
			}
			System.out.println();
		}
		System.out.println();
		for (char[] row : maze10_3){
			for (char cell : row){
				System.out.print(cell);
			}
			System.out.println();
		}
		System.out.println();
		
		/* ################ */
		
		for (char[] row : maze15_1){
			for (char cell : row){
				System.out.print(cell);
			}
			System.out.println();
		}
		System.out.println();
		for (char[] row : maze15_2){
			for (char cell : row){
				System.out.print(cell);
			}
			System.out.println();
		}
		System.out.println();
		for (char[] row : maze15_3){
			for (char cell : row){
				System.out.print(cell);
			}
			System.out.println();
		}
	}

}
