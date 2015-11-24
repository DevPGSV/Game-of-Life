package tp.pr2.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import tp.pr2.utils.Coords;

/**
 * <p>World class</p>
 * <p>Contains a surface</p>
 * <p>Handles the simulation of the game</p>
 */
public class World {
	private Surface surface;
	
	/**
	 * <p>World default constructor</p>
	 * <p>Creates a surface</p>
	 */
	public World(){
		this(Values.BOARD_ROWS, Values.BOARD_COLS);
	}
	
	/**
	 * <p>World constructor</p>
	 * <p>Creates a surface with the specified dimensions</p>
	 * 
	 * @param rows number of rows of the surface to be created
	 * @param cols number of columns of the surface to be created
	 */
	public World(int rows, int cols) {
		this.surface = new Surface(rows, cols);
	}
	
	/**
	 * <p>Execute a simulation step</p>
	 */
	public void evolve(){
		HashSet<Coords> movedCells = new HashSet<Coords>();
		List<Coords> boardCoordinates = new ArrayList<Coords>();
		for (int i = 0; i < Values.BOARD_ROWS; i++)
			for (int j = 0; j < Values.BOARD_COLS; j++)
				boardCoordinates.add(new Coords(i, j));
		Collections.shuffle(boardCoordinates);
		
		Coords newCoords, currentCoords;
		for (int currentCoordsI = 0; currentCoordsI < boardCoordinates.size(); currentCoordsI++) { // For each (randomized) position in the board
			currentCoords = boardCoordinates.get(currentCoordsI);
			if (! surface.isPositionEmpty(currentCoords)) { // If there is a cell (if the position is not empty)
				if (!movedCells.contains(currentCoords)) { // If cell is not one moved to this position in this evolution step
					
					if(surface.getCell(currentCoords).getLp() == 0){ // Kill the cell if its lp is 0
						System.out.println("Cell at " + currentCoords + " dies of inactivity");
						surface.deleteCell(currentCoords);
						
					}else if(surface.getCell(currentCoords).getMp() == 0){ // Complete its maturation if its mp is 0
						newCoords = cellMaturation(currentCoords);
						if (!newCoords.isNullCoords()) { // If the cell could reproduce...
							System.out.println("New cell born at " + newCoords);
							movedCells.add(currentCoords);
							movedCells.add(newCoords);
						} else {
							surface.deleteCell(currentCoords);
							System.out.println("Cell at " + currentCoords + " dies on being unable to reproduce");
						}
					}else { // Try to move the cell
						surface.getCell(currentCoords).maturate();
						newCoords = moveCell(currentCoords);
						if (!newCoords.isNullCoords()) { // If the cell could move...
							System.out.println("Cell at " + currentCoords + " moved to " + newCoords);
							movedCells.add(newCoords);
						}
					}
				}
			}
		}
	}
	
	/**
	 * <p>Search for available positions around some given coordinates</p>
	 * 
	 * @param coords coordinates of the cell
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
					if((tempCoords.getRow() >= 0) && (tempCoords.getRow() < surface.getRows())) {
						if((tempCoords.getColumn() >= 0) && (tempCoords.getColumn() < surface.getColumns()))
						{
							if (surface.isPositionEmpty(tempCoords)) {
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
	 * <p>Try to move a cell to an adjacent position</p>
	 * <p>If it can't move, it loses a life</p>
	 * 
	 * @param coords coordinates
	 * @return      the coordinates where the cell gets moved.
	 */
	public Coords moveCell(Coords coords){
		List<Coords> freeSpots = getAvailablePositions(coords);
		
		if (freeSpots.isEmpty()) { // If no available positions
			surface.getCell(coords).loseLp();
			return new Coords();
		} else { // If there is at least one available position
			Random rand = new Random();
			Coords chosenCoords = freeSpots.get(rand.nextInt(freeSpots.size()));
			surface.createCell(chosenCoords, surface.getCell(coords)); // Clones cell from coords to chosenCoords
			surface.deleteCell(coords);
			return chosenCoords;
		}
		
	}
	
	/**
	 * <p>Try to reproduce a cell to an adjacent position</p>
	 * <p>If it can't reproduce, it dies.</p>
	 * 
	 * @param coords coordinates
	 * @return      the coordinates where the new cell appears.
	 */
	public Coords cellMaturation(Coords coords){
		List<Coords> freeSpots = getAvailablePositions(coords);

		if (freeSpots.isEmpty()) { // If no available positions
			return new Coords();
		} else { // If there is at least one available position
			Random rand = new Random();
			Coords chosenCoords = freeSpots.get(rand.nextInt(freeSpots.size()));
			surface.createCell(chosenCoords);
			surface.createCell(coords);
			return chosenCoords;
		}
		
	}
	
	/**
	 * <p>Creates a new cell at the specified coordinates</p>
	 * <p><b>Overloads: <i>createCell(Coords coords)</i></b></p>
	 * 
	 * @param row row coord
	 * @param col col coord
	 * @return      if it was possible to create the cell at the given coordinates
	 */
	public boolean createCell(int row, int col) {
		return createCell(new Coords(row, col));
	}
	
	/**
	 * <p>Creates a new cell at the specified coordinates</p>
	 * 
	 * @param coords coordinates
	 * @return      if it was possible to create the cell at the given coordinates
	 */
	public boolean createCell(Coords coords) {
		return surface.createCell(coords);
	}
	
	/**
	 * <p>Creates a new cell at the specified coordinates</p>
	 * <p><b>Overloads: <i>deleteCell(Coords coords)</i></b></p>
	 * 
	 * @param row row coord
	 * @param col col coord
	 * @return      if it was possible to delete the cell at the given coordinates
	 */
	public boolean deleteCell(int row, int col) {
		return deleteCell(new Coords(row, col));
	}
	
	/**
	 * <p>Creates a new cell at the specified coordinates</p>
	 * 
	 * @param coords coordinates
	 * @return      if it was possible to delete the cell at the given coordinates
	 */
	public boolean deleteCell(Coords coords) {
		return surface.deleteCell(coords);
	}
	
	/**
	 * <p>Asks the surface to initialize the board</p>
	 */
	public void initWorld() {
		surface.initBoard();
	}
	
	/**
	 * <p>Asks the surface to reset (empty) the board</p>
	 */
	public void cleanWorld() {
		surface.cleanBoard();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return surface.toString();
	}
	
}
