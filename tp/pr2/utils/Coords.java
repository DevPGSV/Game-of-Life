package tp.pr2.utils;

/**
 * <p>Coords class</p>
 * <p>Represents a coordinate object</p>
 * <p>Each coordinate has a row and a column</p>
 * <p>Coordinates can be considered <i>null coordinates</i> (invalid, incorrect, or empty)</p>
 */
public class Coords {
	private int row;
	private int column;
	private boolean nullCoords;
	
	/**
	 * <p>Coords default constructor</p>
	 * <p><i>nullCoords = true;</i></p>
	 */
	public Coords(){
		this.row = 0;
		this.column = 0;
		this.nullCoords = true;
	}
	
	/**
	 * <p>Coords constructor</p>
	 * <p>Generates object with the specified coordinates</p>
	 * @param row value for the row
	 * @param column value for the column
	 */
	public Coords(int row, int column){
		this.row = row;
		this.column = column;
		this.nullCoords = false;
	}
	
	/**
	 * <p>Coords constructor used for "cloning" an instance of Coords</p>
	 * 
	 * @param coords instance of Coords from which a clone will be generated
	 */
	public Coords(Coords coords){
		this.row = coords.getRow();
		this.column = coords.getColumn();
		this.nullCoords = coords.isNullCoords();
	}
	
	/**
	 * <p>row getter</p>
	 * @return the row
	 */
	public int getRow(){
		return row;
	}
	
	/**
	 * <p>column getter</p>
	 * @return the column
	 */
	public int getColumn(){
		return column;
	}
	
	/**
	 * <p>row setter</p>
	 * @param row new value for the row
	 */
	public void setRow(int row){
		this.row = row;
	}
	
	/**
	 * <p>column setter</p>
	 * @param column new value for the column
	 */
	public void setColumn(int column){
		this.column = column;
	}
	
	/**
	 * <p>nullCoords getter</p>
	 * @return if the coordinates are not specified
	 */
	public boolean isNullCoords() {
		return this.nullCoords;
	}
	
	/**
	 * <p>nullCoords setter</p>
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
