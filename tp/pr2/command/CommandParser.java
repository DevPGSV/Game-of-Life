package tp.pr2.command;

import java.util.Arrays;
import java.util.List;

public class CommandParser {
	
	private static List<Command> availableCommands = Arrays.asList(
			new CleanCommand(),
			new CreateCommand(),
			new DeleteCommand(),
			new ExitCommand(),
			new HelpCommand(),
			new InitCommand(),
			new StepCommand()
		);
	
	public static String helpTextCommands() {
		StringBuilder helpText = new StringBuilder();
		helpText.append("AVAILABLE COMMANDS: \n");
		for(Command command : availableCommands) {
			helpText.append("   {BOLD}" +  command.toString().toUpperCase() + "{RESET}  " + command.helpText() + ".\n");
		}
		return helpText.toString();
	}
	
	public static Command parseCommand(String[] commandString) {
		Command cObject;
		for(Command command : availableCommands) {
			cObject = command.parse(commandString);
			if (cObject != null) return cObject;
		}
		return null;
	}
}
