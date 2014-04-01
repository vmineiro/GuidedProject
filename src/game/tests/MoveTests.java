package game.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import maze.logic.*;
import maze.logic.Dragon.Mode;
import maze.logic.Character.Direction;

public class MoveTests {
	
	Game gameTesting;

	
	@Before
	public void setUp() throws Exception {
		
		gameTesting = new Game();

		String[][] lab = {
				{"XX","XX","XX","XX","XX"},
				{"XX","  ","  ","  ","XX"},
				{"SS","  ","XX","  ","XX"},
				{"XX","  ","  ","  ","XX"},
				{"XX","XX","XX","XX","XX"}
		};

		gameTesting.getPlayer().setPosition(new Position(1, 1));
		
		gameTesting.getSword().setPosition(new Position(3,1));

		gameTesting.getMaze().setMaze(lab);
		gameTesting.getMaze().setExit(new Position(2,0));
		
		Dragon dragon = new Dragon(new Position(3,3),Mode.STATIC);
		gameTesting.addDragon(dragon);
		
		gameTesting.updatePositions();

	}

	
	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void heroMoveTest(){

		Hero player = new Hero(new Position(4,4));

		/* Initial Position Test */
		assertTrue(player.getPosition().equals(new Position(4,4)));
		
		/* Move Left Test */
		player.move(Direction.LEFT);
		assertTrue(player.getPosition().equals(new Position(4,3)));
		
		/* Move Down Test */
		player.move(Direction.DOWN);
		assertTrue(player.getPosition().equals(new Position(5,3)));
		
		/* Move Right Test */
		player.move(Direction.RIGHT);
		assertTrue(player.getPosition().equals(new Position(5,4)));
		
		/* Move Up Test */
		player.move(Direction.UP);
		assertTrue(player.getPosition().equals(new Position(4,4)));

	}
	

	@Test
	public void heroMoveTestWithMaze(){
		
		/* Initial Hero Position Test */
		assertTrue(gameTesting.getPlayer().getPosition().equals(new Position(1,1)));
		
		/* Move Down Test */
		gameTesting.movePlayer(Direction.DOWN);
		assertTrue(gameTesting.getPlayer().getPosition().equals(new Position(2,1)));
		
		/* Move to a Wall Position Test - Invalid Move */
		assertFalse(gameTesting.checkPlayerMove(gameTesting.getPlayer().getPosition(Direction.RIGHT)));
		gameTesting.movePlayer(Direction.RIGHT);
		assertTrue(gameTesting.getPlayer().getPosition().equals(new Position(2,1)));

	}
	

	@Test
	public void pickSwordTest(){
		
		/* Initial Hero Position Test */
		assertTrue(gameTesting.getPlayer().getPosition().equals(new Position(1,1)));
		assertFalse(gameTesting.getPlayer().isArmed());
		
		/* Initial Sword Position Test */
		assertTrue(gameTesting.getSword().isActive());
		assertTrue(gameTesting.getSword().getPosition().equals(new Position(3,1)));
		
		/* Hero Unarmed */
		assertFalse(gameTesting.getPlayer().isArmed());
		
		/* Move Down Test */
		gameTesting.movePlayer(Direction.DOWN);
		assertFalse(gameTesting.getPlayer().isArmed());
		assertTrue(gameTesting.getPlayer().getPosition().equals(new Position(2,1)));
		assertTrue(gameTesting.getSword().isActive());
		
		/* Move Down Test */
		gameTesting.movePlayer(Direction.DOWN);
		assertTrue(gameTesting.getPlayer().isArmed());
		assertTrue(gameTesting.getPlayer().getPosition().equals(new Position(3,1)));
		assertFalse(gameTesting.getSword().isActive());
		
	}
	
	@Test
	public void heroDeadTest(){
		
		/* Dragon sleep Test */
		assertFalse(gameTesting.getDragons().get(0).isAsleep());
		assertTrue(gameTesting.getDragons().get(0).getPosition().equals(new Position(3,3)));
		
		/* Hero Moves */
		gameTesting.movePlayer(Direction.RIGHT);
		gameTesting.movePlayer(Direction.RIGHT);
		gameTesting.movePlayer(Direction.DOWN);
		assertTrue(gameTesting.getPlayer().getPosition().equals(new Position(2,3)));
		
		/* Hero Dead */
		assertTrue(gameTesting.getPlayer().isDead());
		assertFalse(gameTesting.getDragons().get(0).isDead());
		
		
	}
	
	@Test
	public void dragonDeadTest(){
		
		gameTesting.printMaze();
		
		/* Dragon sleep Test */
		assertFalse(gameTesting.getDragons().get(0).isAsleep());
		assertTrue(gameTesting.getDragons().get(0).getPosition().equals(new Position(3,3)));
		
		/* Hero Moves */
		gameTesting.movePlayer(Direction.DOWN);
		gameTesting.movePlayer(Direction.DOWN);
		gameTesting.movePlayer(Direction.RIGHT);
		assertTrue(gameTesting.getPlayer().getPosition().equals(new Position(3,2)));
		
		/* Hero and Dragon Dead */
		assertFalse(gameTesting.getPlayer().isDead());
		assertTrue(gameTesting.getDragons().get(0).isDead());
		
		
	}
	
	@Test
	public void winTest(){
		
		/* Hero Moves */
		gameTesting.movePlayer(Direction.DOWN);
		gameTesting.movePlayer(Direction.DOWN);
		gameTesting.movePlayer(Direction.RIGHT);
		gameTesting.movePlayer(Direction.LEFT);
		gameTesting.movePlayer(Direction.UP);
		gameTesting.movePlayer(Direction.LEFT);
		
		assertTrue(gameTesting.gameOver());
		
		/* Hero and Dragon Dead */
		assertFalse(gameTesting.getPlayer().isDead());
		assertTrue(gameTesting.getDragons().get(0).isDead());
		
		
	}
	
	@Test
	public void exitTest(){
		
		/* Hero Moves */
		gameTesting.movePlayer(Direction.DOWN);;
		gameTesting.movePlayer(Direction.LEFT);
		
		assertFalse(gameTesting.gameOver());
		
		/* Hero and Dragon Dead */
		assertTrue(gameTesting.getPlayer().getPosition().equals(new Position(2,1)));
		
		
	}
	
	
}
