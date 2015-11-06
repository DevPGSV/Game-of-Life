package esRuedaPGSV.GameOfLife;

public class Coords {
	private int row;
	private int column;
	private boolean nullCoords;
	
	public Coords(){
		this.row = 0;
		this.column = 0;
		this.nullCoords = true;
	}
	
	public Coords(int row, int column){
		this.row = row;
		this.column = column;
		this.nullCoords = false;
	}
	
	public Coords(Coords coords){
		this.row = coords.getRow();
		this.column = coords.getColumn();
		this.nullCoords = coords.isNullCoords();
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
	
	public boolean isNullCoords() {
		return this.nullCoords;
	}
	
	public void setNullCoords(boolean nullCoords) {
		this.nullCoords = nullCoords;
	}
	
	public String toString() {
		return "(" + getRow() + ", " + getColumn() + ")";
	}
	
	
	public boolean equals(Object coords) {
		if (! (coords instanceof Coords)) {
			return false;
		}
		return (this.hashCode() == coords.hashCode());
		/*
		return (
				this.getRow() == coords.getRow() &&
				this.getColumn() == coords.getColumn() &&
				this.isNullCoords() == coords.isNullCoords()
				);
		*/
	}
	
	public int hashCode() {
		// 1RRRCCCN
	    return 1 * 10000000 + getColumn() * 1000000 + getRow() * 1000 + (isNullCoords() ? 1 : 0);
	}
	
}
