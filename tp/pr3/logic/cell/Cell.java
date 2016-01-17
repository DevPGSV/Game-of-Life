package tp.pr3.logic.cell;

import java.io.IOException;
import java.io.Writer;

import tp.pr3.logic.surface.Surface;
import tp.pr3.utils.Coords;

/**
 * <p>Cell abstract class.</p>
 * <p>Creature located in a surface.</p>
 */
public interface Cell {
	
	/**
	 * <p>Cell Edibility</p>
	 */
	//protected boolean edible;
	
	/**
	 * <p>Executes a move</p>
	 * <p>Cells execute a step in their life cycle</p>
	 * <p>They might die, move, reproduce, burst, eat, idle, ...</p>
	 * 
	 * @param coords Origin coordinates
	 * @param surface Surface instance
	 * @return the destination of the cell (or null if it couldn't move)
	 */
	public abstract Coords executeMove(Coords coords, Surface surface);
	
	
	/**
	 * <p>edible getter</p>
	 * 
	 * @see tp.pr2.logic.Cell#edible
	 * @return if the cell can be eaten
	 */
	public boolean isEdible();
	
	//public void load(File file, Surface surface);
	public void save(Writer fileWriter) throws IOException;
}
