package game.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import maze.logic.*;
import maze.logic.Dragon.Mode;
import maze.logic.Character.Direction;


/**
 * The Class MoveTests.
 */
public class MoveTests {
	
	/** The game testing. */
	Game gameTesting;
	
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		
		/* Set Game Settings */
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

		gameTesting.getMaze().setBoard(lab);
		gameTesting.getMaze().setExit(new Position(2,0));
		
		Dragon dragon = new Dragon(new Position(3,3),Mode.STATIC);
		gameTesting.addDragon(dragon);
		
		gameTesting.updatePositions();

	}

	
	/**
	 * Hero move test.
	 */
	@Test
	public void heroMoveTest(){

		Hero player = new Hero(new Position(4,4));
		
		/* Initial Position Test */
		assertTrue(player.getPosition().equals(new Position(4,4)));
		
		/* Hero Moves */
		Direction[] moves = {
				Direction.DOWN,
				Direction.RIGHT,
				Direction.LEFT,
				Direction.UP
				};
		/* Positions Expected */
		Position[] positions = {
				new Position(5,4),
				new Position(5,5),
				new Position(5,4),
				new Position(4,4)
				};

		/* Assert Hero Positions Change. Does not Consider Wall and Illegal moves.*/
		for (int i = 0 ; i < 4 ; i++){
			
			player.move(moves[i]);
			assertTrue(player.getPosition().equals(positions[i]));
			
		}

	}
	

	/**
	 * Hero move test with maze.
	 */
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
	

	/**
	 * Pick sword test.
	 */
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
	
	
	/**
	 * Hero dead test.
	 */
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
	
	
	/**
	 * Dragon dead test.
	 */
	@Test
	public void dragonDeadTest(){
		
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
	
	
	/**
	 * Win test.
	 */
	@Test
	public void winTest(){
		
		Direction[] moves = {
				Direction.DOWN,
				Direction.DOWN,
				Direction.RIGHT,
				Direction.LEFT,
				Direction.UP,
				Direction.LEFT
				};
		
		//gameTesting.getMaze().printMaze();
		
		/* Hero Moves */
		for (int i = 0; i < 6; i++){
			
			assertFalse(gameTesting.gameOver());
			gameTesting.movePlayer(moves[i]);
			//gameTesting.getMaze().printMaze();
			
		}
		
		assertTrue(gameTesting.gameOver());
		
		/* Hero alive and Dragon Dead */
		assertFalse(gameTesting.getPlayer().isDead());
		assertTrue(gameTesting.getDragons().get(0).isDead());	
		
	}
	
	
	/**
	 * Exit test.
	 */
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
