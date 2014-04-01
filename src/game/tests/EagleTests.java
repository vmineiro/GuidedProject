package game.tests;

import java.util.Random;

import org.junit.Test;

import maze.logic.Eagle;
import maze.logic.Position;
import maze.logic.Sword;

/**
 * The Class EagleTests.
 */
public class EagleTests {

	@Test
	public void eaglePathTests() {

		Random rNr = new Random();
		int line = rNr.nextInt(15);
		int col = rNr.nextInt(15);
		Eagle eagle = new Eagle(new Position(line, col));
		line = rNr.nextInt(15);
		col = rNr.nextInt(15);
		eagle.getSword(new Position(line, col));

		System.out.println("Sword Path");		
		for(Position pos : eagle.getSwordPath()){
			System.out.println("(" + pos.getLine() + ", " + pos.getCol() + ")");
		}

		System.out.println("Return Path");

		while(!eagle.getReturnPath().empty()){
			Position pos = eagle.getReturnPath().pop();
			System.out.println("(" + pos.getLine() + ", " + pos.getCol() + ")");

		}
	}

	@Test
	public void eagleMoveTest(){

		Sword sword = new Sword(new Position(5,3));
		Eagle eagle = new Eagle(new Position( 2, 8));


		boolean stoped = false;
		int i = 0;

		while (!stoped){

			if (!eagle.isOnWay() && eagle.isActive()) {
				stoped = true;
				sword.setPosition(eagle.getPosition());
				sword.setActive();
			}

			System.out.println("Eagle: " + eagle.getSymbol());
			eagle.getPosition().printPosition();
			System.out.println("Sword: " + sword.getSymbol());
			sword.getPosition().printPosition();

			if (i == 1){
				eagle.getSword(sword.getPosition());
				System.out.println("Eagle Launched");
			}
			if (eagle.isOnWay()){
				if (eagle.getPosition().equals(sword.getPosition())){
					sword.picked();
				}
				eagle.move(); 
			}
			i++;
		}
	}

}