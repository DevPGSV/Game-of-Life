package esRuedaPGSV.GameOfLife;

public class World {
	private Surface surface;
	
	public World(){
		this.surface = new Surface(Values.BOARD_ROWS, Values.BOARD_COLS);
	}
	
	/**
	 * Execute a simulation step
	 *
	 */
	public void evolve(){
		//System.out.println("Evolve");
		surface.evolve();
	}
	
	/**
	 * Creates a new cell at the specified coordinates
	 * 
	 * @param coords coordinates
	 * @return      if it was possible to create the cell at the given coordinates
	 */
	public boolean createCell(Coords coords) {
		surface.createCell(coords);
		return false;
	}
	
	public void initWorld() {
		surface.initBoard();
	}
	public void cleanWorld() {
		surface.cleanBoard();
	}
	
	/**
	 * Creates a new cell at the specified coordinates
	 * 
	 * @param coords coordinates
	 * @return      if it was possible to delete the cell at the given coordinates
	 */
	public boolean deleteCell(Coords coords) {
		surface.deleteCell(coords);
		return false;
	}
	
	public String toString() {
		return surface.toString();
	}
	
}
