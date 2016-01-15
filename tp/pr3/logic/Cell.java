package tp.pr3.logic;

import tp.pr3.exceptions.InvalidCoordsException;
import tp.pr3.utils.Coords;

/**
 * <p>Cell abstract class.</p>
 * <p>Creature located in a surface.</p>
 */
public abstract class Cell {
	
	/**
	 * <p>Cell Edibility</p>
	 */
	protected boolean edible;
	
	/**
	 * <p>Executes a move</p>
	 * <p>Cells execute a step in their life cycle</p>
	 * <p>They might die, move, reproduce, burst, eat, idle, ...</p>
	 * 
	 * @param coords Origin coordinates
	 * @param surface Surface instance
	 * @return the destination of the cell (or null if it couldn't move)
	 * @throws InvalidCoordsException 
	 */
	public abstract Coords executeMove(Coords coords, Surface surface) throws InvalidCoordsException;
	
	
	/**
	 * <p>edible getter</p>
	 * 
	 * @see tp.pr2.logic.Cell#edible
	 * @return if the cell can be eaten
	 */
	public boolean isEdible(){ // Code to execute is the same for all classes extending this abstract class.
		return this.edible;
	}
}
