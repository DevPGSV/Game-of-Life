package tp.pr2.command;

import tp.pr2.logic.World;

public abstract class Command {
	/**
	 * <p>Executes a command</p>
	 * 
	 * @param world World in which the command is executed
	 */
	public abstract void execute(World world);
	
	/**
	 * <p>Analyzes grammatically the command</p>
	 * 
	 * @param commandString the command as an array of words
	 * @return An object representing the command, or null if the command doesn't match
	 */
	public abstract Command parse(String[] commandString);
	
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
