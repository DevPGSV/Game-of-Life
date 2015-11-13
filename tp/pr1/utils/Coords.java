package tp.pr1.utils;

/**
 * Coords class.
 * Represents a coordinate object.
 * Each coordinate has a row and a column.
 * Coordinates can be considered "null coordinates" (invalid, incorrect, or empty)
 */
public class Coords {
	private int row;
	private int column;
	private boolean nullCoords;
	
	/**
	 * Coords default constructor.
	 * <i>nullCoords = true;</i>
	 */
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
	
	/**
	 * row getter
	 * @return the row
	 */
	public int getRow(){
		return row;
	}
	
	/**
	 * column getter
	 * @return the column
	 */
	public int getColumn(){
		return column;
	}
	
	/**
	 * row setter
	 * @param row new value for the row
	 */
	public void setRow(int row){
		this.row = row;
	}
	
	/**
	 * column setter
	 * @param column new value for the column
	 */
	public void setColumn(int column){
		this.column = column;
	}
	
	/**
	 * nullCoords getter
	 * @return if the coordinates are not specified
	 */
	public boolean isNullCoords() {
		return this.nullCoords;
	}
	
	/**
	 * nullCoords setter
	 * @param nullCoords new value for nullCoords (if the coordinates are specified)
	 */
	public void setNullCoords(boolean nullCoords) {
		this.nullCoords = nullCoords;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "(" + (getRow()+1) + ", " + (getColumn()+1) + ")";
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object coords) { // Overwrites the default equals method to make sure different instances with the same coordinates are considered equal by java.util.HashSet.contains(Object arg0)
		return (coords instanceof Coords) && (this.hashCode() == coords.hashCode());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() { // Generates a hash code for this instance of the object. Instances with the same coordinates have the same hash. Instances with different coordinates have different hashes. 
		// 1RRRCCCN
	    return 1 * 10000000 + getColumn() * 1000000 + getRow() * 1000 + (isNullCoords() ? 1 : 0);
	}
	
}
