package maze.logic;

public class Dragon extends Character {

	private boolean dead;
	private Mode mode;
	private boolean sleep;
	public enum Mode {STATIC,DINAMIC,MIXED};
	
	public Dragon(int line, int col, Mode mode) {
		super(line,col);
		sleep = false;
		dead = false;
		mode = Mode.STATIC;	
	}
	
	public boolean isDead(){
		return dead;
	}
	
	public boolean isSleep(){
		return sleep;
	}

}
