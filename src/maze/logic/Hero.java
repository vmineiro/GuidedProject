package maze.logic;

public class Hero extends Character {
	
	private boolean armed;
	
	public Hero(int line, int col) {
		super(line, col);
		armed = false;
	}
	
	public boolean isArmed() {
		return armed;
	}
	
	public boolean isDead(){
		return isActive();
	}

	public boolean validMove(){
		return false;
	}
}
