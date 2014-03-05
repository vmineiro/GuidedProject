package maze.logic;

public class Dragon extends Character {

	public enum Mode {STATIC,DINAMIC,MIXED};
	
	private Mode mode;
	private boolean sleep;
	
	public Dragon(int line, int col, Mode mode) {
		super(line,col);
		sleep = false;
		mode = Mode.STATIC;	
	}
	
	public boolean isDead(){
		return !isActive();
	}
	
	public boolean isAsleep(){
		return sleep;
	}

}
