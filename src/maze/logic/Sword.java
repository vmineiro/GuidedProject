package maze.logic;

public class Sword extends Character {
	
	private boolean active;
	
	public Sword(int line, int col){
		super(line,col);
		active = true;
	}
	
	public boolean isAtcive(){
		return active;
	}

}
