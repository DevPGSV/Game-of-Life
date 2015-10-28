package esRuedaPGSV.GameOfLife;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Surface {
	private Cell[][] surface;
	private int rows;
	private int columns;
	
	public Surface(int numRows, int numColumns){
		this.rows = numRows;
		this.columns = numColumns;
		
		surface = new Cell[rows][columns];
		
		for (int i = 0; i < rows; i++){
			for (int j = 0; j < columns; j++){
				surface[i][j] = new Cell(1, 2);
			}
		}
		
		
		// Coords c = new Coords(1, 2);
		
		/*Cell cell = new Cell(1,3);
		boolean dead = cell.loseLp();
		if (cell.loseLp()) {
			
		}
		
		System.out.print(cell);
		
		*/
	}
	
	/**
	 * Creates a new cell at the specified coordinates
	 * 
	 * @param coords coordinates
	 * @return      if it was possible to create the cell at the given coordinates
	 */
	public boolean createCell(Coords coords) {
		if (surface[coords.getRow()][coords.getColumn()] == null) {
			surface[coords.getRow()][coords.getColumn()] = new Cell(1, 2);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Creates a new cell at the specified coordinates
	 * 
	 * @param coords coordinates
	 * @return      if it was possible to delete the cell at the given coordinates
	 */
	public boolean deleteCell(Coords coords) {
		if (surface[coords.getRow()][coords.getColumn()] != null) {
			surface[coords.getRow()][coords.getColumn()] = null;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Try to move a cell to an adjacent position. If it gets moved, it maturates. If not, it loses a life.
	 * 
	 * @param coords coordinates
	 * @return      if it was possible to move the cell at the given coordinates
	 */
	public boolean moveCell(Coords coords){
		Coords tempCoords = new Coords(0,0);
		List<Coords> freeSpots = new ArrayList<Coords>();
		
		for (int i = -1; i <= 1; i++){
			for (int j = -1; j <= 1; j++){
				tempCoords.setRow(coords.getRow() + i);
				tempCoords.setColumn(coords.getColumn() + j);
				if(tempCoords.getRow() != coords.getRow() && (tempCoords.getColumn() != coords.getColumn())){
					if((tempCoords.getRow() >= 0) && (tempCoords.getRow() < this.rows)) {
						if((tempCoords.getColumn() >= 0) && (tempCoords.getColumn() < this.columns))
						{
							if (surface[tempCoords.getRow()][tempCoords.getColumn()] == null) {
								freeSpots.add(tempCoords); // Add available position to freeSpots list
							}
						}
					}
				}
			}
		}
		
		if (freeSpots.isEmpty()) { // If no available positions
			surface[coords.getRow()][coords.getColumn()].loseLp();
			return false;
		} else { // If there is at least one available position
			Random rand = new Random();
			Coords choosenCoords = freeSpots.get(rand.nextInt(freeSpots.size()));
			surface[choosenCoords.getRow()][choosenCoords.getColumn()] = surface[coords.getRow()][coords.getColumn()];
			surface[coords.getRow()][coords.getColumn()] = null;
			surface[choosenCoords.getRow()][choosenCoords.getColumn()].maturate();
			return true;
		}
	}
	
	/**
	 * Evolve surface. Try to move all cells.
	 */
	public void evolve() {
		for (int i = 0; i < rows; i++){
			for (int j = 0; j < columns; j++){
				if (surface[i][j] != null) {
					moveCell(new Coords(i, j));
				}
			}
		}
	}

	public String toString() {
		String board = "";
		for (int i = 0; i < rows; i++){
			board = board +  "|  ";
			for (int j = 0; j < columns; j++){
				board = board + surface[i][j] + "  |  ";
			}
			board = board + "\n"; // System.out.println("\n");
		}
		
		return board;
	}
	

}

