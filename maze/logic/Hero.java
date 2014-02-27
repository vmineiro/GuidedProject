package maze.logic;

public class Hero extends Character {
	
	private boolean armed;
	private boolean dead;
	
	public Hero(int line, int col) {
		super(line, col);
		armed = false;
		dead = false;
	}
	
	public boolean isArmed() {
		return armed;
	}
	
	public boolean isDead(){
		return dead;
	}

}
