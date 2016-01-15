package tp.pr3.command;

import tp.pr3.controller.Controller;
import tp.pr3.exceptions.InvalidCoordsException;
import tp.pr3.logic.World;

public abstract class Command {
	/**
	 * <p>Executes a command</p>
	 * 
	 * @param world World in which the command is executed
	 * @throws InvalidCoordsException 
	 */
	public abstract void execute(World world, Controller controller) throws InvalidCoordsException;
	
	/**
	 * <p>Analyzes grammatically the command</p>
	 * 
	 * @param commandString the command as an array of words
	 * @return An object representing the command, or null if the command doesn't match
	 * @throws InvalidCoordsException 
	 */
	public abstract Command parse(String[] commandString) throws InvalidCoordsException;
	
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
