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
	 * @throws UnknownWorldTypeException 
	 * @throws IOException 
	 * @throws FileFormatException 
	 * @throws NumberFormatException 
	 * @throws ErrorOnSaveException 
	 * @throws NoFileSelectedException 
	 * @throws ErrorOnLoadException 
	 * @throws ErrorOnCreateCellException 
	 * @throws InvalidCoordsException 
	 */
	public abstract void execute(World world, Controller controller) throws UnknownWorldTypeException, NumberFormatException, FileFormatException, IOException, ErrorOnSaveException, NoFileSelectedException, ErrorOnLoadException, ErrorOnCreateCellException, InvalidCoordsException;
	
	/**
	 * <p>Analyzes grammatically the command</p>
	 * 
	 * @param commandString the command as an array of words
	 * @return An object representing the command, or null if the command doesn't match
	 * @throws UnknownWorldTypeException 
	 * @throws InitialisationException 
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
