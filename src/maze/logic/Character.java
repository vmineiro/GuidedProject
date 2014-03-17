package maze.logic;

public class Character {
	
	private Position positionChar;
	private char symbolChar;
	
	public Character(int linChar, int colChar, char symChar)
	{
		this.positionChar = new Position(linChar,colChar);
		this.symbolChar = symChar;
	}
	
	public int getCharLin()
	{
		return positionChar.getLinPos();
	}
	
	public int getCharCol()
	{
		return positionChar.getColPos();
	}
	
	public void setCharCoord(int nLin, int nCol)
	{
		this.positionChar = new Position(nLin, nCol);
	}
	
	public char getCharSymbol()
	{
		return symbolChar;
	}
	
	public void setCharSymbol(char symChar){
		this.symbolChar = symChar;
	}
	
}
