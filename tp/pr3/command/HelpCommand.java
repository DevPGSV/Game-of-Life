package tp.pr3.command;

import tp.pr3.controller.Controller;
import tp.pr3.logic.World;
import tp.pr3.view.Printer;

public class HelpCommand extends CommandWithoutCoords{

	@Override
	public void execute(World world, Controller controller) {
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
