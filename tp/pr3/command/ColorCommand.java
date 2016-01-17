package tp.pr3.command;

import java.io.IOException;
import tp.pr3.controller.Controller;
import tp.pr3.exceptions.ErrorOnLoadException;
import tp.pr3.exceptions.FileFormatException;
import tp.pr3.exceptions.NoFileSelectedException;
import tp.pr3.exceptions.UnknownWorldTypeException;
import tp.pr3.logic.world.World;
import tp.pr3.view.Ansii;
import tp.pr3.view.Printer;

public class ColorCommand extends Command{
	
	private String color;
	
	public ColorCommand(){}
	
	private ColorCommand(String color) {
		this.color = color;
	}

	@Override
	public void execute(World world, Controller controller) throws NumberFormatException, FileFormatException, IOException, NoFileSelectedException, ErrorOnLoadException {
		Ansii.resetCode = this.color;
		Printer.getInstance().print("{RESET}");
	}

	@Override
	public Command parse(String[] commandString) throws UnknownWorldTypeException {
		if (commandString[0].equalsIgnoreCase(this.toString())) {
			return new ColorCommand(Printer.getInstance().patternReplace(commandString[1]));
		}
		return null;
	}

	@Override
	public String helpText() {
		return "{BOLD}{PURPLE}" +  this.toString().toUpperCase() + " {color} {RESET}	sets the default color";
	}

	@Override
	public String toString() {
		return "color";
	}

}
