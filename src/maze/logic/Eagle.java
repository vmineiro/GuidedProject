package maze.logic;

import java.util.ArrayList;
import java.util.Stack;

import sun.misc.Queue;

// TODO: Auto-generated Javadoc

/**
 * The Class Eagle.
 */
public class Eagle extends Character {

	private ArrayList<Position> swordPath;

	private Stack<Position> returnPath;

	boolean hasSword;

	/**
	 * Instantiates a new eagle.
	 *
	 * @param position the position
	 */
	public Eagle(Position position) {
		super(position);
		this.setInactive();
		hasSword = false;
		swordPath = new ArrayList<Position>();
		returnPath = new Stack<Position>();
	}

	/**
	 * Checks if is flying.
	 *
	 * @return true, if is flying
	 */
	public boolean isFlying(){
		return isActive();
	}

	public void getSword() {
		symbol = "Aa";
		hasSword = true;
	}

	public void dropSword() {
		hasSword = false;
		setInactive();
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

	public void setPath(Position swordPosition) {

		int x0 = this.getPosition().getCol();
		int y0 = this.getPosition().getLine();
		int x1 = swordPosition.getCol();
		int y1 = swordPosition.getLine();

		// 1st Octant
		if (x1>x0 && y1>=y0 && (y1-y0)<=(x1-x0)) {
			int a, b, d, inc1, inc2;
			a = x1 - x0;
			b = y1 - y0;
			inc2 = 2*b;
			d = inc2 - a; // d = 2*b – a;
			inc1 = d - a; // inc1 = 2*(b-a);
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
		// 2nd Octant
		else if (x1>x0 && y1>y0 && (y1-y0)>(x1-x0)) {
			int a, b, d, inc1, inc2;
			a = y1 - y0;
			b = x1 - x0;
			inc2 = 2*b;
			d = inc2 - a; // d = 2*b – a;
			inc1 = d - a; // inc1 = 2*(b-a);
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
		// 3rd Octant
		else if (x1<=x0 && y1>y0 && (y1-y0)>=(x0-x1)) {
			int a, b, d, inc1, inc2;
			a = y1 - y0;
			b = x0 - x1;
			inc2 = 2*b;
			d = inc2 - a; // d = 2*b – a;
			inc1 = d - a; // inc1 = 2*(b-a);
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
		// 4th Octant
		else if (x1<x0 && y1>y0 && (y1-y0)<(x0-x1)) {
			int a, b, d, inc1, inc2;
			a = x0 - x1;
			b = y1 - y0;
			inc2 = 2*b;
			d = inc2 - a; // d = 2*b – a;
			inc1 = d - a; // inc1 = 2*(b-a);
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
		// 5th Octant
		else if (x1<=x0 && y1<=y0 && (y0-y1)<=(x0-x1)) {
			int a, b, d, inc1, inc2;
			a = x0 - x1;
			b = y0 - y1;
			inc2 = 2*b;
			d = inc2 - a; // d = 2*b – a;
			inc1 = d - a; // inc1 = 2*(b-a);
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
		// 6th Octant
		else if (x1<x0 && y1<y0 && (y0-y1)>(x0-x1)) {
			int a, b, d, inc1, inc2;
			a = y0 - y1;
			b = x0 - x1;
			inc2 = 2*b;
			d = inc2 - a; // d = 2*b – a;
			inc1 = d - a; // inc1 = 2*(b-a);
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
		// 7th Octant
		else if (x1>=x0 && y1<y0 && (y0-y1)>=(x1-x0)) {
			int a, b, d, inc1, inc2;
			a = y0 - y1;
			b = x1 - x0;
			inc2 = 2*b;
			d = inc2 - a; // d = 2*b – a;
			inc1 = d - a; // inc1 = 2*(b-a);
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
		// 8th Octant
		else if (x1>x0 && y1<y0 && (y0-y1)<(x1-x0)) {
			int a, b, d, inc1, inc2;
			a = x1 - x0;
			b = y0 - y1;
			inc2 = 2*b;
			d = inc2 - a; // d = 2*b – a;
			inc1 = d - a; // inc1 = 2*(b-a);
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

	public ArrayList<Position> getSwordPath() {
		return swordPath;
	}

	public Stack<Position> getReturnPath() {
		return returnPath;
	}

}
