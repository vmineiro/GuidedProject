package maze.logic;

import java.io.Serializable;


/**
 * The Class Sword.
 */
public class Sword extends Character implements Serializable{
	
	
	/**
	 * Instantiates a new sword.
	 *
	 * @param pos the initial position
	 */
	public Sword(Position pos) {
		super(pos);
		setActive();
		symbol = "E ";
	}

	
	/**
	 * Pick sword.
	 */
	public void picked(){
		symbol = "  ";
		setInactive();
	}
	

	/**
	 * Droped.
	 *
	 * @param position the position
	 */
	public void droped(Position position) {
		setPosition(position);
		setActive();
		symbol = "E ";
	}

}
