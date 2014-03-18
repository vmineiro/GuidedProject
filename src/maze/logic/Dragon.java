package maze.logic;

import maze.logic.Dragon.Mode;

// TODO: Auto-generated Javadoc
/**
 * The Class Dragon.
 */
public class Dragon extends Character {

	/**
	 * The Enum Mode.
	 */
	public enum Mode {
		/** The static. */ STATIC,
		/** The dinamic. */ DINAMIC,
		/** The mixed. */MIXED
		};
	
	/** The mode. */
	private Mode mode;
	
	/** The sleep. */
	private boolean sleep;
	
	/**
	 * Instantiates a new dragon.
	 *
	 * @param line the line
	 * @param col the col
	 * @param mode the mode
	 */
	public Dragon(int line, int col, Mode mode) {
		super(line,col);
		sleep = false;
		mode = Mode.DINAMIC;
		symbol = 'D';
	}
	
	public Dragon(Position pos, Mode mod) {
		super(pos);
		mode = mod;
		mode = Mode.DINAMIC;
		symbol = 'D';
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
	 * Checks if is asleep.
	 *
	 * @return true, if is asleep
	 */
	public boolean isAsleep(){
		return sleep;
	}
	
	/**
	 * Awake.
	 */
	public void awake(){
		sleep = false;
	}
	
	/**
	 * Sleeps.
	 */
	public void sleeps(){
		symbol = 'd';
		sleep = true;
	}
	
	/**
	 * Gets the mode.
	 *
	 * @return the mode
	 */
	public Mode getMode() {
		return mode;
	}

}
