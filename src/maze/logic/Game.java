package maze.logic;

import java.util.ArrayList;

public class Game {
	
	public Maze maze;
	private Hero player;
	private ArrayList<Dragon> dragons;
	private Sword sword;
	
	public Game() {	
		maze = new Maze();
		player = new Hero(1,1);
		Dragon dragonTemp = new Dragon(3,1, Dragon.Mode.STATIC);
		dragons.add(dragonTemp);
		sword = new Sword(8,1);
		
		setPlayerPosition(player,maze);
		setDragonsPosition(dragons,maze);
		setSwordPosition(sword,maze);
		
	}

	public static void main(String[] args) {

		Game game = new Game();

		game.maze.printMaze();

		boolean gameEnd = false;

		while (!gameEnd) {

			gameEnd = game.playerMove();
			if (!gameEnd) {
				if (dragon[2]==1){
					dragonMove();
					checkKill();
				}
				Game.printLab(lab);
				gameEnd = gameOver();
				updateStatus();
			} else
				System.out.println("\nExit");
		}
	}	

	private void setPlayerPosition(Hero player, Maze maze) {
		if (player.isArmed()) maze.setCellValue(player.getLine(), player.getCol(), 'A');
		else maze.setCellValue(player.getLine(), player.getCol(), 'H');
	}

	private void setSwordPosition(Sword sword, Maze maze) {
		if (sword.isAtcive()) maze.setCellValue(sword.getLine(), sword.getCol(), 'S');
		else maze.setCellValue(sword.getLine(), sword.getCol(), ' ');

	}

	private void setDragonsPosition(ArrayList<Dragon> dragons, Maze maze) {
		for (Dragon dragon : dragons) {
			if (!dragon.isDead()) {
				if (dragon.isAsleep()) {
					maze.setCellValue(dragon.getLine(), dragon.getCol(), 'd');
				} else {
					maze.setCellValue(dragon.getLine(), dragon.getCol(), 'D');
				}		
			}
		}
	}

	public boolean playerMove() {

		boolean validMove = false;

		while (!validMove){

			System.out.println("\nMove (w-up; a-left; s-down; d-right; q-quit):");

			Scanner moveInput = new Scanner(System.in);
			String move = moveInput.nextLine();

			switch (move) {
			case "a":
				if (Game.game.checkCellPlayer(player[0],player[1]-1)) {
					player[1]--;
					validMove = true;
				}
				break;
			case "s":
				if (Game.checkCellPlayer(player[0]+1,player[1])) {
					player[0]++;
					validMove = true;
				}
				break;
			case "d":
				if (Game.checkCellPlayer(player[0],player[1]+1)) {
					player[1]++;
					validMove = true;
				}
				break;
			case "w":
				if (Game.checkCellPlayer(player[0]-1,player[1])) {
					player[0]--;
					validMove = true;
				}
				break;
			case "q":
				validMove = true;
				moveInput.close();
				return true;
			default:
				break;
			}
			if (validMove == false) {
				System.out.println("\nInvalid Move!");
			}
		}
		if (player[0]==sword[0] && player[1]==sword[1]) player[2]=1;
		return false;
	}

	public boolean checkCellPlayer(int line,int col) {
		if (line == caveExit[0] && col == caveExit[1]){
			if (player[2]==0) {
				System.out.println("\nYou need to get the sword (E) and kill the Dragon (D)");
				return false;
			} else {
				if (dragon[2]==1) {
					System.out.println("\nYou need to kill the Dragon (D)");
					return false;
				} else
					return true;
			}
		}
		if (lab[line][col] == 'X') return false;
		return true;
	}

	public boolean checkCellDragon(int line,int col) {
		if (lab[line][col] == 'X') return false;
		return true;
	}

	private void dragonMove() {
		int dragonMoves[][] = getDragonMoves();

		Random rand = new Random();
		int i = rand.nextInt(dragonMoves.length);

		dragon[0]=dragonMoves[i][0];
		dragon[1]=dragonMoves[i][1];

	}

	private int[][] getDragonMoves() {
		List<int[]> temp = new ArrayList<int[]>();
		int cell[] = null;
		for (int i = 0; i<4; i++) {
			if (checkCellDragon(dragon[0]-1, dragon[1])) {
				cell[0] = dragon[0]-1;
				cell[1] = dragon[1];
				temp.add(cell);
			} else if (checkCellDragon(dragon[0], dragon[1]-1)) {

			} else if (checkCellDragon(dragon[0]+1, dragon[1])) {

			} else if (checkCellDragon(dragon[0], dragon[1]+1)) {

			}
		}
		return temp;
	}

	private void checkKill() {
		if (player[2]==0) {
			if ((player[0]==dragon[0] && (player[1]==dragon[1]-1 || player[1]==dragon[1]+1))||
					(player[1]==dragon[1] && (player[0]==dragon[0]-1 || player[0]==dragon[0]+1))) {
				player[3]=0;
			}
		} else {
			if ((player[0]==dragon[0] && (player[1]==dragon[1]-1 || player[1]==dragon[1]+1))||
					(player[1]==dragon[1] && (player[0]==dragon[0]-1 || player[0]==dragon[0]+1))) {
				dragon[2]=0;
			}
		}
	}

	private boolean gameOver() {
		if (dragon[2]==0){
			if (player[0]==caveExit[0] && player[1] == caveExit[1]) {
				System.out.println("\nCongratulations you Win!!!");
				return true;
			} else
				return false;
		} 
		if (player[3]==0) {
			System.out.println("\nGame Over you Lost!!!");
			return true;
		}
		return false;
	}

	public void printLab(char[][] lab) {
		for (int i=0; i<10; i++) {
			for (int j=0; j<10;j++) {
				if (i==player[0] && j==player[1]) {
					if (player[2] == 0) System.out.print("H");
					else System.out.print("A");
				} else if (i==sword[0] && j==sword[1]) {
					if (player[2]==0) System.out.print("E");
					else System.out.print(" ");
				} else if (i==dragon[0] && j==dragon[1]) {
					if (dragon[2]==1) {
						if (player[2]==0 && sword[0]==dragon[0] && sword[1]==dragon[1]) 
							System.out.print("F");
						else 
							System.out.print("D"); 
					} else 
						System.out.print(" ");
				} else if (i==caveExit[0] && j==caveExit[1]){
					System.out.print("S");
				} else
					System.out.print(lab[i][j]);
			}
			System.out.println();
		}

	}
	
//	if (i==player.getLine() && j==player.getCol()) {
//		if (!player.isArmed()) System.out.print("H");
//		else System.out.print("A");
//	} else if (i==sword.getLine() && j==sword.getLine()) {
//		if (sword.isAtcive()) System.out.print("E");
//		else System.out.print(" ");
//	} else if (i==dragon[0] && j==dragon[1]) {
//		if (dragon[2]==1) {
//			if (player[2]==0 && sword[0]==dragon[0] && sword[1]==dragon[1]) 
//				System.out.print("F");
//			else 
//				System.out.print("D"); 
//		} else 
//			System.out.print(" ");
//	} else if (i==mazeExit[0] && j==mazeExit[1]){
//		System.out.print("S");
//	} else
//		System.out.print(lab[i][j]);
}
