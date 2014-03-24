/*
 * 
 */
package maze.logic;

// TODO: Auto-generated Javadoc
/**
 * The Class Hero.
 */
public class Hero extends Character {
	
	/** The armed. */
	private boolean armed;
	
	/**
	 * Instantiates a new hero.
	 *
	 * @param line the line 
	 * @param col the col
	 */
	public Hero(int line, int col) {
		super(line, col);
		armed = false;
		symbol = "Ha";
	}
	
	/**
	 * Instantiates a new hero.
	 *
	 * @param pos the pos
	 */
	public Hero(Position pos) {
		super(pos);
		armed = false;
		symbol = "Ha";
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
	 * Character gets armed.
	 *
	 * @return the armed
	 */
	public void getArmed() {
		symbol = "A ";
		armed = true;
	}
	
	/**
	 * Launch eagle.
	 */
	public void launchEagle(){
		symbol = "H ";
	}
}
