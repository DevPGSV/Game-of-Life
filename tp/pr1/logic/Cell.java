package tp.pr1.logic;

/**
 * <p>Cell class</p>
 * <p>A cell is an object with life points and maturation points</p>
 */
public class Cell {
	private int lp;
	private int mp;
	
	
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return (new Integer(getLp())).toString() + "-" + (new Integer(getMp())).toString();
	}
	
}
