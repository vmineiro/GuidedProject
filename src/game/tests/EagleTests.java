package game.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import maze.logic.Eagle;
import maze.logic.Position;
import maze.logic.Sword;


/**
 * The Class EagleTests.
 */
public class EagleTests {

	
	/**
	 * Eagle path tests.
	 */
	@Test
	public void eaglePathTests() {
		
		/* Create an Eagle */
		Eagle eagle = new Eagle(new Position(0, 0));
		
		/* Create the path to the sword and the returning path */
		eagle.getSword(new Position(2, 5));
		
		/* Sword Path expected */
		Position[] swordPath = {
				new Position(0,0),
				new Position(0,1),
				new Position(1,2),
				new Position(1,3),
				new Position(2,4),
				new Position(2,5)
				};
		
		int i = 0;
		
		/* Assert Positions of Sworth Path Created */
		for(Position pos : eagle.getSwordPath()){
			
			assertTrue(pos.equals(swordPath[i]));
			i++;
			
		}

		/* Assert Positions of Returning Path Created */
		while(!eagle.getReturnPath().empty()){
			
			i--;
			Position pos = eagle.getReturnPath().pop();
			assertTrue(pos.equals(swordPath[i]));
			
		}
		
		/* New Eagle */
		Eagle eagle2 = new Eagle(new Position(5, 0));
		eagle.getSword(new Position(1,0));
		
		Position[] swordPath2 = {
				new Position(5,0),
				new Position(4,0),
				new Position(3,0),
				new Position(2,0),
				new Position(1,0)
				};

		i = 0;
		for(Position pos2 : eagle2.getSwordPath()){
			
			assertTrue(pos2.equals(swordPath2[i]));
			i++;
			
		}

		while(!eagle2.getReturnPath().empty()){
			
			i--;
			Position pos = eagle.getReturnPath().pop();
			assertTrue(pos.equals(swordPath2[i]));
			
		}
	
	}

	/**
	 * Eagle move test.
	 */
	@Test
	public void eagleMoveTest(){

		/* Sword Created */
		Sword sword = new Sword(new Position(5,3));
		
		/* Eagle Created */
		Eagle eagle = new Eagle(new Position(2,8));

		/* Moves Expected */
		Position[] moves = {
				new Position(2, 8),
				new Position(2, 8),
				new Position(2, 8),
				new Position(3, 7),
				new Position(3, 6),
				new Position(4, 5),
				new Position(4, 4),
				new Position(5, 3),
				new Position(5, 3)
				};

		boolean stoped = false;
		int i = 0;

		/* Eagle Move Simulation */
		while (!stoped){

			if (!eagle.isOnWay() && eagle.isActive()) {
				
				stoped = true;
				
				/* Assert Symbol Change */
				assertTrue(eagle.getSymbol().equals("Ea"));
				sword.setPosition(eagle.getPosition());
				sword.setActive();
				
			}

			if (i == 1){
				
				/* Eagle Launch */
				assertFalse(eagle.isActive());
				eagle.getSword(sword.getPosition());
				assertTrue(eagle.isActive());
				
			}
			
			if (eagle.isOnWay()){
				
				/* Picking the sword */
				if (eagle.getPosition().equals(sword.getPosition())){
					assertTrue(sword.isActive());
					sword.picked();
					assertFalse(sword.isActive());
					assertTrue(sword.getSymbol().equals("  "));
				}
				
				/* Positions check */
				assertTrue(eagle.getPosition().equals(moves[i]));
				eagle.move(); 
			}
			
			i++;
			
		}
		
	}

}