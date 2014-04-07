package maze.logic;

<<<<<<< HEAD
public class Hero extends Character {

	public Hero(int linHero, int colHero)
	{
		super(linHero, colHero,'H');
	}	
=======

/**
 * The Class Hero.
 */
public class Hero extends Character {
	
	
	/** The armed. */
	private boolean armed;
	
	
	/** The armed. */
	private boolean eagleLaunched;
	
	
	/**
	 * Instantiates a new hero.
	 *
	 * @param pos the initial position
	 */
	public Hero(Position pos) {
		super(pos);
		armed = false;
		symbol = "Ha";
		eagleLaunched = false;
	}

	
	/**
	 * Character gets armed without eagle help.
	 *
	 * @return the armed
	 */
	public void getArmed() {
		if (symbol.equals("Ha")){		/* In case of eagle wasn't launched */
			symbol = "Aa";
		} else {
			symbol = "A ";				/* In case of eagle died */
		}
		armed = true;
	}
	
	
	/**
	 * Launch eagle.
	 */
	public void launchEagle(){
		symbol = "H ";
		eagleLaunched = true;
	}

	
	/**
	 * Pick eagle.
	 */
	public void pickEagle() {
		symbol = "Aa";
		armed = true;
	}
	

	/**
	 * Check if is armed.
	 *
	 * @return true, if is armed
	 */
	public boolean isArmed() {
		return armed;
	}
	
	
	/**
	 * Check if is dead.
	 *
	 * @return true, if is dead
	 */
	public boolean isDead(){
		return !isActive();
	}
	
	
	/**
	 * Check if the eagle was launched.
	 *
	 * @return true, if successful
	 */
	public boolean eagleLaunched(){
		return eagleLaunched;
	}
	
>>>>>>> Vitor
}

