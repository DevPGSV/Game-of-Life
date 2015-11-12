package tp.pr1.logic;

import java.util.HashSet;

import tp.pr1.utils.Coords;

public class World {
	private Surface surface;
	
	/**
	 * World constructor.
	 * Creates a surface.
	 */
	public World(){
		this.surface = new Surface(Values.BOARD_ROWS, Values.BOARD_COLS);
	}
	
	/**
	 * Execute a simulation step
	 */
	public void evolve(){
		HashSet<Coords> movedCells = new HashSet<Coords>();
		Coords newCoords;
		for (int i = 0; i < Values.BOARD_ROWS; i++){
			for (int j = 0; j < Values.BOARD_COLS; j++){ // For every position
				if (! surface.isPositionEmpty(new Coords(i, j))) { // If there is a cell (if the position is not empty)
					if (!movedCells.contains(new Coords(i, j))) { // If cell is not one moved to this position in this evolution step
						
						if(surface.getCell(new Coords(i, j)).getLp() == 0){ // Kill the cell if its lp is 0
							System.out.println("[ DIE ] (" + i + ", " + j + ")");
							surface.deleteCell(new Coords(i, j));
							
						}else if(surface.getCell(new Coords(i, j)).getMp() == 0){ // Complete its maturation if its mp is 0
							newCoords = surface.cellMaturation(new Coords(i, j));
							if (!newCoords.isNullCoords()) { // If the cell could reproduce...
								System.out.println("[MATUR] (" + i + ", " + j + ") to " + newCoords);
								movedCells.add(new Coords(i, j));
								movedCells.add(newCoords);
							}
							
						}else { // Try to move the cell
							surface.getCell(new Coords(i, j)).maturate();
							newCoords = surface.moveCell(new Coords(i, j));
							if (!newCoords.isNullCoords()) { // If the cell could move...
								System.out.println("[MOVED] (" + i + ", " + j + ") to " + newCoords);
								movedCells.add(newCoords);
							}
						}
					}
				}
			}
		}
		
		
		
		
		//surface.evolve();
	}
	
	/**
	 * Creates a new cell at the specified coordinates
	 * 
	 * @param coords coordinates
	 * @return      if it was possible to create the cell at the given coordinates
	 */
	public boolean createCell(Coords coords) {
		return surface.createCell(coords);
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
	
	public String toString() {
		return surface.toString();
	}
	
}
