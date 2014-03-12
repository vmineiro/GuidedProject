package maze.logic;

// TODO: Auto-generated Javadoc
/**
 * The Class Sword.
 */
public class Sword extends Character {
	
	/** The active. */
	private boolean active;
	
	/** The symbol. */
	private char symbol;
	
	/**
	 * Instantiates a new sword.
	 *
	 * @param line the line
	 * @param col the col
	 */
	public Sword(int line, int col){
		super(line,col);
		active = true;
		symbol = 'E';
	}
	
	/**
	 * Checks if is atcive.
	 *
	 * @return true, if is atcive
	 */
	public boolean isAtcive(){
		return active;
	}
	
	/**
	 * Pick sword.
	 */
	public void pickSword(){
		symbol = ' ';
		active = false;
	}

	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	public char getSymbol() {
		return symbol;
	}

}
