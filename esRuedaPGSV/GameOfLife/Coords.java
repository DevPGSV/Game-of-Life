package esRuedaPGSV.GameOfLife;

public class Coords {
	private int row;
	private int column;
	
	public Coords(int row, int column){
		this.row = row;
		this.column = column;
	}
	
	public Coords(Coords coords){
		this.row = coords.getRow();
		this.column = coords.getColumn();
	}
	
	public int getRow(){
		return row;
	}
	
	public int getColumn(){
		return column;
	}
	
	public void setRow(int row){
		this.row = row;
		
	}
	
	public void setColumn(int column){
		this.column = column;
	}
	
	public String toString() {
		return "(" + getRow() + ", " + getColumn() + ")";
	}
	
}
