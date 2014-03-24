package game.tests;

import java.util.ArrayList;

import org.junit.Test;

import maze.logic.Eagle;
import maze.logic.Position;

public class EagleTests {

	@Test
	public void eaglePathTests() {

		Position start = new Position(2,2);
		ArrayList<Eagle> eagles = new ArrayList<Eagle>();

		Eagle eagle01 = new Eagle(start);
		eagle01.setPath(new Position(3,4));
		eagles.add(eagle01);
		Eagle eagle02 = new Eagle(start);
		eagle02.setPath(new Position(4,3));
		eagles.add(eagle02);
		Eagle eagle03 = new Eagle(start);
		eagle03.setPath(new Position(4,2));
		eagles.add(eagle03);
		Eagle eagle04 = new Eagle(start);
		eagle04.setPath(new Position(3,0));
		eagles.add(eagle04);
		Eagle eagle05 = new Eagle(start);
		eagle05.setPath(new Position(1,0));
		eagles.add(eagle05);
		Eagle eagle06 = new Eagle(start);
		eagle06.setPath(new Position(0,1));
		eagles.add(eagle06);
		Eagle eagle07 = new Eagle(start);
		eagle07.setPath(new Position(0,3));
		eagles.add(eagle07);
		Eagle eagle08 = new Eagle(start);
		eagle08.setPath(new Position(1,4));
		eagles.add(eagle08);
		int i = 1;
		for (Eagle eagle : eagles) {
			System.out.println("Eagle" + i + ":");
			System.out.println("Sword Path");		
			for(Position pos : eagle.getSwordPath()){
				System.out.println("(" + pos.getLine() + ", " + pos.getCol() + ")");
			}

			System.out.println("Return Path");

			while(!eagle.getReturnPath().empty()){
				Position pos = eagle.getReturnPath().pop();
				System.out.println("(" + pos.getLine() + ", " + pos.getCol() + ")");

			}
			i++;
			System.out.println();
		}


	}

}
