package tp.pr2.logic;

import tp.pr2.utils.Coords;
import tp.pr2.utils.Utils;

public class ComplexCell extends Cell{
	
	private int ec; // Eat Capacity
	
	public ComplexCell(int ec){
		super();
		this.ec = ec;
		this.edible = false;
	}
	
	public void loseEc() {
		this.ec--;
	}
	
	public int getEc() {
		return this.ec;
	}
	
	public boolean shouldBurst() {
		return (this.ec == 0);
	}

	/* (non-Javadoc)
	 * @see tp.pr2.logic.Cell#executeMove(tp.pr2.utils.Coords, tp.pr2.logic.Surface)
	 */
	@Override
	public Coords executeMove(Coords coords, Surface surface) {
		Coords newCoords = Utils.getRandomAvailablePosition(coords, surface, false);
		if (newCoords != null) {
			if (surface.isPositionEmpty(newCoords)) {
				surface.moveCell(coords, newCoords);
				System.out.println(this.getClass().getSimpleName() + " at " + coords + " moved to " + newCoords);
			} else if (surface.getCell(newCoords).isEdible()){
				loseEc();
				if (shouldBurst()) {
					System.out.println(this.getClass().getSimpleName() + " at " + coords + " moved to " + newCoords + " eating " + surface.getCell(newCoords).getClass().getSimpleName() + " and bursting");
					surface.deleteCell(newCoords);
				} else {
					System.out.println(this.getClass().getSimpleName() + " at " + coords + " moved to " + newCoords + " eating " + surface.getCell(newCoords).getClass().getSimpleName());
					surface.deleteCell(newCoords);
					surface.moveCell(coords, newCoords);
				}
				
			}
			return newCoords;
		} else
			return null;
}
	
	public String toString() {
		return (char)183 + "{BLUE}" + (new Integer(getEc())).toString() + "{RESET}" + (char)183;
	}
}
