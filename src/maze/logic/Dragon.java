package maze.logic;

public class Dragon extends Character {

	public enum Mode {STATIC,DINAMIC,MIXED};
	
	private Mode mode;
	private boolean sleep;
	private char symbol;
	
	public Dragon(int line, int col, Mode mode) {
		super(line,col);
		sleep = false;
		mode = Mode.STATIC;
		symbol = 'D';
	}
	
	public char getSymbol(){
		return symbol;
	}
	
	public boolean isDead(){
		return !isActive();
	}
	
	public boolean isAsleep(){
		return sleep;
	}
	
	public void dye(){
		symbol = ' ';
		setInactive();
	}
	
	public void awake(){
		sleep = false;
	}
	
	public void sleeps(){
		symbol = 'd';
		sleep = true;
	}

}
