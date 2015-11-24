package tp.pr2.logic;

import tp.pr2.utils.Coords;

/**
 * <p>Cell class</p>
 * <p>A cell is an object with life points and maturation points</p>
 */
public abstract class Cell {
	private int lp;
	private int mp;
	protected boolean edible;
	
	
	/**
	 * <p>Cell constructor</p>
	 * 
	 * @param lp Life points
	 * @param mp Maturation points
	 */
	public Cell(int lp, int mp){
		this.lp = lp;
		this.mp = mp;

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
	public abstract boolean isEdible();
	
	/**
	 * <p>Decrease one life</p>
	 */
	public void loseLp(){
		this.lp--;
	}
	
	/**
	 * <p>Decrease one maturation point</p>
	 */
	public void maturate(){
		this.mp--;
	}
	
	/**
	 * <p>lp getter</p>
	 *
	 * @return      the life points
	 */
	public int getLp(){
		return lp;
	}
	
	/**
	 * <p>mp getter</p>
	 *
	 * @return      the maturation points
	 */
	public int getMp(){
		return mp;
	}
	
	/**
	 * <p>Checks if the cell should die (ie. if its life is 0)</p>
	 * 
	 * @return if (the cells lp == 0)
	 */
	public boolean shouldDie() {
		return (this.lp == 0);
	}
	
	/**
	 * <p>Checks if the cell should reproduce (ie. if its mp is 0)</p>
	 * 
	 * @return if (the cells mp == 0)
	 */
	public boolean shouldReproduce() {
		return (this.mp == 0);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return (new Integer(getLp())).toString() + "-" + (new Integer(getMp())).toString();
	}
	
}
