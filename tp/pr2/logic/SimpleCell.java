package tp.pr2.logic;

import tp.pr2.utils.Coords;
import tp.pr2.utils.Utils;

public class SimpleCell extends Cell{

	public SimpleCell(int lp, int mp) {
		super(lp, mp);
		this.edible = true;
	}

	/* (non-Javadoc)
	 * @see tp.pr2.logic.Cell#executeMove(tp.pr2.utils.Coords, tp.pr2.logic.Surface)
	 */
	@Override
	public Coords executeMove(Coords coords, Surface surface) {
		Coords newCoords = Utils.getRandomAvailablePosition(coords, surface);
		if (newCoords != null) {
			surface.createCell(newCoords, this); // Clones cell from coords to chosenCoords
			surface.deleteCell(coords);
			return newCoords;
		} else {
			loseLp();
			return new Coords();
		}
	}
	
	public String toString() {
		return (new Integer(getLp())).toString() + "-" + (new Integer(getMp())).toString();
	}
}
