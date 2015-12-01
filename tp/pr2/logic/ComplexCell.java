package tp.pr2.logic;

import tp.pr2.utils.Coords;

public class ComplexCell extends Cell{
	
	public ComplexCell(int lp, int mp){
		super(lp, mp);
		this.edible = false;
	}

	/* (non-Javadoc)
	 * @see tp.pr2.logic.Cell#executeMove(tp.pr2.utils.Coords, tp.pr2.logic.Surface)
	 */
	@Override
	public Coords executeMove(Coords coords, Surface surface) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString() {
		return (new Integer(getLp())).toString() + "+" + (new Integer(getMp())).toString();
	}
}
