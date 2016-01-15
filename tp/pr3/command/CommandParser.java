package tp.pr3.command;

import java.util.Arrays;
import java.util.List;

import tp.pr3.exceptions.InvalidCommandException;
import tp.pr3.exceptions.InvalidCoordsException;

public class CommandParser {
	
	private static List<Command> availableCommands = Arrays.asList(
		new CleanCommand(),
		new CreateComplexCommand(),
		new CreateSimpleCommand(),
		new DeleteCommand(),
		new ExitCommand(),
		new HelpCommand(),
		new InitCommand(),
		new InitSizeCommand(),
		new StepCommand()
	);
	
	/**
	 * <p>Returns the help for all commands</p>
	 * 
	 * @return the help for all commands
	 */
	public static String helpTextCommands() {
		StringBuilder helpText = new StringBuilder();
		helpText.append("AVAILABLE COMMANDS: \n");
		for(Command command : availableCommands) {
			helpText.append("   " + command.helpText() + ".\n");
		}
		return helpText.toString();
	}
	
	/**
	 * <p>Return an object representing the command, or null if the command doesn't exist</p>
	 * 
	 * @param commandString the command as an array of words
	 * @return an object representing the command, or null if the command doesn't exist
	 * @throws InvalidCommandException 
	 * @throws InvalidCoordsException 
	 */
	public static Command parseCommand(String[] commandString) throws InvalidCommandException, InvalidCoordsException {
		Command cObject;
		for(Command command : availableCommands) {
			cObject = command.parse(commandString);
			if (cObject != null) return cObject;
		}
		throw new InvalidCommandException("Invalid Command.");
	}
}
