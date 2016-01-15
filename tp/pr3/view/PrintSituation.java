package tp.pr3.view;

import tp.pr3.utils.Coords;

public class PrintSituation {
	
	/**
	 * <p>Prints if cell dies from inactivity</p>
	 * 
	 * @param cellType describes the type of the cell
	 * @param coords the coordinates of the current cell
	 */
	public static void cellDiesOfInactivity(String cellType, Coords coords) {
		System.out.println(cellType + " at " + coords + " dies of inactivity");
	}
	
	/**
	 * <p>Prints if cell dies from being unable to reproduce</p>
	 * 
	 * @param cellType describes the type of the cell
	 * @param coords the coordinates of the current cell
	 */
	public static void cellDiesOfUnableToReproduce(String cellType, Coords coords) {
		System.out.println(cellType + " at " + coords + " dies on being unable to reproduce");
	}
	
	/**
	 * <p>Prints if cell reproduces</p>
	 * 
	 * @param cellType describes the type of the cell
	 * @param coords the coordinates of the current cell
	 * @param newCoords the coordinates of the new cell
	 */
	public static void cellReproduces(String cellType, Coords coords, Coords newCoords) {
		System.out.println(cellType + " at " + coords + " divided creating new cells at " + coords + " and " + newCoords);
	}
	
	/**
	 * <p>Prints if cell moves</p>
	 * 
	 * @param cellType describes the type of the cell
	 * @param coords the coordinates of the current cell
	 * @param newCoords the coordinates of the new position of the cell
	 */
	public static void cellMoves(String cellType, Coords coords, Coords newCoords) {
		System.out.println(cellType + " at " + coords + " moved to " + newCoords);
	}
	
	/**
	 * <p>Prints if cell eats</p>
	 * <p>If it bursts its displayed </p>
	 * 
	 * @param cellType describes the type of the cell
	 * @param coords the coordinates of the current cell
	 * @param eatenCellType describes the type of the eaten cell
	 * @param newCoords the coordinates of the eaten cell
	 * @param burst if the cell bursts
	 */
	public static void eat(String cellType, Coords coords, String eatenCellType, Coords newCoords, boolean burst) {
		System.out.println(cellType + " at " + coords + " moved to " + newCoords + " eating " + eatenCellType + (burst ? " and bursting" : ""));
	}
	
	
}
