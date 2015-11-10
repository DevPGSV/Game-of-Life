package esRuedaPGSV.GameOfLife;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

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
		if((coords.getRow() >= 0) && (coords.getRow() < this.rows)) {
			if((coords.getColumn() >= 0) && (coords.getColumn() < this.columns)) {
				if (surface[coords.getRow()][coords.getColumn()] == null) {
					surface[coords.getRow()][coords.getColumn()] = new Cell(Values.MAX_LP, Values.MAX_MP);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Creates a new cell at the specified coordinates
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
	
	/**
	 * Search for available positions around some given coordinates
	 * 
	 * @param coords coordinates
	 * @return      a coordinate list with available positions.
	 */
	private List<Coords> getAvailablePositions(Coords coords){
		Coords tempCoords = new Coords(0,0);
		List<Coords> freeSpots = new ArrayList<Coords>();
		//System.out.println("=> " + coords);
		for (int i = -1; i <= 1; i++){
			for (int j = -1; j <= 1; j++){
				tempCoords.setRow(coords.getRow() + i);
				tempCoords.setColumn(coords.getColumn() + j);
				if(tempCoords.getRow() != coords.getRow() || (tempCoords.getColumn() != coords.getColumn())){
					if((tempCoords.getRow() >= 0) && (tempCoords.getRow() < this.rows)) {
						if((tempCoords.getColumn() >= 0) && (tempCoords.getColumn() < this.columns))
						{
							if (surface[tempCoords.getRow()][tempCoords.getColumn()] == null) {
								freeSpots.add(new Coords(tempCoords)); // Add available position to freeSpots list
							}
						}
					}
				}
			}
		}
		return freeSpots;
	}
	
	/**
	 * Try to move a cell to an adjacent position. If it can't move, it loses a life.
	 * 
	 * @param coords coordinates
	 * @return      the coordinates where the cell gets moved.
	 */
	public Coords moveCell(Coords coords){
		List<Coords> freeSpots = getAvailablePositions(coords);
		
		if (freeSpots.isEmpty()) { // If no available positions
			surface[coords.getRow()][coords.getColumn()].loseLp();
			return new Coords();
		} else { // If there is at least one available position
			Random rand = new Random();
			Coords chosenCoords = freeSpots.get(rand.nextInt(freeSpots.size()));
			surface[chosenCoords.getRow()][chosenCoords.getColumn()] = surface[coords.getRow()][coords.getColumn()];
			surface[coords.getRow()][coords.getColumn()] = null;
			return chosenCoords;
		}
		
	}
	
	/**
	 * Try to reproduce a cell to an adjacent position. If it can't reproduce, it dies.
	 * 
	 * @param coords coordinates
	 * @return      the coordinates where the new cell appears.
	 */
	private Coords cellMaturation(Coords coords){
		List<Coords> freeSpots = getAvailablePositions(coords);

		if (freeSpots.isEmpty()) { // If no available positions
				surface[coords.getRow()][coords.getColumn()] = null;
			return new Coords();
		} else { // If there is at least one available position
			Random rand = new Random();
			Coords chosenCoords = freeSpots.get(rand.nextInt(freeSpots.size()));
			surface[chosenCoords.getRow()][chosenCoords.getColumn()] = new Cell(Values.MAX_LP, Values.MAX_MP);
			surface[coords.getRow()][coords.getColumn()] = new Cell(Values.MAX_LP, Values.MAX_MP);
			return chosenCoords;
		}
		
	}
	
	/**
	 * Evolve surface. Try to move all cells.
	 */
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
	}

	public String toString() {
		StringBuilder board = new StringBuilder();
		for (int i = 0; i < rows; i++){
			board.append("|  ");
			for (int j = 0; j < columns; j++){
				if (surface[i][j] != null) {
					board.append(surface[i][j] + "  |  ");
				} else {
					board.append("~~~" + "  |  ");
				}
			}
			board.append("\n"); // System.out.println("\n");
		}
		
		return board.toString();
	}
	

}

