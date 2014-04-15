package game.tests;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;

import maze.logic.*;
import maze.logic.Dragon.Mode;
import maze.logic.Character.Direction;


/**
 * The Class DragonTests.
 */ 
public class DragonTests {

	/** The game testing. */
	private Game gameTesting;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {

		gameTesting = new Game();
		gameTesting.initGame(0, 1, 1, 0);
		
	}

	
	/**
	 * Dragon move test.
	 */
	@Test
	public void dragonMoveTest(){

		/* Assert Dragon Start Position */
		assertTrue(gameTesting.getDragons().get(0).getPosition().equals(new Position(3,1)));

		Direction[] dragonMoves = {
				Direction.DOWN,
				Direction.LEFT,
				Direction.RIGHT,
				Direction.DOWN,
				Direction.RIGHT,
				Direction.RIGHT,
				Direction.RIGHT,
				Direction.UP
				}; 
		Position[] positions = {
				new Position(4,1),
				new Position(4,1),
				new Position(4,1),
				new Position(5,1),
				new Position(5,2),
				new Position(5,3),
				new Position(5,4),
				new Position(4,4)
				};

		for (int i = 0; i < 8; i++){
			
			/* Dragon Move */
			gameTesting.moveDragon(gameTesting.getDragons().get(0),dragonMoves[i]);
			
			/* Assert Dragon Move*/
			assertTrue(gameTesting.getDragons().get(0).getPosition().equals(positions[i]));
		}

	}

	
	/**
	 * Dragon sleep test.
	 */
	@Test
	public void dragonSleepTest(){

		/* Assert Dragon Awake */
		assertFalse(gameTesting.getDragons().get(0).isAsleep());

		/* Dragon Falls Asleep */
		gameTesting.getDragons().get(0).fallsAsleep();
		
		/* Assert Dragon Asleep */
		assertTrue(gameTesting.getDragons().get(0).isAsleep());

		/* Assert Dragon Start Position */
		assertTrue(gameTesting.getDragons().get(0).getPosition().equals(new Position(3,1)));

		for (int i = 0; i < 8; i++){
			
			/* Dragon Move */
			gameTesting.dragonsMove();
			
			/* Assert Dragon Position - Expected to be always the same */
			assertTrue(gameTesting.getDragons().get(0).getPosition().equals(new Position(3,1)));
			
		}
		
		/* Assert Player Dead While is Asleep */
		gameTesting.movePlayer(Direction.DOWN);
		assertFalse(gameTesting.gameOver());
		assertFalse(gameTesting.getPlayer().isDead());
		assertFalse(gameTesting.getDragons().get(0).isDead());

		/* Player Move Back */
		gameTesting.movePlayer(Direction.UP);
		
		/* Dragon Awakes */
		gameTesting.getDragons().get(0).awake();
		
		/* Assert Dragon is Awake */
		assertFalse(gameTesting.getDragons().get(0).isAsleep());
		
		/* Assert Player Dead */
		gameTesting.movePlayer(Direction.DOWN);
		assertTrue(gameTesting.getPlayer().isDead());
		assertFalse(gameTesting.getDragons().get(0).isDead());
		
	}


	/**
	 * Multiple dragons.
	 */
	@Test
	public void multipleDragons(){

		/* Add New Dragon */
		Dragon newDragon = new Dragon (new Position(5, 1),Mode.DYNAMIC);
		gameTesting.addDragon(newDragon);
		
		/* Add New Dragon */
		Dragon newDragon2 = new Dragon (new Position(5, 4),Mode.DYNAMIC);
		gameTesting.addDragon(newDragon2);
		
		/* Update Maze cells */
		gameTesting.updatePositions();
		
		/* Dragons Moves */
		Direction[] dragon01Moves = {
				Direction.DOWN,
				Direction.DOWN,
				Direction.UP,
				Direction.UP,
				Direction.UP
				};
		Direction[] dragon02Moves = {
				Direction.LEFT,
				Direction.RIGHT,
				Direction.RIGHT,
				Direction.RIGHT, 
				Direction.DOWN
				}; 
		Direction[] dragon03Moves = {
				Direction.UP,
				Direction.UP, 
				Direction.UP,
				Direction.RIGHT,
				Direction.UP
				}; 
		
		/* Dragons Expected Positions */
		Position[] positions01 = {
				new Position(4,1),
				new Position(4,1),
				new Position(3,1),
				new Position(2,1),
				new Position(1,1)
				};
		Position[] positions02 = {
				new Position(5,1),
				new Position(5,2),
				new Position(5,3),
				new Position(5,4),
				new Position(6,4)
				};
		Position[] positions03 = {
				new Position(4,4),
				new Position(3,4),
				new Position(2,4),
				new Position(2,4),
				new Position(1,4)
				};
		
		for (int i = 0; i < 5; i++){
			
			/* Dragon 01 Move */
			gameTesting.moveDragon(gameTesting.getDragons().get(0), dragon01Moves[i]);
			/* Assert Dragon 01 Position */
			assertTrue(gameTesting.getDragons().get(0).getPosition().equals(positions01[i]));
			
			/* Dragon 02 Move */
			gameTesting.moveDragon(gameTesting.getDragons().get(1), dragon02Moves[i]);
			/* Assert Dragon 02 Position */
			assertTrue(gameTesting.getDragons().get(1).getPosition().equals(positions02[i]));
			
			/* Dragon 03 Move */
			gameTesting.moveDragon(gameTesting.getDragons().get(2), dragon03Moves[i]);
			/* Assert Dragon 03 Position */
			assertTrue(gameTesting.getDragons().get(2).getPosition().equals(positions03[i]));
			
			/* Update Maze */
			gameTesting.checkKill();
			
		}		

	}
	
	
	/**
	 * Multiple dragons start.
	 */
	@Test
	public void multipleDragonsStart(){

		/* New Random Maze */
		Game game2 = new Game();
		
		/* Initial Dragons */
		int nDragons = 5;
		
		/* Initializes the game with a maze with size 10x10, with nDragons in DINAMIC mode (3) with maze builder 1 */ 
		game2.initGame(10, 3, nDragons, 1);
		
		/* Number of dragons found */
		int countDragons = 0;
		
		for (String [] line : game2.getMaze().getBoard()){
			
			for (String cell : line){
				
				if (cell.equals("D ") || cell.equals("d ")){
					
					countDragons++;
					
				}
				
			}
			
		}
		
		assertTrue(nDragons == countDragons);

	}

}
