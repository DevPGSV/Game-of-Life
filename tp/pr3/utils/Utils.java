package tp.pr3.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import tp.pr3.exceptions.NoFileSelectedException;
import tp.pr3.logic.surface.Surface;
import tp.pr3.utils.gol.GameOfLifeFileView;

public class Utils {
	private static List<String> extensions = Arrays.asList("gol", "gameoflife");
	
	
	/**
	 * <p>Search for positions around some given coordinates</p>
	 * 
	 * @param coords specified coordinates
	 * @param surface surface instance
	 * @param checkIfOccupied if occupied positions should be skipped
	 * @return a coordinate list with positions around the specified coordinates
	 */
	public static List<Coords> getAvailablePositions(Coords coords, Surface surface, boolean checkIfOccupied){
		Coords tempCoords = new Coords(0,0);
		List<Coords> positionsAround = new ArrayList<Coords>();
		//System.out.println("=> " + coords);
		for (int i = -1; i <= 1; i++){
			for (int j = -1; j <= 1; j++){
				tempCoords.setRow(coords.getRow() + i);
				tempCoords.setColumn(coords.getColumn() + j);
				if(tempCoords.getRow() != coords.getRow() || (tempCoords.getColumn() != coords.getColumn()))
					if((tempCoords.getRow() >= 0) && (tempCoords.getRow() < surface.getRows())) 
						if((tempCoords.getColumn() >= 0) && (tempCoords.getColumn() < surface.getColumns()))
							if (!checkIfOccupied || surface.isPositionEmpty(tempCoords))
								positionsAround.add(new Coords(tempCoords)); // Add available position to freeSpots list
			}
		}
		return positionsAround;
	}
	
	/**
	 * <p>Gets a possible movement for a cell at the specified coordinates</p>
	 * 
	 * @param coords specified coordinates
	 * @param surface surface instance
	 * @param checkIfOccupied if occupied positions should be skipped
	 * @return a random position from getAvailablePositions()
	 */
	public static Coords getRandomAvailablePosition(Coords coords, Surface surface, boolean checkIfOccupied) {
		List<Coords> positionsAround = Utils.getAvailablePositions(coords, surface, checkIfOccupied);
		
		if (positionsAround.isEmpty()) { // If no available positions
			return null;
		} else { // If there is at least one available position
			return positionsAround.get(SingleRandom.getInstance().getRandom().nextInt(positionsAround.size()));
		}
	}
	
	public static List<Coords> getAllPositions(Surface surface){
		List<Coords> positionsList = new ArrayList<Coords>();
		
		for (int i = 0; i < surface.getRows(); i++){
			for (int j = 0; j < surface.getColumns(); j++){
				positionsList.add(new Coords(i, j));
			}
		}
		return positionsList;
	}
	
	public static List<Coords> getAllPositionsShuffled(Surface surface){
		List<Coords> positionsList = getAllPositions(surface);
		Coords tmpCoords;
		int randomPos;
		for (int i = 0; i < positionsList.size(); i++) {
			randomPos = SingleRandom.getInstance().getRandom().nextInt(positionsList.size());
			tmpCoords = positionsList.get(randomPos);
			positionsList.set(randomPos, positionsList.get(i));
			positionsList.set(i, tmpCoords);
		}
		return positionsList;
	}
	
	public static File askFileOpen() throws NoFileSelectedException {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	    fileChooser.setFileFilter(
    		new FileNameExtensionFilter(
		        "Game of Life save files",
		        "gol", "gameoflife"
	        )
		);
	    fileChooser.setAcceptAllFileFilterUsed(false);
	    fileChooser.setFileView(new GameOfLifeFileView());
	    int returnVal = fileChooser.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	return fileChooser.getSelectedFile();
	    }
	    throw new NoFileSelectedException("");
	}
	
	public static File askFileSave() throws NoFileSelectedException {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	    fileChooser.setFileFilter(
    		new FileNameExtensionFilter(
		        "Game of Life save files (.gol)",
		        "gol", "gameoflife"
	        )
		);
	    fileChooser.setAcceptAllFileFilterUsed(false);
	    fileChooser.setFileView(new GameOfLifeFileView());
	    int returnVal = fileChooser.showSaveDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	return fileChooser.getSelectedFile();
	    }
	    throw new NoFileSelectedException("");
	}
	
	public static File checkGolExtension(File file) {
		if (!checkIfCorrectExtension(file.getAbsolutePath())) {
    		return new File(file.getAbsolutePath() + ".gol");
    	}
		return file;
	}
	
	public static boolean checkIfCorrectExtension(String fileName) {
        int i = fileName.lastIndexOf('.');
        if (i > 0 &&  i < fileName.length() - 1) {
        	if (extensions.contains(fileName.substring(i+1).toLowerCase())) {
        		return true;
        	}
        }
        return false;
	}
}
