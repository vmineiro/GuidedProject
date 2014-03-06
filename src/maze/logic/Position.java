package maze.logic;

public class Position {

	private int line;
	private int col;
	
	public Position(int line, int col) {
		this.line = line;
		this.col = col;
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
	public Position upperPosition(){
		Position temp = new Position (line,col);
		temp.moveUp();
		return temp;
	}
	public Position bottomPosition(){
		Position temp = new Position (line,col);
		temp.moveDown();
		return temp;
	}
	public Position leftPosition(){
		Position temp = new Position (line,col);
		temp.moveLeft();
		return temp;
	}
	public Position rightPosition(){
		Position temp = new Position (line,col);
		temp.moveRight();
		return temp;
	}
	
	public boolean equals(Position pos1){
		if (this.line == pos1.getLine() && this.col == pos1.getCol()) return true;
		return false;
	}

}
