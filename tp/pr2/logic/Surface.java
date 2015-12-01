package tp.pr2.logic;

import java.util.Random;
import tp.pr2.utils.Coords;

/**
 * <p>Surface class</p>
 * <p>Contains matrix of cells</p>
 */
public class Surface {
	private Cell[][] surface;
	private int rows;
	private int columns;
	
	/**
	 * <p>Surface constructor</p>
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
	 * <p>Asks th ecell to move</p>
	 * 
	 * @param coords coordinates of the cell to move
	 * @return the destination of the cell (or null if it couldn't move)
	 */
	public Coords moveCell(Coords coords) {
		return getCell(coords).executeMove(coords, this);
	}
	
	/**
	 * <p>Checks if the cell at the specified coordinates should die (ie. if its life is 0)</p>
	 * 
	 * @param coords the coordinates of the specified cell
	 * @return if (the cells lp == 0)
	 */
	public boolean shouldDie(Coords coords) {
		return getCell(coords).shouldDie();
	}
	
	/**
	 * <p>Checks if the cell at the specified coordinates should reproduce (ie. if its mp is 0)</p>
	 * 
	 * @param coords the coordinates of the specified cell
	 * @return if (the cells mp == 0)
	 */
	public boolean shouldReproduce(Coords coords) {
		return getCell(coords).shouldReproduce();
	}
	
	/**
	 * <p>rows getter</p>
	 * 
	 * @return the rows of the surface
	 */
	public int getRows() {
		return this.rows;
	}
	
	/**
	 * <p>columns getter</p>
	 * 
	 * @return the columns of the surface
	 */
	public int getColumns() {
		return this.columns;
	}
	
	/**
	 * <p>Initializes the board with cells at random positions</p>
	 */
	public void initBoard() {
		cleanBoard();
		Random rand = new Random();
		surface[1][1] = new SimpleCell(Values.MAX_LP, Values.MAX_MP);
		surface[2][1] = new ComplexCell(Values.MAX_LP, Values.MAX_MP);
		/*
		for (int i = 0; i < rows; i++){
			for (int j = 0; j < columns; j++){
				if ((rand.nextInt() % 2) == 0) {
					surface[i][j] = new SimpleCell(Values.MAX_LP, Values.MAX_MP);
				}
			}
		}
		*/
	}
	
	/**
	 * <p>Checks if there is not a cell at some given coordinates</p>
	 * 
	 * @param row Row to check
	 * @param col Column to check
	 * @return if the is not a cell
	 */
	public boolean isPositionEmpty(int row, int col) {
		return (surface[row][col] == null);
	}
	
	/**
	 * <p>Checks if there is not a cell at some given coordinates</p>
	 * <p><b>Overloads: <i>isPositionEmpty(int row, int col)</i></b></p>
	 * 
	 * @param coords Coordinates to check
	 * @return if the is not a cell
	 */
	public boolean isPositionEmpty(Coords coords) {
		return isPositionEmpty(coords.getRow(), coords.getColumn());
	}
	
	/**
	 * <p>Gets a cell from some given coordinates</p>
	 * <p><b>Overloads: <i>getCell(int row, int col)</i></b></p>
	 * 
	 * @param coords Coordinates of the cell
	 * @return the cell at the specified coordinates
	 */
	public Cell getCell(Coords coords) {
		return getCell(coords.getRow(), coords.getColumn());
	}
	
	/**
	 * <p>Gets a cell from some given coordinates</p>
	 * 
	 * @param row row coord of the cell
	 * @param col col coord of the cell
	 * @return the cell at the specified coordinates
	 */
	public Cell getCell(int row, int col) {
		return surface[row][col];
	}
	
	/**
	 * <p>Reset the board. (Creates a new empty one)</p>
	 */
	public void cleanBoard() {
		surface = new Cell[rows][columns];
	}

	/**
	 * <p>Creates a new cell at the specified coordinates</p>
	 * 
	 * @param coords coordinates
	 * @return      if it was possible to create the cell at the given coordinates
	 */
	public boolean createCell(Coords coords) {
		return createCell(coords, new SimpleCell(Values.MAX_LP, Values.MAX_MP));
	}
	
	/**
	 * <p>Puts a cell at the specified coordinates</p>
	 * 
	 * @param coords coordinates
	 * @param cell cell to place in the specified coordinates
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
	
	/*-*
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
	 * <p>Deletes a cell at the specified coordinates</p>
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

