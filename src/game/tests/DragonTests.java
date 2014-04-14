package game.tests;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import maze.logic.*;
import maze.logic.Dragon.Mode;
import maze.logic.Character.Direction;

public class DragonTests {
	
	private Game gameTesting;
	
	
	@Before
	public void setUp() throws Exception {
		
		gameTesting = new Game();

		String[][] lab = {
				{"XX","XX","XX","XX","XX","XX","XX","XX","XX","XX"},
				{"XX","  ","  ","  ","  ","  ","  ","  ","  ","XX"},
				{"XX","  ","XX","XX","  ","XX","  ","XX","  ","XX"},
				{"XX","  ","XX","XX","  ","XX","  ","XX","  ","XX"},
				{"XX","  ","XX","XX","  ","XX","  ","XX","  ","XX"},
				{"XX","  ","  ","  ","  ","  ","  ","XX","  ","SS"},
				{"XX","  ","XX","XX","  ","XX","  ","XX","  ","XX"},
				{"XX","  ","XX","XX","  ","XX","  ","XX","  ","XX"},
				{"XX","  ","XX","XX","  ","  ","  ","  ","  ","XX"},
				{"XX","XX","XX","XX","XX","XX","XX","XX","XX","XX"}
		};

		gameTesting.getPlayer().setPosition(new Position(1, 1));
		
		gameTesting.getSword().setPosition(new Position(8,1));

		gameTesting.getMaze().setMaze(lab);
		gameTesting.getMaze().setExit(new Position(5,9));
		
		Dragon dragon = new Dragon(new Position(3,1),Mode.STATIC);
		gameTesting.addDragon(dragon);
		
		gameTesting.updatePositions();

	}
	
	@Test
	public void dragonMoveTest(){
		
		assertTrue(gameTesting.getDragons().get(0).getPosition().equals(new Position(3,1)));
		gameTesting.getDragons().get(0).move(Direction.DOWN);
		assertTrue(gameTesting.getDragons().get(0).getPosition().equals(new Position(4,1)));
		gameTesting.getDragons().get(0).move(Direction.LEFT);
		
	}

}
