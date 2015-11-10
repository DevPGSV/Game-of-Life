package esRuedaPGSV.GameOfLife;

public class Cell {
	private int lp;
	private int mp;
	
	
	/**
	 * Cell constructor
	 * 
	 * @param lp Life points
	 * @param mp Maturation points
	 */
	public Cell(int lp, int mp){
		this.lp = lp;
		this.mp = mp;

	}
	
	/**
	 * Decrease one life.
	 *
	 * @return      if the cell has died
	 */
	public void loseLp(){
		this.lp--;
	}
	
	/**
	 * Decrease one maturity point
	 *
	 * @return      if the cell is mature
	 */
	public void maturate(){
		this.mp--;
	}
	
	/**
	 * lp getter
	 *
	 * @return      the life points
	 */
	public int getLp(){
		return lp;
	}
	
	/**
	 * mp getter
	 *
	 * @return      the maturity points
	 */
	public int getMp(){
		return mp;
	}
	
	public String toString() {
		return (new Integer(getLp())).toString() + "-" + (new Integer(getMp())).toString();
	}
	
}
