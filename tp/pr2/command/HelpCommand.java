package tp.pr2.command;

import tp.pr2.logic.World;
import tp.pr2.view.printer.Printer;

public class HelpCommand extends Command{

	@Override
	public void execute(World world) {
		Printer.getInstance().print(CommandParser.helpTextCommands());
	}

	@Override
	public Command parse(String[] commandString) {
		if ((commandString.length == 1) && (commandString[0].equalsIgnoreCase("help"))) return new HelpCommand();
		return null;
	}

	@Override
	public String helpText() {
		return "gets a help message";
	}

	@Override
	public String toString() {
		return "help";
	}
	
}
