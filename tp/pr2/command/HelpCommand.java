package tp.pr2.command;

import tp.pr2.logic.World;
import tp.pr2.view.printer.Printer;

public class HelpCommand extends CommandWithoutCoords{

	@Override
	public void execute(World world) {
		Printer.getInstance().print(CommandParser.helpTextCommands());
	}

	@Override
	public String helpText() {
		return "{BOLD}" +  this.toString().toUpperCase() + " {RESET}		gets a help message";
	}

	@Override
	public String toString() {
		return "help";
	}

	@Override
	public CommandWithoutCoords createInstance() {
		return new HelpCommand();
	}
	
}
