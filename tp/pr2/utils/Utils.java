package tp.pr2.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tp.pr2.logic.Surface;

public class Utils {
	
	
	/**
	 * <p>Search for positions around some given coordinates</p>
	 * 
	 * @param coords specified coordinates
	 * @param surface surface instance
	 * @return      a coordinate list with positions around the specified coordinates
	 */
	public static List<Coords> getAvailablePositions(Coords coords, Surface surface){
		Coords tempCoords = new Coords(0,0);
		List<Coords> positionsAround = new ArrayList<Coords>();
		//System.out.println("=> " + coords);
		for (int i = -1; i <= 1; i++){
			for (int j = -1; j <= 1; j++){
				tempCoords.setRow(coords.getRow() + i);
				tempCoords.setColumn(coords.getColumn() + j);
				if(tempCoords.getRow() != coords.getRow() || (tempCoords.getColumn() != coords.getColumn())){
					if((tempCoords.getRow() >= 0) && (tempCoords.getRow() < surface.getRows())) {
						if((tempCoords.getColumn() >= 0) && (tempCoords.getColumn() < surface.getColumns()))
						{
							positionsAround.add(new Coords(tempCoords)); // Add available position to freeSpots list
						}
					}
				}
			}
		}
		return positionsAround;
	}
	
	/**
	 * <p>Gets a possible movement for a cell at the specified coordinates</p>
	 * 
	 * @param coords 
	 * @param surface
	 * @return
	 */
	public static Coords getRandomAvailablePosition(Coords coords, Surface surface) {
		List<Coords> positionsAround = Utils.getAvailablePositions(coords, surface);
		
		if (positionsAround.isEmpty()) { // If no available positions
			return null;
		} else { // If there is at least one available position
			Random rand = new Random();
			return positionsAround.get(rand.nextInt(positionsAround.size()));
		}
	}
}
