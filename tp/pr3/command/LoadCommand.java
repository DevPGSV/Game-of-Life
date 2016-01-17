package tp.pr3.command;

import java.io.File;
import java.io.IOException;

import tp.pr3.controller.Controller;
import tp.pr3.exceptions.ErrorOnLoadException;
import tp.pr3.exceptions.FileFormatException;
import tp.pr3.exceptions.NoFileSelectedException;
import tp.pr3.exceptions.UnknownWorldTypeException;
import tp.pr3.logic.world.World;
import tp.pr3.utils.Utils;

public class LoadCommand extends Command{
	
	private File file;
	
	public LoadCommand(){}
	
	private LoadCommand(File file) {
		this.file = file;
	}

	@Override
	public void execute(World world, Controller controller) throws NumberFormatException, FileFormatException, IOException, NoFileSelectedException, ErrorOnLoadException {
		if (this.file == null) {
			try {
				this.file = Utils.askFileOpen();
			} catch (NoFileSelectedException e) {
				throw new NoFileSelectedException("Load command cancelled");
			}
		}
		controller.load(this.file);
	}

	@Override
	public Command parse(String[] commandString) throws UnknownWorldTypeException {
		if (commandString[0].equalsIgnoreCase(this.toString())) {
			if (commandString.length == 1)
				return new LoadCommand();
			else
				return new LoadCommand(new File(commandString[1]));
		}
		return null;
	}

	@Override
	public String helpText() {
		return "";
	}

	@Override
	public String toString() {
		return "load";
	}

}
