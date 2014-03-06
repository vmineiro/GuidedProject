package maze.logic;

// TODO: Auto-generated Javadoc
/**
 * The Class Hero.
 */
public class Hero extends Character {
	
	/** The armed. */
	private boolean armed;
	
	/** The symbol. */
	private char symbol;
	
	/**
	 * Instantiates a new hero.
	 *
	 * @param line the line
	 * @param col the col
	 */
	public Hero(int line, int col) {
		super(line, col);
		armed = false;
		symbol = 'H';
	}
	
	/**
	 * Checks if is armed.
	 *
	 * @return true, if is armed
	 */
	public boolean isArmed() {
		return armed;
	}
	
	/**
	 * Checks if is dead.
	 *
	 * @return true, if is dead
	 */
	public boolean isDead(){
		return !isActive();
	}

	/**
	 * Valid move.
	 *
	 * @return true, if successful
	 */
	public boolean validMove(){
		return false;
	}
	
	/**
	 * Dye.
	 */
	public void dye() {
		symbol = ' ';
		setInactive();
	}
	
	/**
	 * Gets the armed.
	 *
	 * @return the armed
	 */
	public void getArmed() {
		symbol = 'A';
		armed = true;
	}
	
	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	public char getSymbol(){
		return symbol;
	}
}
