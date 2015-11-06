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
	
	public Surface(int numRows, int numColumns){
		this.rows = numRows;
		this.columns = numColumns;
		
		surface = new Cell[rows][columns];
		
		initBoard();
		
		// Coords c = new Coords(1, 2);
		
		/*Cell cell = new Cell(1,3);
		boolean dead = cell.loseLp();
		if (cell.loseLp()) {
			
		}
		
		System.out.print(cell);
		
		*/
	}
	
	
	public void cleanBoard() {
		surface = new Cell[rows][columns];
	}
	
	public void initBoard() {
		cleanBoard();
		Random rand = new Random();
		if (!Values.DEBUG) {
			for (int i = 0; i < rows; i++){
				for (int j = 0; j < columns; j++){
					if ((rand.nextInt() % 2) == 0) {
						surface[i][j] = new Cell(Values.MAX_LP, Values.MAX_MP);
					}
				}
			}
		} else {
			surface[rand.nextInt(rows)][rand.nextInt(columns)] = new Cell(Values.MAX_LP, Values.MAX_MP);
		}
	}
	
	/**
	 * Creates a new cell at the specified coordinates
	 * 
	 * @param coords coordinates
	 * @return      if it was possible to create the cell at the given coordinates
	 */
	public boolean createCell(Coords coords) {
		if (surface[coords.getRow()][coords.getColumn()] == null) {
			surface[coords.getRow()][coords.getColumn()] = new Cell(Values.MAX_LP, Values.MAX_MP);
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
	 * Try to move a cell to an adjacent position. If it gets moved, it maturates. If not, it loses a life.
	 * 
	 * @param coords coordinates
	 * @return      if it was possible to move the cell at the given coordinates
	 */
	public Coords moveCell(Coords coords){
		List<Coords> freeSpots = getAvailablePositions(coords);
		
		//System.out.println(freeSpots.size());
		//System.out.println(freeSpots);
		
		
		if (freeSpots.isEmpty()) { // If no available positions
			surface[coords.getRow()][coords.getColumn()].loseLp();
			return new Coords();
		} else { // If there is at least one available position
			Random rand = new Random();
			Coords chosenCoords = freeSpots.get(rand.nextInt(freeSpots.size()));
			surface[chosenCoords.getRow()][chosenCoords.getColumn()] = surface[coords.getRow()][coords.getColumn()];
			surface[coords.getRow()][coords.getColumn()] = null;
			surface[chosenCoords.getRow()][chosenCoords.getColumn()].maturate();
			return chosenCoords;
		}
		
	}
	
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
			for (int j = 0; j < columns; j++){
				if (surface[i][j] != null) {
					if (!movedCells.contains(new Coords(i, j))) {
						if(surface[i][j].getLp() == 0){
							surface[i][j] = null;
						}else if(surface[i][j].getMp() == 0){
							newCoords = cellMaturation(new Coords(i, j));
							//newCoords = moveCell(new Coords(i, j));
							if (!newCoords.isNullCoords()) {
								movedCells.add(new Coords(i, j));
								movedCells.add(newCoords);
							}
						}else {
							newCoords = moveCell(new Coords(i, j));
							if (Values.DEBUG) System.out.println("[Moved] (" + i + ", " + j + ") to " + newCoords);
							if (!newCoords.isNullCoords()) {
								movedCells.add(newCoords);
							}
						}
					}
				}
			}
		}
	}

	public String toString() {
		String board = "";
		for (int i = 0; i < rows; i++){
			board = board +  "|  ";
			for (int j = 0; j < columns; j++){
				if (surface[i][j] != null) {
					board = board + surface[i][j] + "  |  ";
				} else {
					board = board + "~~~" + "  |  ";
				}
			}
			board = board + "\n"; // System.out.println("\n");
		}
		
		return board;
	}
	

}

