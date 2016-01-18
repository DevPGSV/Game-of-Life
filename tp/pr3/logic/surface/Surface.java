package tp.pr3.logic.surface;

import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
import tp.pr3.exceptions.FileFormatException;
import tp.pr3.logic.cell.Cell;
import tp.pr3.logic.cell.ComplexCell;
import tp.pr3.logic.cell.SimpleCell;
import tp.pr3.utils.Coords;
import tp.pr3.utils.Values;

/**
 * <p>Surface class.</p>
 * <p>Contains matrix of cells.</p>
 */
public class Surface {
	private Cell[][] board;
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
		
		board = new Cell[rows][columns];
		
		//initBoard();
	}
	
	/**
	 * <p>Asks the cell to execute a step of its life cycle</p>
	 * 
	 * @param coords coordinates of the cell to run
	 * @return the destination of the cell (or null if it couldn't move)
	 */
	public Coords runCell(Coords coords) {
		return getCell(coords).executeMove(coords, this);
	}
	
	/**
	 * Moves a cell from one position to another
	 * 
	 * @param origin initial position
	 * @param destination final position
	 */
	public void moveCell(Coords origin, Coords destination) {
		createCell(destination, getCell(origin)); // Clones cell from coords to chosenCoords
		deleteCell(origin);
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
	
	/*
	 * <p>Initializes the board with cells at random positions</p>
	 * 
	 * @param percentage approximate percentage of cells
	 */
	/*public void initBoard(int percentage) {
		cleanBoard();
		Random rand = new Random();
		
		for (int i = 0; i < rows; i++){
			for (int j = 0; j < columns; j++){
				if (rand.nextInt(101) <= percentage) {
					board[i][j] = (rand.nextInt(2) == 0 ? new SimpleCell(Values.MAX_LP, Values.MAX_MP) : new ComplexCell(Values.MAX_EAT));
				}
			}
		}
		
	}*/
	
	/*
	 * <p>Initializes the board with cells at random positions</p>
	 * <p><b>Overloads: <i>initBoard(int percentage)</i></b></p>
	 * <p>calls <i>initBoard</i> with a percentage of 50</p>
	 * @see tp.pr2.logic.Surface#initBoard(int)
	 */
	/*public void initBoard() {
		initBoard(50);
	}*/
	
	/**
	 * <p>Checks if there is not a cell at some given coordinates</p>
	 * 
	 * @param row Row to check
	 * @param col Column to check
	 * @return if the is not a cell
	 */
	public boolean isPositionEmpty(int row, int col) {
		return (board[row][col] == null);
	}
	
	/**
	 * <p>Checks if there is not a cell at some given coordinates</p>
	 * <p><b>Overloads: <i>isPositionEmpty(int row, int col)</i></b></p>
	 * 
	 * @see tp.pr2.logic.Surface#isPositionEmpty(int, int)
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
	 * @see tp.pr2.logic.Surface#getCell(int, int)
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
		return board[row][col];
	}
	
	/**
	 * <p>Reset the board. (Creates a new empty one)</p>
	 */
	public void cleanBoard() {
		board = new Cell[rows][columns];
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
				if (board[coords.getRow()][coords.getColumn()] == null) {
					board[coords.getRow()][coords.getColumn()] = cell;
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * <p>Deletes a cell at the specified coordinates</p>
	 * 
	 * @param coords coordinates
	 * @return      if it was possible to delete the cell at the given coordinates
	 */
	public boolean deleteCell(Coords coords) {
		if((coords.getRow() >= 0) && (coords.getRow() < this.rows)) {
			if((coords.getColumn() >= 0) && (coords.getColumn() < this.columns)) {
				if (board[coords.getRow()][coords.getColumn()] != null) {
					board[coords.getRow()][coords.getColumn()] = null;
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
		StringBuilder stringBoard = new StringBuilder();
		
		
		stringBoard.append(" \\ C    ");
		for (int j = 0; j < columns; j++){ // Print column numbers
			if (j + 1 >= 10) 
				stringBoard.append((j+1) + "      ");
			else
				stringBoard.append((j+1) + "       ");
		}
		stringBoard.append("\n");
		
		
		stringBoard.append("R \\     ");
		for (int j = 0; j < columns; j++){ // Print "v" under column numbers
			stringBoard.append("v       ");
		}
		stringBoard.append("\n");
		
		
		// Print board
		for (int i = 0; i < rows; i++){ 
			if (i + 1 >= 10) 
				stringBoard.append((i+1) + "> |  "); // Print row numbers
			else
				stringBoard.append((i+1) + " > |  "); // Print row numbers
			
			for (int j = 0; j < columns; j++){
				if (!isPositionEmpty(i ,j)) { // If there is a cell
					stringBoard.append(board[i][j] + "  |  "); // Print the cell
				} else {
					stringBoard.append("~~~" + "  |  "); // Print "no cell" placeholder
				}
			}
			stringBoard.append("\n");
		}
		
		return stringBoard.toString();
	}
	
	/**
	 * Load surface from file
	 * @param fileReader input file
	 * @return the surface object
	 * @throws NumberFormatException When an invalid String is converted to int
	 * @throws IOException When there is an IOException
	 * @throws FileFormatException Thrown when there is an error parsing a game a file
	 */
	public static Surface load(Scanner fileReader) throws NumberFormatException, IOException, FileFormatException {
		Surface surface;
		int rows = fileReader.nextInt(); fileReader.nextLine();
		int cols = fileReader.nextInt(); fileReader.nextLine();
		
		surface = new Surface(rows, cols);
		
		while (fileReader.hasNext()) {
			rows = fileReader.nextInt();
			cols = fileReader.nextInt();
			surface.board[rows][cols] = loadCell(fileReader);
		}
		
		return surface;
	}
	
	/**
	 * Save the surface to a file
	 * @param fileWriter the output file
	 * @throws IOException When there is an IOException
	 */
	public void save(Writer fileWriter) throws IOException {
		for (int i = 0; i < rows; i++){ 
			for (int j = 0; j < columns; j++){
				if (!isPositionEmpty(i ,j)) {
					fileWriter.write(i + " " + j + " ");
					board[i][j].save(fileWriter);
					fileWriter.write(System.lineSeparator());
				}
			}
		}
	}
	
	/**
	 * Load a cell, depending of its type
	 * @param fileReader input file
	 * @return the loaded cell
	 * @throws FileFormatException Thrown when there is an error parsing a game a file
	 */
	public static Cell loadCell(Scanner fileReader) throws FileFormatException {
		String cellType = fileReader.next();
		if (cellType.equals("simple")) {
			return (Cell)SimpleCell.load(fileReader);
		} else if (cellType.equals("complex")){
			return (Cell)ComplexCell.load(fileReader);
		} else {
			throw new FileFormatException("Unknown cell type: " + cellType);
		}
	}
	
	/**
	 * Check if some coordinates are valid
	 * @param coords input coordinates
	 * @return if the coordinates are valid
	 */
	public boolean checkIfValidCoords(Coords coords) {
		if((coords.getRow() >= 0) && (coords.getRow() < this.rows)) {
			if((coords.getColumn() >= 0) && (coords.getColumn() < this.columns)) {
				return true;
			}
		}
		return false;
	}
	

}

