package maze.logic;

public class Position {
	
	private int linPos;
	private int colPos;
	
	public Position(int linPos, int colPos){
		this.linPos = linPos;
		this.colPos = colPos;
	}
	
	public void setLinPos(int nLin){
		this.linPos = nLin;
	}
	
	public void setColPos(int nCol){
		this.colPos = nCol;
	}
	
	public int getLinPos(){
		return linPos;
	}
	
	public int getColPos(){
		return colPos;
	}
	
	public void setCoord(int nLin, int nCol){
		this.linPos = nLin;
		this.colPos = nCol;
	}
	
}
