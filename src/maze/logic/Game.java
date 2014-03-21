package maze.logic;

import java.util.Random;

public class Game {
	
	private Maze mazeGame;
	private Hero heroGame;
	private Sword swordGame;
	private Dragon dragonGame;
	
	private boolean runGame;
	
	//=========================================================
	//
	//=========================================================
	
	public Game()
	{
		this.runGame = true;
		this.mazeGame = new Maze();
		this.heroGame = new Hero(0,0);
		this.swordGame = new Sword(0,0);
		this.dragonGame = new Dragon(0,0);
	}
	
	//=========================================================
	//
	//=========================================================
	
	public Maze getMaze()
	{
		return this.mazeGame;
	}
	
	//=========================================================
	//
	//=========================================================
	
	public Hero getHero()
	{
		return this.heroGame;
	}
	
	//=========================================================
	//
	//=========================================================
	
	public Sword getSword()
	{
		return this.swordGame;
	}
	
	//=========================================================
	//
	//=========================================================
	
	public Dragon getDragon(){
		return this.dragonGame;
	}
	
	//=========================================================
	//
	//=========================================================

	public void generateChar(Character charGame)
	{
		Random rand = new Random();
		boolean charPosFound = false;
		int charLin = 0;
		int charCol = 0;

		while(!charPosFound)
		{
			charLin = rand.nextInt(mazeGame.getBoard().length-2)+1;
			charCol = rand.nextInt(mazeGame.getBoard().length-2)+1;

			if(mazeGame.checkPos(charLin, charCol)==' ')
			{
				charGame.setCharCoord(charLin, charCol);
				mazeGame.drawPos(charLin, charCol,charGame.getCharSymbol());
				charPosFound = true;
			}
		}		
	}
	
	//=========================================================
	//
	//=========================================================
	
	public void initGame(int mazeSize)
	{	
		MazeBuilder mBuild = new MazeBuilder();
		
		if(mazeSize > 0)
		{
			mBuild.setRandomMaze(mazeSize);
			mazeGame = mBuild.getResult();
		}
		else
		{
			mBuild.setStandardMaze();
			mazeGame = mBuild.getResult();
		}
		
		generateChar(heroGame);
		generateChar(swordGame);
		generateChar(dragonGame);
		
		//FIX: Check if Dragon and Hero are at least separated by one cell
		while(Math.abs((heroGame.getCharLin()-dragonGame.getCharLin()))==1 || Math.abs((heroGame.getCharCol()-dragonGame.getCharCol()))==1)
		{
			mazeGame.drawPos(dragonGame.getCharLin(), dragonGame.getCharCol(),' ');
			generateChar(dragonGame);
		}
		
	}

	//=========================================================
	//
	//=========================================================
	
	public boolean checkRunGame(){
		return runGame;
	}

	//=========================================================
	//
	//=========================================================
	
	public void setEndGame(){
		this.runGame = false;
	}
	
	//=========================================================
	//
	//=========================================================
	
	public void moveChar(int nLinChar, int nColChar, Character Char){
		mazeGame.movElem(Char.getCharLin(), Char.getCharCol(), nLinChar, nColChar, Char.getCharSymbol());
		Char.setCharCoord(nLinChar, nColChar);
	}
	
	//=========================================================
	//
	//=========================================================
	
	public void heroIsArmed(){
		heroGame.setCharSymbol('A');
		swordGame.setCharSymbol(' ');
	}
	
	//=========================================================
	//
	//=========================================================
	
	public void heroExits(int nLinHero, int nColHero){
		
		if(heroGame.getCharSymbol()=='A')
		{
			moveChar(nLinHero, nColHero, heroGame);
			setEndGame();
		}
	}
	
	//=========================================================
	//
	//=========================================================
	
	public void moveHeroUp()
	{
		int nLinHero = heroGame.getCharLin()-1;
		int nColHero = heroGame.getCharCol();
		
		heroPlay(nLinHero, nColHero);
	}

	//=========================================================
	//
	//=========================================================
	
	public void moveHeroDown()
	{
		int nLinHero = heroGame.getCharLin()+1;
		int nColHero = heroGame.getCharCol();
		
		heroPlay(nLinHero, nColHero);
	}

	//=========================================================
	//
	//=========================================================
	
	public void moveHeroRight()
	{
		int nLinHero = heroGame.getCharLin();
		int nColHero = heroGame.getCharCol()+1;
		
		heroPlay(nLinHero, nColHero);
	}

	//=========================================================
	//
	//=========================================================
	
	public void moveHeroLeft()
	{
		int nLinHero = heroGame.getCharLin();
		int nColHero = heroGame.getCharCol()-1;
		
		heroPlay(nLinHero, nColHero);
	}

	//=========================================================
	//
	//=========================================================
	
	public void heroPlay(int nLinHero, int nColHero)
	{	
		char charPos = mazeGame.checkPos(nLinHero, nColHero);
		
		switch (charPos){
			case ' ':
				moveChar(nLinHero, nColHero, heroGame);
				break;
			case 'H':
				moveChar(nLinHero, nColHero, heroGame);
				break;
			case 'E':
				heroIsArmed();
				moveChar(nLinHero, nColHero, heroGame);	
				break;
			case 'S':
				heroExits(nLinHero, nColHero);
				break;
			default:
				break;
		}
	}
	
	//=========================================================
	//
	//=========================================================
	
	public boolean heroVSdragon(){
		
		if((Math.abs(heroGame.getCharLin()-dragonGame.getCharLin())==1 && heroGame.getCharCol()==dragonGame.getCharCol()) || 
				(heroGame.getCharLin()==dragonGame.getCharLin() && Math.abs(heroGame.getCharCol()-dragonGame.getCharCol())==1)){
			
			return true;	
		}
		else
		{
			return false;
		}
	}
	
	//=========================================================
	//
	//=========================================================

	
	public void updateDragon(){
		
		int nLinDragon = dragonGame.getCharLin();
		int nColDragon = dragonGame.getCharCol();
		
		Random rand = new Random();
		int  randMove = rand.nextInt(4) + 1;

		switch (randMove) {
		case 1:
			nLinDragon = dragonGame.getCharLin()+1;
			nColDragon = dragonGame.getCharCol();
			break;
		case 2:
			nLinDragon = dragonGame.getCharLin()-1;
			nColDragon = dragonGame.getCharCol();
			break;
		case 3:
			nLinDragon = dragonGame.getCharLin();
			nColDragon = dragonGame.getCharCol()+1;
			break;
		case 4:
			nLinDragon = dragonGame.getCharLin()-1;
			nColDragon = dragonGame.getCharCol();
			break;
		default:
			break;
		}
		
		char charPos = mazeGame.checkPos(nLinDragon, nColDragon);
		
		if(charPos==' ' && dragonGame.getCharSymbol()!=' '){
			if((swordGame.getCharSymbol() != ' ') && (dragonGame.getCharLin() == swordGame.getCharLin()) && (dragonGame.getCharCol() == swordGame.getCharCol())){
				moveChar(nLinDragon, nColDragon, dragonGame);
				moveChar(swordGame.getCharLin(), swordGame.getCharCol(), swordGame);
			}
			else{
				moveChar(nLinDragon, nColDragon, dragonGame);
			}
			
		}
		else if(charPos == 'E' && dragonGame.getCharSymbol()!=' '){
			moveChar(nLinDragon, nColDragon, dragonGame);
			mazeGame.drawPos(nLinDragon, nColDragon, 'F');
		}
		
	}
		
	//=========================================================
	//
	//=========================================================
	
	public void updateGame(){
		
		if(heroVSdragon()){
			if(heroGame.getCharSymbol()=='A'){
				dragonGame.setCharSymbol(' ');
				mazeGame.drawPos(dragonGame.getCharLin(), dragonGame.getCharCol(), ' ');
			}
			else{
				setEndGame();				
			}
		}
	}

}
