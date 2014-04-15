package maze.logic;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;


/**
 * The Class Eagle.
 */
public class Eagle extends Character implements Serializable{

	/** The list of positions of sword path. */
	private ArrayList<Position> swordPath;

	/** The return path. */
	private Stack<Position> returnPath;

	/** The has sword. */
	private boolean hasSword;

	/** The on way field. */
	private boolean onWay;

	/** The turning back field. */
	private boolean returning;

	/** Store the last cell symbol to reprint after pass the cell. */
	private String lastCell;


	/**
	 * Instantiates a new eagle.
	 *
	 * @param position the initial position
	 */
	public Eagle(Position position) {
		super(position);
		setInactive();
		hasSword = false;
		onWay = false;
		returning = false;
		swordPath = new ArrayList<Position>();
		returnPath = new Stack<Position>();
		lastCell = "H ";
	}


	/**
	 * Sets the last cell.
	 *
	 * @param positionValue the new last cell
	 */
	public void setLastCell(String positionValue) {
		lastCell = positionValue;
	}


	/**
	 * Gets the last cell.
	 *
	 * @return the last cell
	 */
	public String getLastCell() {
		return lastCell;
	}


	/**
	 * Gets the sword path. Used for debugging.
	 *
	 * @return the sword path
	 */
	public ArrayList<Position> getSwordPath() {
		return swordPath;
	}


	/**
	 * Gets the return path. Used for debugging.
	 *
	 * @return the return path
	 */
	public Stack<Position> getReturnPath() {
		return returnPath;
	}

	
	/**
	 * Move.
	 */
	public void move(){

		if (onWay) {					/* moving to the sword position	*/	
			if (swordPath.isEmpty()){	/* arrived to the sword position */
				lastCell = "  ";
				onWay = false;
			} else {
				moveToSword();
			}

		}
		if (!onWay && !returning && !hasSword){	/* picking the sword. Possibility to the dragon kill the eagle while picking the sword */		
			returning = true;
			hasSword = true;
			symbol = "Ea";

		}
		if (returning){							/* returning to the position where the eagle was launched */	
			if (returnPath.isEmpty()){			/* arrived to the launch position */
				returning = false;
			} else {
				moveBack();
			}

		}

	}


	/**
	 * Move to sword.
	 */
	public void moveToSword(){	
		setPosition(swordPath.get(0));
		swordPath.remove(0);
	}


	/**
	 * Move back.
	 */
	public void moveBack(){	
		setPosition(returnPath.pop());
	}


	/**
	 * Check if the eagle is on way to the sword position or to the launch position.
	 *
	 * @return true, if successful
	 */
	public boolean isOnWay(){
		return onWay;
	}


	/**
	 * Check if the eagle is on way to the sword position or to the launch position.
	 *
	 * @return true, if successful
	 */
	public boolean isReturning(){
		return returning;
	}


	/**
	 * Build the shortest path from the position where the eagle was launched to the sword and the return path.
	 *
	 * @param swordPosition the sword position
	 * @return the sword
	 */
	public void getSword(Position swordPosition) {

		onWay = true;
		setActive();
		symbol = " a";

		int x0 = this.getPosition().getCol();
		int y0 = this.getPosition().getLine();
		int x1 = swordPosition.getCol();
		int y1 = swordPosition.getLine();

		/* 1st Octant */
		if (x1>x0 && y1>=y0 && (y1-y0)<=(x1-x0)) {
			int a, b, d, inc1, inc2;
			a = x1 - x0;
			b = y1 - y0;
			inc2 = 2*b;
			d = inc2 - a; /* d = 2*b – a; */
			inc1 = d - a; /* inc1 = 2*(b-a); */
			Position temp = new Position(y0,x0);
			swordPath.add(temp);
			returnPath.push(temp);
			for(int i=0; i<a; i++){
				temp = new Position(temp.rightPosition().getLine(), temp.rightPosition().getCol());
				if (d >= 0)	{ 
					temp = new Position(temp.bottomPosition().getLine(), temp.bottomPosition().getCol());
					d=d+inc1;
				} else {
					d=d+inc2;
				}
				swordPath.add(temp);
				returnPath.push(temp);
			}
		}
		/* 2nd Octant */
		else if (x1>x0 && y1>y0 && (y1-y0)>(x1-x0)) {
			int a, b, d, inc1, inc2;
			a = y1 - y0;
			b = x1 - x0;
			inc2 = 2*b;
			d = inc2 - a; /* d = 2*b – a; */
			inc1 = d - a; /* inc1 = 2*(b-a); */
			Position temp = new Position(y0,x0);
			swordPath.add(temp);
			returnPath.push(temp);
			for(int i=0; i<a; i++)
			{ 
				temp = new Position(temp.bottomPosition().getLine(), temp.bottomPosition().getCol());
				if (d >= 0)	{
					temp = new Position(temp.rightPosition().getLine(), temp.rightPosition().getCol());
					d=d+inc1;
				} else {
					d=d+inc2;
				}
				swordPath.add(temp);
				returnPath.push(temp);
			}
		}
		/* 3rd Octant */
		else if (x1<=x0 && y1>y0 && (y1-y0)>=(x0-x1)) {
			int a, b, d, inc1, inc2;
			a = y1 - y0;
			b = x0 - x1;
			inc2 = 2*b;
			d = inc2 - a; /* d = 2*b – a; */
			inc1 = d - a; /* inc1 = 2*(b-a); */
			Position temp = new Position(y0,x0);
			swordPath.add(temp);
			returnPath.push(temp);
			for(int i=0; i<a; i++){
				temp = new Position(temp.bottomPosition().getLine(), temp.bottomPosition().getCol());
				if (d >= 0)	{
					temp = new Position(temp.leftPosition().getLine(), temp.leftPosition().getCol());
					d=d+inc1;
				} else {
					d=d+inc2;
				}
				swordPath.add(temp);
				returnPath.push(temp);
			}
		}
		/* 4th Octant */
		else if (x1<x0 && y1>y0 && (y1-y0)<(x0-x1)) {
			int a, b, d, inc1, inc2;
			a = x0 - x1;
			b = y1 - y0;
			inc2 = 2*b;
			d = inc2 - a; /* d = 2*b – a; */
			inc1 = d - a; /* inc1 = 2*(b-a); */
			Position temp = new Position(y0,x0);
			swordPath.add(temp);
			returnPath.push(temp);
			for(int i=0; i<a; i++)
			{ 
				temp = new Position(temp.leftPosition().getLine(), temp.leftPosition().getCol());
				if (d >= 0)	{
					temp = new Position(temp.bottomPosition().getLine(), temp.bottomPosition().getCol());
					d=d+inc1;
				} else {
					d=d+inc2;
				}
				swordPath.add(temp);
				returnPath.push(temp);
			}
		}
		/* 5th Octant */
		else if (x1<=x0 && y1<=y0 && (y0-y1)<=(x0-x1)) {
			int a, b, d, inc1, inc2;
			a = x0 - x1;
			b = y0 - y1;
			inc2 = 2*b;
			d = inc2 - a; /* d = 2*b – a; */
			inc1 = d - a; /* inc1 = 2*(b-a); */
			Position temp = new Position(y0,x0);
			swordPath.add(temp);
			returnPath.push(temp);
			for(int i=0; i<a; i++){
				temp = new Position(temp.leftPosition().getLine(), temp.leftPosition().getCol());
				if (d >= 0)	{ 
					temp = new Position(temp.upperPosition().getLine(), temp.upperPosition().getCol());
					d=d+inc1;
				} else {
					d=d+inc2;
				}
				swordPath.add(temp);
				returnPath.push(temp);
			}
		}
		/* 6th Octant */
		else if (x1<x0 && y1<y0 && (y0-y1)>(x0-x1)) {
			int a, b, d, inc1, inc2;
			a = y0 - y1;
			b = x0 - x1;
			inc2 = 2*b;
			d = inc2 - a; /* d = 2*b – a; */
			inc1 = d - a; /* inc1 = 2*(b-a); */
			Position temp = new Position(y0,x0);
			swordPath.add(temp);
			returnPath.push(temp);
			for(int i=0; i<a; i++)
			{ 
				temp = new Position(temp.upperPosition().getLine(), temp.upperPosition().getCol());
				if (d >= 0)	{
					temp = new Position(temp.leftPosition().getLine(), temp.leftPosition().getCol());
					d=d+inc1;
				} else {
					d=d+inc2;
				}
				swordPath.add(temp);
				returnPath.push(temp);
			}
		}
		/* 7th Octant */
		else if (x1>=x0 && y1<y0 && (y0-y1)>=(x1-x0)) {
			int a, b, d, inc1, inc2;
			a = y0 - y1;
			b = x1 - x0;
			inc2 = 2*b;
			d = inc2 - a; /* d = 2*b – a; */
			inc1 = d - a; /* inc1 = 2*(b-a); */
			Position temp = new Position(y0,x0);
			swordPath.add(temp);
			returnPath.push(temp);
			for(int i=0; i<a; i++){
				temp = new Position(temp.upperPosition().getLine(), temp.upperPosition().getCol());
				if (d >= 0)	{
					temp = new Position(temp.rightPosition().getLine(), temp.rightPosition().getCol());
					d=d+inc1;
				} else {
					d=d+inc2;
				}
				swordPath.add(temp);
				returnPath.push(temp);
			}
		}
		/* 8th Octant */
		else if (x1>x0 && y1<y0 && (y0-y1)<(x1-x0)) {
			int a, b, d, inc1, inc2;
			a = x1 - x0;
			b = y0 - y1;
			inc2 = 2*b;
			d = inc2 - a; /* d = 2*b – a; */
			inc1 = d - a; /* inc1 = 2*(b-a); */
			Position temp = new Position(y0,x0);
			swordPath.add(temp);
			returnPath.push(temp);
			for(int i=0; i<a; i++)
			{ 
				temp = new Position(temp.rightPosition().getLine(), temp.rightPosition().getCol());
				if (d >= 0)	{
					temp = new Position(temp.upperPosition().getLine(), temp.upperPosition().getCol());
					d=d+inc1;
				} else {
					d=d+inc2;
				}
				swordPath.add(temp);
				returnPath.push(temp);
			}
		}

	}

}
