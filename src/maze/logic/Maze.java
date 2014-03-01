package maze.logic;

import java.util.List;

public class Maze {
	
	private char maze [][] = {
		{'X','X','X','X','X','X','X','X','X','X'},
		{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
		{'X',' ','X','X',' ','X',' ','X',' ','X'},
		{'X',' ','X','X',' ','X',' ','X',' ','X'},
		{'X',' ','X','X',' ','X',' ','X',' ','X'},
		{'X',' ',' ',' ',' ',' ',' ','X',' ','X'},
		{'X',' ','X','X',' ','X',' ','X',' ','X'},
		{'X',' ','X','X',' ','X',' ','X',' ','X'},
		{'X',' ','X','X',' ',' ',' ',' ',' ','X'},
		{'X','X','X','X','X','X','X','X','X','X'}
		};	
	
	public Maze(){
		// set maze exit
		setCellValue(5, 9, 'S');
	}
	
	public char[][] getMaze(){
		return maze;
	}
	
	public void setCellValue(int line, int col, char value){
		maze[line][col] = value;
	}
	
	public void clearCell(int line, int col){
		maze[line][col] = ' ';
	}
	
	public char getCellValue(int line,int col){
		return maze[line][col];
	}
	
	public void printMaze() {
		for (int i=0; i<10; i++) {
			for (int j=0; j<10;j++) {
				System.out.print(maze[i][j]);
			}
			System.out.println();
		}
	}
	
	public List<Position> getEmptyCells(){
		
		List <Position> emptyCells = null;
		
		for (int i=0; i < maze.length;i++) {
			for (int j=0; j < maze[0].length;j++){
				if (getCellValue(i, j) == ' ') {
					Position temp = new Position(i,j);
					emptyCells.add(temp);
				}
			}
		}
		return emptyCells;
	}
}
