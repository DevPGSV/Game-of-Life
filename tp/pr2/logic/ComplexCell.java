package tp.pr2.logic;

import tp.pr2.utils.Coords;
import tp.pr2.utils.Utils;
import tp.pr2.view.PrintSituation;

/**
 * <p>ComplexCell class.</p>
 * <p>Can eat edible cells. Once they have eaten enough, they burst.</p>
 */
public class ComplexCell extends Cell{
	
	/**
	 * <p>Eat Capacity</p>
	 */
	private int ec;
	
	/**
	 * <p>ComplexCell constructor</p>
	 * 
	 * @param ec initial Eat Capacity
	 */
	public ComplexCell(int ec){
		super();
		this.ec = ec;
		this.edible = false;
	}
	
	/**
	 * <p>Decrease Eat Capacity by 1</p>
	 * 
	 * @see tp.pr2.logic.ComplexCell#ec
	 */
	public void loseEc() {
		this.ec--;
	}
	
	/**
	 * <p>Eat Capacity getter</p>
	 * 
	 * @see tp.pr2.logic.ComplexCell#ec
	 * @return the Eat Capacity
	 */
	public int getEc() {
		return this.ec;
	}
	
	/**
	 * <p>Checks if the cell should burst (ie. if its ec is 0)</p>
	 * 
	 * @see tp.pr2.logic.ComplexCell#ec
	 * @return if (the cells ec == 0)
	 */
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
				PrintSituation.cellMoves(this.getClass().getSimpleName(), coords, newCoords);
			} else if (surface.getCell(newCoords).isEdible()){
				loseEc();
				if (shouldBurst()) {
					PrintSituation.eat(this.getClass().getSimpleName(), coords, surface.getCell(newCoords).getClass().getSimpleName(), newCoords, true);
					surface.deleteCell(newCoords);
					surface.deleteCell(coords);
				} else {
					PrintSituation.eat(this.getClass().getSimpleName(), coords, surface.getCell(newCoords).getClass().getSimpleName(), newCoords, false);
					surface.deleteCell(newCoords);
					surface.moveCell(coords, newCoords);
				}
				
			}
			return newCoords;
		} else
			return null;
}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return (char)183 + "{BLUE}" + (new Integer(getEc())).toString() + "{RESET}" + (char)183;
	}
}
