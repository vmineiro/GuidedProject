/*
 * 
 */
package maze.logic;

// TODO: Auto-generated Javadoc
/**
 * The Class Sword.
 */
public class Sword extends Character {
	
	/** The active. */
	private boolean active;
	
	/**
	 * Instantiates a new sword.
	 *
	 * @param line the line
	 * @param col the column
	 */
	public Sword(int line, int col){
		super(line,col);
		active = true;
		symbol = "E ";
	}
	
	/**
	 * Instantiates a new sword.
	 *
	 * @param pos the pos
	 */
	public Sword(Position pos) {
		super(pos);
		active = true;
		symbol = "E ";
	}

	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isAtcive(){
		return active;
	}
	
	/**
	 * Pick sword.
	 */
	public void pickSword(){
		symbol = "  ";
		active = false;
	}

}
