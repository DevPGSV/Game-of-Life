package tp.pr1.logic;

import java.util.Random;
import tp.pr1.utils.Coords;

/**
 * Surface class.
 * Contains matrix of cells.
 */
public class Surface {
	private Cell[][] surface;
	private int rows;
	private int columns;
	
	/**
	 * Surface constructor
	 * 
	 * @param numRows number of rows for the board
	 * @param numColumns number of columns for the board
	 */
	public Surface(int numRows, int numColumns){
		this.rows = numRows;
		this.columns = numColumns;
		
		surface = new Cell[rows][columns];
		
		initBoard();
	}
	
	/**
	 * rows getter
	 * @return the rows of the surface
	 */
	public int getRows() {
		return this.rows;
	}
	
	/**
	 * columns getter
	 * @return the columns of the surface
	 */
	public int getColumns() {
		return this.columns;
	}
	
	/**
	 * Initializes the board with cells at random positions.
	 */
	public void initBoard() {
		cleanBoard();
		Random rand = new Random();

		for (int i = 0; i < rows; i++){
			for (int j = 0; j < columns; j++){
				if ((rand.nextInt() % 2) == 0) {
					surface[i][j] = new Cell(Values.MAX_LP, Values.MAX_MP);
				}
			}
		}
	}
	
	/**
	 * Checks if there is not a cell at some given coordinates
	 * @param row Row to check
	 * @param col Column to check
	 * @return if the is not a cell
	 */
	public boolean isPositionEmpty(int row, int col) {
		return (surface[row][col] == null);
	}
	
	/**
	 * Checks if there is not a cell at some given coordinates.
	 * Overloads: isPositionEmpty(int row, int col)
	 * 
	 * @param coords Coordinates to check
	 * @return if the is not a cell
	 */
	public boolean isPositionEmpty(Coords coords) {
		return isPositionEmpty(coords.getRow(), coords.getColumn());
	}
	
	/**
	 * Gets a cell from some given coordinates.
	 * Overloads: getCell(int row, int col)
	 * 
	 * @param coords Coordinates of the cell
	 * @return the cell at the specified coordinates
	 */
	public Cell getCell(Coords coords) {
		return getCell(coords.getRow(), coords.getColumn());
	}
	
	/**
	 * Gets a cell from some given coordinates.
	 * @param coords Coordinates of the cell
	 * @return the cell at the specified coordinates
	 */
	public Cell getCell(int row, int col) {
		return surface[row][col];
	}
	
	/**
	 * Reset the board. (Creates a new empty one)
	 */
	public void cleanBoard() {
		surface = new Cell[rows][columns];
	}

	/**
	 * Creates a new cell at the specified coordinates
	 * 
	 * @param coords coordinates
	 * @return      if it was possible to create the cell at the given coordinates
	 */
	public boolean createCell(Coords coords) {
		return createCell(coords, new Cell(Values.MAX_LP, Values.MAX_MP));
	}
	
	/**
	 * Puts a cell at the specified coordinates
	 * 
	 * @param coords coordinates
	 * @return      if it was possible to create the cell at the given coordinates
	 */
	public boolean createCell(Coords coords, Cell cell) {
		if((coords.getRow() >= 0) && (coords.getRow() < this.rows)) {
			if((coords.getColumn() >= 0) && (coords.getColumn() < this.columns)) {
				if (surface[coords.getRow()][coords.getColumn()] == null) {
					surface[coords.getRow()][coords.getColumn()] = cell;
					return true;
				}
			}
		}
		return false;
	}
	
	/* *
	 * Places a cell at the specified coordinates.
	 * Doesn't care if the position is already occupied.
	 * 
	 * @param coords coordinates
	 * @return      if it was possible to create the cell at the given coordinates
	 */
	/*
	public boolean overrideCell(Coords coords, Cell cell) {
		if((coords.getRow() >= 0) && (coords.getRow() < this.rows)) {
			if((coords.getColumn() >= 0) && (coords.getColumn() < this.columns)) {
				surface[coords.getRow()][coords.getColumn()] = cell;
				return true;
			}
		}
		return false;
	}*/
	
	/**
	 * Deletes a cell at the specified coordinates
	 * 
	 * @param coords coordinates
	 * @return      if it was possible to delete the cell at the given coordinates
	 */
	public boolean deleteCell(Coords coords) {
		if((coords.getRow() >= 0) && (coords.getRow() < this.rows)) {
			if((coords.getColumn() >= 0) && (coords.getColumn() < this.columns)) {
				if (surface[coords.getRow()][coords.getColumn()] != null) {
					surface[coords.getRow()][coords.getColumn()] = null;
					return true;
				}
			}
		}
		return false;
	}
	
	
	/* *
	 * Evolve surface. Try to move all cells.
	 * <b>This function is NOT used!!!</b> (Main implementation moved to world)
	 */
	/*
	public void evolve() {
		HashSet<Coords> movedCells = new HashSet<Coords>();
		Coords newCoords;
		for (int i = 0; i < rows; i++){
			for (int j = 0; j < columns; j++){ // For every position
				if (surface[i][j] != null) { // If there is a cell (if the position is not empty)
					if (!movedCells.contains(new Coords(i, j))) { // If cell is not one moved to this position in this evolution step
						
						if(surface[i][j].getLp() == 0){ // Kill the cell if its lp is 0
							System.out.println("[ DIE ] (" + i + ", " + j + ")");
							surface[i][j] = null;
							
						}else if(surface[i][j].getMp() == 0){ // Complete its maturation if its mp is 0
							newCoords = cellMaturation(new Coords(i, j));
							if (!newCoords.isNullCoords()) { // If the cell could reproduce...
								System.out.println("[MATUR] (" + i + ", " + j + ") to " + newCoords);
								movedCells.add(new Coords(i, j));
								movedCells.add(newCoords);
							}
							
						}else { // Try to move the cell
							surface[i][j].maturate();
							newCoords = moveCell(new Coords(i, j));
							if (!newCoords.isNullCoords()) { // If the cell could move...
								System.out.println("[MOVED] (" + i + ", " + j + ") to " + newCoords);
								movedCells.add(newCoords);
							}
						}
					}
				}
			}
		}
	}*/

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder board = new StringBuilder();
		
		
		board.append(" \\ C   ");
		for (int j = 0; j < columns; j++){ // Print column numbers
			board.append((j+1) + "       ");
		}
		board.append("\n");
		
		
		board.append("R \\    ");
		for (int j = 0; j < columns; j++){ // Print "v" under column numbers
			board.append("v       ");
		}
		board.append("\n");
		
		
		// Print board
		for (int i = 0; i < rows; i++){ 
			board.append((i+1) + "> |  "); // Print row numbers
			for (int j = 0; j < columns; j++){
				if (!isPositionEmpty(i ,j)) { // If there is a cell
					board.append(surface[i][j] + "  |  "); // Print the cell
				} else {
					board.append("~~~" + "  |  "); // Print "no cell" placeholder
				}
			}
			board.append("\n");
		}
		
		return board.toString();
	}
	

}

