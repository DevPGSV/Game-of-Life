package tp.pr3.command;

import java.io.IOException;

import tp.pr3.controller.Controller;
import tp.pr3.exceptions.ErrorOnCreateCellException;
import tp.pr3.exceptions.ErrorOnLoadException;
import tp.pr3.exceptions.ErrorOnSaveException;
import tp.pr3.exceptions.FileFormatException;
import tp.pr3.exceptions.InitialisationException;
import tp.pr3.exceptions.InvalidCoordsException;
import tp.pr3.exceptions.NoFileSelectedException;
import tp.pr3.exceptions.UnknownWorldTypeException;
import tp.pr3.logic.world.World;

public abstract class Command {
	/**
	 * <p>Executes a command</p>
	 * 
	 * @param world World in which the command is executed
	 * @param controller an instance of the controller
	 * @throws UnknownWorldTypeException Thrown when an unknown world type is found.
	 * @throws IOException If there is an IOException
	 * @throws FileFormatException If the file is not correctly formated
	 * @throws NumberFormatException If it can't convert string to int
	 * @throws ErrorOnSaveException If it can't save the game
	 * @throws NoFileSelectedException If no file is selected
	 * @throws ErrorOnLoadException If it can't load the game
	 * @throws ErrorOnCreateCellException If it can't create a cell
	 * @throws InvalidCoordsException If the coordinates are invalid
	 */
	public abstract void execute(World world, Controller controller) throws UnknownWorldTypeException, NumberFormatException, FileFormatException, IOException, ErrorOnSaveException, NoFileSelectedException, ErrorOnLoadException, ErrorOnCreateCellException, InvalidCoordsException;
	
	/**
	 * <p>Analyzes grammatically the command</p>
	 * 
	 * @param commandString the command as an array of words
	 * @return An object representing the command, or null if the command doesn't match
	 * @throws UnknownWorldTypeException Thrown when an unknown world type is found.
	 * @throws InitialisationException Thrown when the user tries to create a new game with more cells than positions.
	 */
	public abstract Command parse(String[] commandString) throws UnknownWorldTypeException, InitialisationException;
	
	/**
	 * <p>Return the help message for this command</p>
	 * 
	 * @return the help message for this command
	 */
	public abstract String helpText();
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public abstract String toString();
}
