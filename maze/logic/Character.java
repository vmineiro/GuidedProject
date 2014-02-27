package maze.logic;

public class Character {
	
	private Position position;
	
	public Character(int line, int col) {
		position = new Position(line,col);
	}
	
	public int getLine(){
		return position.getLine();
	}
	
	public int getCol(){
		return position.getCol();
	}
	
	public void moveUp(){
		position.moveUp();
	}
	
	public void moveDown(){
		position.moveDown();
	}
	
	public void moveLeft(){
		position.moveLeft();
	}
	
	public void moveRight(){
		position.moveRight();
	}

}
