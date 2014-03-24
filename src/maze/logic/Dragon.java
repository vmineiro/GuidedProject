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
		setMode(mode);
		sleep = false;
		symbol = "D ";
	}
	
	public void setMode(Mode mode2) {
		mode = mode2;
	}

	/**
	 * Instantiates a new dragon.
	 *
	 * @param pos the pos
	 * @param mod the mod
	 */
	public Dragon(Position pos, Mode mod) {
		super(pos);
		setMode(mode);
		sleep = false;
		symbol = "D ";
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
		symbol = "d ";
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

	public void changeStatus() {
		sleep = !sleep;
		if (sleep) {
			symbol = "d ";
		} else {
			symbol = "D ";
		}
	}

}
