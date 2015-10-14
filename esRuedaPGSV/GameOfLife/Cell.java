package esRuedaPGSV.GameOfLife;

public class Cell {
	private int lp;
	private int mp;
	
	public Cell(int lp, int mp){
		this.lp = lp;
		this.mp = mp;
	}
	
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
	
	public boolean maturate(){
		this.mp--;
		return (this.mp == 0);
	}
	
	public int getLp(){
		return lp;
	}
	
	public int getMp(){
		return mp;
	}
	
	public String toString() {
		return (new Integer(getLp())).toString() + "-" + (new Integer(getMp())).toString();
	}
	
}
