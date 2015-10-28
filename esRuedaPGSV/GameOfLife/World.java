package esRuedaPGSV.GameOfLife;

public class World {
	private Surface surface;
	
	public World(){
		this.surface = new Surface(4, 3);
	}
	
	/**
	 * Execute a simulation step
	 *
	 */
	public void evolve(){
		System.out.println("Evolve");
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
