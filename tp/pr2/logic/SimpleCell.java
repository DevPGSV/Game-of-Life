package tp.pr2.logic;

import tp.pr2.utils.Coords;
import tp.pr2.utils.Utils;

/**
 * <p>SimpleCell class.</p>
 * <p>Reproduce after some life cycle steps. Might die if they don't move enough.</p>
 */
public class SimpleCell extends Cell{
	
	/**
	 * <p>Life Points</p>
	 */
	private int lp;
	/**
	 * <p>Maduration Points</p>
	 */
	private int mp;
	
	/**
	 * <p>SimpleCell constructor</p>
	 * 
	 * @param lp initial Life Points
	 * @param mp initial Maduration Points
	 */
	public SimpleCell(int lp, int mp) {
		super();
		this.lp = lp;
		this.mp = mp;
		this.edible = true;
	}

	/* (non-Javadoc)
	 * @see tp.pr2.logic.Cell#executeMove(tp.pr2.utils.Coords, tp.pr2.logic.Surface)
	 */
	@Override
	public Coords executeMove(Coords coords, Surface surface) {
		if (shouldDie()) { // Kill the cell if its lp is 0
			System.out.println(this.getClass().getSimpleName() + " at " + coords + " dies of inactivity");
			//new Action(this).atCoordinates(coords).dies("inactivity").create();
			surface.deleteCell(coords);
			return null;
		} else if (shouldReproduce()) { // Complete its maturation if its mp is 0
			Coords newCoords = Utils.getRandomAvailablePosition(coords, surface, true);
			if (newCoords != null) {
				surface.deleteCell(coords); surface.createCell(coords);
				surface.createCell(newCoords);
				System.out.println(this.getClass().getSimpleName() + " at " + coords + " divided creating new cells at " + coords + " and " + newCoords);
				return newCoords;
			} else {
				surface.deleteCell(coords);
				System.out.println(this.getClass().getSimpleName() + " at " + coords + " dies on being unable to reproduce");
				return null;
			}
		} else { // Try to move the cell
			maturate();
			Coords newCoords = Utils.getRandomAvailablePosition(coords, surface, true);
			if (newCoords != null) {
				surface.moveCell(coords, newCoords);
				System.out.println(this.getClass().getSimpleName() + " at " + coords + " moved to " + newCoords);
				return newCoords;
			} else {
				loseLp();
				return null;
			}
		}
	}
	
	/**
	 * <p>Decrease one life</p>
	 * @see tp.pr2.logic.SimpleCell#lp
	 */
	public void loseLp(){
		this.lp--;
	}
	
	/**
	 * <p>Decrease one maturation point</p>
	 * @see tp.pr2.logic.SimpleCell#mp
	 */
	public void maturate(){
		this.mp--;
	}
	
	/**
	 * <p>lp getter</p>
	 * 
	 * @see tp.pr2.logic.SimpleCell#lp
	 * @return the life points
	 */
	public int getLp(){
		return lp;
	}
	
	/**
	 * <p>mp getter</p>
	 *
	 * @see tp.pr2.logic.SimpleCell#mp
	 * @return the maturation points
	 */
	public int getMp(){
		return mp;
	}
	
	/**
	 * <p>Checks if the cell should die (ie. if its life is 0)</p>
	 * 
	 * @see tp.pr2.logic.SimpleCell#lp
	 * @return if (the cells lp == 0)
	 */
	public boolean shouldDie() {
		return (this.lp == 0);
	}
	
	/**
	 * <p>Checks if the cell should reproduce (ie. if its mp is 0)</p>
	 * 
	 * @see tp.pr2.logic.SimpleCell#mp
	 * @return if (the cells mp == 0)
	 */
	public boolean shouldReproduce() {
		return (this.mp == 0);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "{CYAN}" + (new Integer(getLp())).toString() + "-" + (new Integer(getMp())).toString() + "{RESET}";
	}
}
