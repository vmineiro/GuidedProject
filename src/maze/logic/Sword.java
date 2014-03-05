package maze.logic;

public class Sword extends Character {
	
	private boolean active;
	private char symbol;
	
	public Sword(int line, int col){
		super(line,col);
		active = true;
		symbol = 'E';
	}
	
	public boolean isAtcive(){
		return active;
	}
	
	public void pickSword(){
		symbol = ' ';
		active = false;
	}

	public char getSymbol() {
		return symbol;
	}

}
