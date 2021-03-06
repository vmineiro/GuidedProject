/*
 * Dragon
 */
package maze.logic;

import java.io.Serializable;


/**
 * The Class Dragon.
 */
public class Dragon extends Character implements Serializable{
	
	/**
	 * The Enumeration of different possible modes of dragons.
	 */
	public enum Mode {
		/** The static. */ STATIC,
		/** The dynamic. */ DYNAMIC,
		/** The mixed. */MIXED
		};	
		
	/** The mode. */
	private Mode mode;	
	
	/** The actual sleep status of the dragon. */
	private boolean sleep;
	
	
	/**
	 * Instantiates a new dragon.
	 *
	 * @param pos the initial position
	 * @param mod the mode
	 */
	public Dragon(Position pos, Mode mod) {
		super(pos);
		setMode(mod);
		sleep = false;
		symbol = "D ";
	}
	
	
	/**
	 * Sets the mode.
	 *
	 * @param mode2 the new mode
	 */
	public void setMode(Mode mode2) {
		mode = mode2;
	}

	
	/**
	 * Gets the mode.
	 *
	 * @return the mode
	 */
	public Mode getMode() {
		return mode;
	}


	/**
	 * Checks if dragon is dead.
	 *
	 * @return true, if is dead
	 */
	public boolean isDead(){
		return !isActive();
	}
	
	
	/**
	 * Checks if dragon is asleep.
	 *
	 * @return true, if is asleep
	 */
	public boolean isAsleep(){
		return sleep;
	}

	
	/**
	 * Change the sleep status.
	 */
	public void changeStatus() {
		if (sleep) {
			awake();
		} else {
			fallsAsleep();
		}
	}
	

	/**
	 * Dragon falls asleep.
	 */
	public void fallsAsleep() {
		sleep = true;
		symbol = "d ";
	}


	/**
	 * Dragon Awakes.
	 */
	public void awake() {
		sleep = false;
		symbol = "D ";
	}

}
