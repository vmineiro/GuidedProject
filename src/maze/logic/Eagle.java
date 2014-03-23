package maze.logic;

// TODO: Auto-generated Javadoc
/**
 * The Class Eagle.
 */
public class Eagle extends Character {
	
	/** The moving. */
	private boolean moving;

	/**
	 * Instantiates a new eagle.
	 *
	 * @param position the position
	 */
	public Eagle(Position position) {
		super(position);
		this.setInactive();
		moving = false;
	}
	
	/**
	 * Checks if is flying.
	 *
	 * @return true, if is flying
	 */
	public boolean isFlying(){
		return isActive();
	}
	
	/**
	 * Land.
	 */
	public void land(){
		setInactive();
	}
	
	/**
	 * Launch.
	 */
	public void launch(){
		setActive();
	}
	
	
	
}
