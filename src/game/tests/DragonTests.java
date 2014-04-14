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

		Direction[] dragonMoves = {Direction.DOWN, Direction.LEFT, Direction.RIGHT, Direction.DOWN,Direction.RIGHT,Direction.RIGHT,Direction.RIGHT,Direction.UP}; 
		Position[] positions = {new Position(4,1),new Position(4,1),new Position(4,1),new Position(5,1),new Position(5,2),new Position(5,3),new Position(5,4),new Position(4,4)};

		for (int i = 0; i < 8; i++){
			gameTesting.moveDragon(gameTesting.getDragons().get(0),dragonMoves[i]);
			assertTrue(gameTesting.getDragons().get(0).getPosition().equals(positions[i]));
		}

	}

	@Test
	public void dragonSleepTest(){

		assertFalse(gameTesting.getDragons().get(0).isAsleep());

		gameTesting.getDragons().get(0).fallsAsleep();
		assertTrue(gameTesting.getDragons().get(0).isAsleep());

		assertTrue(gameTesting.getDragons().get(0).getPosition().equals(new Position(3,1)));

		for (int i = 0; i < 8; i++){
			gameTesting.dragonsMove();
			assertTrue(gameTesting.getDragons().get(0).getPosition().equals(new Position(3,1)));
		}
		
		gameTesting.movePlayer(Direction.DOWN);
		assertFalse(gameTesting.gameOver());
		assertFalse(gameTesting.getPlayer().isDead());
		assertFalse(gameTesting.getDragons().get(0).isDead());

		gameTesting.movePlayer(Direction.UP);
		gameTesting.getDragons().get(0).awake();
		assertFalse(gameTesting.getDragons().get(0).isAsleep());
		gameTesting.movePlayer(Direction.DOWN);
		assertTrue(gameTesting.getPlayer().isDead());
		assertFalse(gameTesting.getDragons().get(0).isDead());
		
	}


	@Test
	public void multipleDragons(){

		Dragon newDragon = new Dragon (new Position(5, 1),Mode.DINAMIC);
		gameTesting.addDragon(newDragon);
		Dragon newDragon2 = new Dragon (new Position(5, 4),Mode.DINAMIC);
		gameTesting.addDragon(newDragon2);
		gameTesting.updatePositions();
		
		Direction[] dragon01Moves = {Direction.DOWN, Direction.DOWN, Direction.UP, Direction.UP, Direction.UP};
		Direction[] dragon02Moves = {Direction.LEFT, Direction.RIGHT, Direction.RIGHT, Direction.RIGHT, Direction.DOWN}; 
		Direction[] dragon03Moves = {Direction.UP, Direction.UP, Direction.UP, Direction.RIGHT, Direction.UP}; 
		
		Position[] positions01 = {new Position(4,1),new Position(4,1),new Position(3,1),new Position(2,1),new Position(1,1)};
		Position[] positions02 = {new Position(5,1),new Position(5,2),new Position(5,3),new Position(5,4),new Position(6,4)};
		Position[] positions03 = {new Position(4,4),new Position(3,4),new Position(2,4),new Position(2,4),new Position(1,4)};
		
		for (int i = 0; i < 5; i++){
			gameTesting.moveDragon(gameTesting.getDragons().get(0), dragon01Moves[i]);
			assertTrue(gameTesting.getDragons().get(0).getPosition().equals(positions01[i]));
			gameTesting.moveDragon(gameTesting.getDragons().get(1), dragon02Moves[i]);
			assertTrue(gameTesting.getDragons().get(1).getPosition().equals(positions02[i]));
			gameTesting.moveDragon(gameTesting.getDragons().get(2), dragon03Moves[i]);
			assertTrue(gameTesting.getDragons().get(2).getPosition().equals(positions03[i]));
			
			gameTesting.checkKill();
		}		

	}
	
	@Test
	public void multipleDragonsStart(){

		Game game2 = new Game();
		int nDragons = 5;
		game2.initGame(10, 3, nDragons, 1);
		
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
