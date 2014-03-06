package maze.logic;

public class Character {
	
	private Position position;
	private boolean active;
	
	public Character(int line, int col) {
		position = new Position(line,col);
		active = true;
	}
	
//	public int getLine(){
//		return position.getLine();
//	}
//	
//	public int getCol(){
//		return position.getCol();
//	}
	
	public Position getPosition(){
		return position;
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
	
	public boolean isActive(){
		return active;
	}
	
	public void setInactive(){
		active = false;
	}
	
	public void setActive(){
		active = true;
	}

}
