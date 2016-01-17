package tp.pr3.logic.world;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import tp.pr3.exceptions.FileFormatException;
import tp.pr3.logic.cell.Cell;
import tp.pr3.logic.surface.Surface;
import tp.pr3.utils.Coords;
import tp.pr3.utils.Values;

/**
 * <p>World class.</p>
 * <p>Contains a surface.</p>
 * <p>Handles the simulation of the game.</p>
 */
public abstract class World implements WorldType {
	
	protected Surface surface;
	
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
	}
	
	public World(Surface surface) {
		this.surface = surface;
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
	 * <p>Asks the surface to initialize the board</p>
	 */
	public abstract void initWorld();
	
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
	
	public abstract String getWorldTypeAsString();
	
	public static World load(Scanner fileReader) throws FileFormatException, NumberFormatException, IOException {
		World world;
		
		String worldType = fileReader.nextLine();
		//int rows = fileReader.nextInt(); fileReader.nextLine();
		//int cols = fileReader.nextInt(); fileReader.nextLine();
		
		if (worldType.equals("simple")) {
			world = new SimpleWorld(Surface.load(fileReader));
		} else if (worldType.equals("complex")){
			world = new ComplexWorld(Surface.load(fileReader));
		} else {
			throw new FileFormatException("Unknown world type: " + worldType);
		}
		
		return world;
	}
	
	public void save(Writer fileWriter) throws IOException {
		fileWriter.write(getWorldTypeAsString() + System.lineSeparator());
		fileWriter.write(surface.getRows() + System.lineSeparator());
		fileWriter.write(surface.getColumns() + System.lineSeparator());
		surface.save(fileWriter);
	}
	
	public int getRows() {
		return surface.getRows();
	}
	
	public int getColumns() {
		return surface.getColumns();
	}
	
	public boolean checkIfValidCoords(Coords coords) {
		return surface.checkIfValidCoords(coords);
	}
	
}
