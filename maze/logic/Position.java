package maze.logic;

public class Position {

	private int line;
	private int col;
	
	public Position(int line, int col) {
		
	}
	
	public int getLine() {
		return line;
	}
	public int getCol() {
		return col;
	}
	public void moveUp(){
		line--;
	}
	
	public void moveDown(){
		line++;
	}
	
	public void moveLeft(){
		col--;
	}
	
	public void moveRight(){
		col++;
	}

}
