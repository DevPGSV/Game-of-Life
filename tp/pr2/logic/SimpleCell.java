package tp.pr2.logic;

import tp.pr2.utils.Coords;

public class SimpleCell extends Cell{

	public SimpleCell(int lp, int mp) {
		super(lp, mp);
		this.edible = true;
	}
	
	/* (non-Javadoc)
	 * @see tp.pr2.logic.Cell#isEdible()
	 */
	@Override
	public boolean isEdible() {
		return this.edible;
	}
	
	/* (non-Javadoc)
	 * @see tp.pr2.logic.Cell#executeMove(tp.pr2.utils.Coords, tp.pr2.logic.Surface)
	 */
	@Override
	public Coords executeMove(Coords coords, Surface surface) {
		// TODO Auto-generated method stub
		return null;
	}
}
