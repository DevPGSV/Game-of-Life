package esRuedaPGSV.GameOfLife;

public class Cell {
	private int lp;
	private int mp;
	private boolean movable;
	
	public Cell(int lp, int mp){
		this.lp = lp;
		this.mp = mp;
		this.movable = true;
	}
	
	public boolean isMovable() {
		return this.movable;
	}
	
	public void setMovable(boolean movable) {
		this.movable = movable;
	}
	
	
	/**
	 * Decrease one life.
	 *
	 * @return      if the cell has died
	 */
	public boolean loseLp(){
		/*
		boolean dead = false;
		this.lp= this.lp - 1;
		if (this.lp == 0){
			dead = true;
		}
		return dead;
		*/
		
		this.lp--;
		return (this.lp == 0);
	}
	
	/**
	 * Decrease one maturity point
	 *
	 * @return      if the cell is mature
	 */
	public boolean maturate(){
		this.mp--;
		return (this.mp == 0);
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
	
	/**
	 * String representation of the cell
	 *
	 * @return      a string representing the cell
	 */
	public String toString() {
		return (new Integer(getLp())).toString() + "-" + (new Integer(getMp())).toString();
	}
	
}
