package tp.pr1.logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import tp.pr1.utils.Coords;

/**
 * World class.
 * Contains a surface.
 * Handles the simulation of the game.
 */
public class World {
	private Surface surface;
	
	/**
	 * World constructor.
	 * Creates a surface.
	 */
	public World(){
		this(Values.BOARD_ROWS, Values.BOARD_COLS);
	}
	
	public World(int rows, int cols) {
		this.surface = new Surface(rows, cols);
	}
	
	/**
	 * Execute a simulation step
	 */
	public void evolve(){
		HashSet<Coords> movedCells = new HashSet<Coords>();
		Coords newCoords;
		for (int i = 0; i < Values.BOARD_ROWS; i++){
			for (int j = 0; j < Values.BOARD_COLS; j++){ // For every position
				if (! surface.isPositionEmpty(i, j)) { // If there is a cell (if the position is not empty)
					if (!movedCells.contains(new Coords(i, j))) { // If cell is not one moved to this position in this evolution step
						
						if(surface.getCell(new Coords(i, j)).getLp() == 0){ // Kill the cell if its lp is 0
							System.out.println("Cell at " + new Coords(i, j) + " dies of inactivity");
							surface.deleteCell(new Coords(i, j));
							
						}else if(surface.getCell(new Coords(i, j)).getMp() == 0){ // Complete its maturation if its mp is 0
							newCoords = cellMaturation(new Coords(i, j));
							if (!newCoords.isNullCoords()) { // If the cell could reproduce...
								System.out.println("New cell born at " + newCoords);
								movedCells.add(new Coords(i, j));
								movedCells.add(newCoords);
							} else {
								surface.deleteCell(new Coords(i, j));
								System.out.println("Cell at " + new Coords(i, j) + " dies on being unable to reproduce");
							}
							
						}else { // Try to move the cell
							surface.getCell(new Coords(i, j)).maturate();
							newCoords = moveCell(new Coords(i, j));
							if (!newCoords.isNullCoords()) { // If the cell could move...
								System.out.println("Cell at " + new Coords(i, j) + " moved to " + newCoords);
								movedCells.add(newCoords);
							}
						}
					}
				}
			}
		}
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
	 * Try to move a cell to an adjacent position. If it can't move, it loses a life.
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
	 * Try to reproduce a cell to an adjacent position. If it can't reproduce, it dies.
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
	 * Creates a new cell at the specified coordinates.
	 * Overloads: createCell(Coords coords)
	 * 
	 * @param row row coord
	 * @param col col coord
	 * @return      if it was possible to create the cell at the given coordinates
	 */
	public boolean createCell(int row, int col) {
		return createCell(new Coords(row, col));
	}
	
	/**
	 * Creates a new cell at the specified coordinates.
	 * 
	 * @param coords coordinates
	 * @return      if it was possible to create the cell at the given coordinates
	 */
	public boolean createCell(Coords coords) {
		return surface.createCell(coords);
	}
	
	/**
	 * Creates a new cell at the specified coordinates.
	 * Overloads: deleteCell(Coords coords)
	 * 
	 * @param row row coord
	 * @param col col coord
	 * @return      if it was possible to delete the cell at the given coordinates
	 */
	public boolean deleteCell(int row, int col) {
		return deleteCell(new Coords(row, col));
	}
	
	/**
	 * Creates a new cell at the specified coordinates
	 * 
	 * @param coords coordinates
	 * @return      if it was possible to delete the cell at the given coordinates
	 */
	public boolean deleteCell(Coords coords) {
		return surface.deleteCell(coords);
	}
	
	/**
	 * Asks the surface to initialize the board
	 */
	public void initWorld() {
		surface.initBoard();
	}
	
	/**
	 * Asks the surface to reset (empty) the board
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
