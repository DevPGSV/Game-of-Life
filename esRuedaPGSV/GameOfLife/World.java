package esRuedaPGSV.GameOfLife;

public class World {
	private Surface surface;
	
	public World(){
		
	}
	
	/**
	 * Execute a simulation step
	 *
	 */
	public void evolve(){
		
	}
	
	/**
	 * Creates a new cell at the specified coordinates
	 * 
	 * @param coords coordinates
	 * @return      if it was possible to create the cell at the given coordinates
	 */
	public boolean createCell(Coords coords) {
		return false;
	}
	
	/**
	 * Creates a new cell at the specified coordinates
	 * 
	 * @param coords coordinates
	 * @return      if it was possible to delete the cell at the given coordinates
	 */
	public boolean deleteCell(Coords coords) {
		return false;
	}
	
}
