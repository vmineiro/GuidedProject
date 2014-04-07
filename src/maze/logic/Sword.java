package maze.logic;

<<<<<<< HEAD
public class Sword extends Character {
	
	public Sword(int linSword, int colSword)
	{
		super(linSword, colSword, 'E');
	}
=======

/**
 * The Class Sword.
 */
public class Sword extends Character {
	
	
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

>>>>>>> Vitor
}
