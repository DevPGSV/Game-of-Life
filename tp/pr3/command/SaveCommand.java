package tp.pr3.command;

import java.io.File;
import java.io.IOException;

import tp.pr3.controller.Controller;
import tp.pr3.exceptions.ErrorOnSaveException;
import tp.pr3.exceptions.FileFormatException;
import tp.pr3.exceptions.NoFileSelectedException;
import tp.pr3.exceptions.UnknownWorldTypeException;
import tp.pr3.logic.world.World;
import tp.pr3.utils.Utils;

public class SaveCommand extends Command{
	
	private File file;
	
	/**
	 *  <p>Save Command public contructor</p>
	 */
	public SaveCommand(){}
	
	/**
	 * <p>Save Command private contructor</p>
	 * @param file output file
	 */
	private SaveCommand(File file) {
		this.file = file;
	}

	@Override
	public void execute(World world, Controller controller) throws NumberFormatException, FileFormatException, IOException, ErrorOnSaveException, NoFileSelectedException {
		if (this.file == null) {
			try {
				this.file = Utils.askFileSave();
			} catch (NoFileSelectedException e) {
				throw new NoFileSelectedException("Save command cancelled");
			}
		}
		controller.save(Utils.checkGolExtension(this.file));
	}

	@Override
	public Command parse(String[] commandString) throws UnknownWorldTypeException {
		if (commandString[0].equalsIgnoreCase(this.toString())) {
			if (commandString.length == 1)
				return new SaveCommand();
			else
				return new SaveCommand(new File(commandString[1]));
		}
		return null;
	}

	@Override
	public String helpText() {
		return "{BOLD}{PURPLE}" +  this.toString().toUpperCase() + " <file> {RESET}		saves game to file";
	}

	@Override
	public String toString() {
		return "save";
	}

}
