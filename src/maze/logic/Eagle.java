package maze.logic;

public class Eagle extends Character {
	
	private boolean moving;

	public Eagle(Position position) {
		super(position);
		this.setInactive();
		moving = false;
	}
	
	public boolean isFlying(){
		return isActive();
	}
	
	public void land(){
		setInactive();
	}
	
	public void launch(){
		setActive();
	}
	
	
	
}
