package tp.pr3.logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import tp.pr3.utils.Coords;
import tp.pr3.utils.SingleRandom;

/**
 * <p>World class.</p>
 * <p>Contains a surface.</p>
 * <p>Handles the simulation of the game.</p>
 */
public abstract class World implements WorldType {
	
	private Surface surface;
	private boolean simulationFinished;
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
		createNewDimensionedSurface(rows, cols);
		this.simulationFinished = false;
	}
	
	/**
	 * <p>Creates a surface with the specified dimensions</p>
	 * 
	 * @param rows number of rows of the surface to be created
	 * @param cols number of columns of the surface to be created
	 */
	public void createNewDimensionedSurface(int rows, int cols) {
		if ((rows < 0) || (cols < 0)) this.surface = new Surface(Values.BOARD_ROWS, Values.BOARD_COLS);
		else this.surface = new Surface(rows, cols);
	}
	
	/**
	 * <p>Checks if the simulation is finished</p>
	 * 
	 * @return if the simulation is finished
	 */
	public boolean isSimulationFinished() {
		return this.simulationFinished;
	}
	
	/**
	 * <p>Sets the simulation as finished</p> 
	 */
	public void setSimulationFinished() {
		this.simulationFinished = true;
	}
	
	/**
	 * <p>Execute a simulation step</p>
	 * 
	 * @return any messages about events in the current simulation step.
	 */
	public String evolve(){
		StringBuilder returnedMessages = new StringBuilder();
		HashSet<Coords> movedCells = new HashSet<Coords>();
		List<Coords> boardCoordinates = new ArrayList<Coords>();
		for (int i = 0; i < surface.getRows(); i++)
			for (int j = 0; j < surface.getColumns(); j++)
				boardCoordinates.add(new Coords(i, j));
		
		Coords newCoords, currentCoords;
		for (int currentCoordsI = 0; currentCoordsI < boardCoordinates.size(); currentCoordsI++) { // For each (randomized) position in the board
			currentCoords = boardCoordinates.get(currentCoordsI);
			if (! surface.isPositionEmpty(currentCoords)) { // If there is a cell (if the position is not empty)
				if (!movedCells.contains(currentCoords)) { // If cell is not one moved to this position in this evolution step
					newCoords = surface.runCell(currentCoords);
					if (newCoords != null) movedCells.add(newCoords);
				}
			}
		}
		return returnedMessages.toString();
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
	 * 
	 * @param coords coordinates
	 * @param cell cell to place in the specified coordinates
	 * @return      if it was possible to create the cell at the given coordinates
	 */
	public boolean createCell(Coords coords, Cell cell) {
		return surface.createCell(coords, cell);
	}
	
	/**
	 * <p>Deletes a new cell at the specified coordinates</p>
	 * <p><b>Overloads: <i>deleteCell(Coords coords)</i></b></p>
	 * 
	 * @see tp.pr2.logic.World#deleteCell(Coords)
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
	 * <p>Initializes the board with cells at random positions</p>
	 * 
	 * @param percentage approximate percentage of cells
	 */
	public void initWorld(int percentage) {
		surface.cleanBoard();
		
		for (int i = 0; i < surface.getRows(); i++){
			for (int j = 0; j < surface.getColumns(); j++){
				if (SingleRandom.getInstance().getRandom().nextInt(101) <= percentage) {
					surface.createCell(new Coords(i,  j), (SingleRandom.getInstance().getRandom().nextInt(2) == 0 ? new SimpleCell(Values.MAX_LP, Values.MAX_MP) : new ComplexCell(Values.MAX_EAT)));
					//surface[i][j] = (rand.nextInt(2) == 0 ? new SimpleCell(Values.MAX_LP, Values.MAX_MP) : new ComplexCell(Values.MAX_EAT));
				}
			}
		}
		
	}
	
	/**
	 * <p>Initializes the world with cells at random positions</p>
	 * <p><b>Overloads: <i>initWorld(int percentage)</i></b></p>
	 * <p>calls <i>initWorld</i> with a percentage of 50</p>
	 * @see tp.pr3.logic.World#initWorld(int)
	 */
	public void initWorld() {
		initWorld(surface.getRows() * surface.getColumns() / 4);
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
