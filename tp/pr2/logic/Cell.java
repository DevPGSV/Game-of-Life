package tp.pr2.logic;

import tp.pr2.utils.Coords;

/**
 * <p>Cell class</p>
 * <p>A cell is an object with life points and maturation points</p>
 */
public abstract class Cell {
	
	protected boolean edible;
	
	/**
	 * <p>Cell constructor</p>
	 * 
	 * @param lp Life points
	 * @param mp Maturation points
	 */
	public Cell(){
		
	}
	
	/**
	 * <p>Executes a move</p>
	 * 
	 * @param coords Origin coordinates
	 * @param surface Surface instance
	 * @return the destination of the cell (or null if it couldn't move)
	 */
	public abstract Coords executeMove(Coords coords, Surface surface);
	
	
	/**
	 * <p>edible getter</p>
	 * 
	 * @return if the cell can be eaten
	 */
	public boolean isEdible(){
		return this.edible;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/*
	public String toString() {
		return (new Integer(getLp())).toString() + "-" + (new Integer(getMp())).toString();
	}
	*/
}
