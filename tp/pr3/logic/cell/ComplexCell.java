package tp.pr3.logic.cell;

import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

import tp.pr3.logic.surface.Surface;
import tp.pr3.utils.Coords;
import tp.pr3.utils.Utils;
import tp.pr3.view.PrintSituation;

/**
 * <p>ComplexCell class.</p>
 * <p>Can eat edible cells. Once they have eaten enough, they burst.</p>
 */
public class ComplexCell implements Cell{
	
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
	}
	
	/**
	 * <p>Decrease Eat Capacity by 1</p>
	 * 
	 * @see tp.pr3.logic.cell.ComplexCell#ec
	 */
	public void loseEc() {
		this.ec--;
	}
	
	/**
	 * <p>Eat Capacity getter</p>
	 * 
	 * @see tp.pr3.logic.cell.ComplexCell#ec
	 * @return the Eat Capacity
	 */
	public int getEc() {
		return this.ec;
	}
	
	/**
	 * <p>Checks if the cell should burst (ie. if its ec is 0)</p>
	 * 
	 * @see tp.pr3.logic.cell.ComplexCell#ec
	 * @return if (the cells ec == 0)
	 */
	public boolean shouldBurst() {
		return (this.ec == 0);
	}

	/* (non-Javadoc)
	 * @see tp.pr2.logic.cell.Cell#executeMove(tp.pr2.utils.Coords, tp.pr2.logic.Surface)
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
	
	@Override
	public boolean isEdible() {
		return false;
	}

	@Override
	public void save(Writer fileWriter) throws IOException {
		fileWriter.write("complex " + (new Integer(getEc())).toString());
	}
	
	/**
	 * Load a complex cell from a file
	 * @param fileReader scanner to use as input to read a serialized complex cell
	 * @return the complex cell object
	 */
	public static ComplexCell load(Scanner fileReader) {
		int ec = fileReader.nextInt(); fileReader.nextLine();
		return new ComplexCell(ec);
	}
}
