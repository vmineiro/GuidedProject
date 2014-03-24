/*
 * 
 */
package maze.logic;

// TODO: Auto-generated Javadoc
/**
 * The Class Sword.
 */
public class Sword extends Character {
	
	/**
	 * Instantiates a new sword.
	 *
	 * @param line the line
	 * @param col the column
	 */
	public Sword(int line, int col){
		super(line,col);
		setActive();
		symbol = "E ";
	}
	
	/**
	 * Instantiates a new sword.
	 *
	 * @param pos the pos
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

}
