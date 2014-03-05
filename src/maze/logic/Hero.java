package maze.logic;

public class Hero extends Character {
	
	private boolean armed;
	private char symbol;
	
	public Hero(int line, int col) {
		super(line, col);
		armed = false;
		symbol = 'H';
	}
	
	public boolean isArmed() {
		return armed;
	}
	
	public boolean isDead(){
		return !isActive();
	}

	public boolean validMove(){
		return false;
	}
	
	public void dye() {
		symbol = ' ';
		setInactive();
	}
	
	public void getArmed() {
		symbol = 'A';
		armed = true;
	}
	
	public char getSymbol(){
		return symbol;
	}
}
