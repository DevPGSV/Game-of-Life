package tp.pr3.command;

import java.io.IOException;
import tp.pr3.controller.Controller;
import tp.pr3.exceptions.ErrorOnLoadException;
import tp.pr3.exceptions.FileFormatException;
import tp.pr3.exceptions.NoFileSelectedException;
import tp.pr3.exceptions.UnknownWorldTypeException;
import tp.pr3.logic.world.World;
import tp.pr3.utils.SingleRandom;

public class SeedCommand extends Command{
	
	private Long seed;
	
	/**
	 *  <p>Load Command public contructor</p>
	 */
	public SeedCommand(){}
	
	/**
	 *  <p>Seed Command private contructor</p>
	 * @param seed the new seed to use
	 */
	private SeedCommand(Long seed) {
		this.seed = seed;
	}

	@Override
	public void execute(World world, Controller controller) throws NumberFormatException, FileFormatException, IOException, NoFileSelectedException, ErrorOnLoadException {
		SingleRandom.getInstance().getRandom().setSeed(seed);
	}

	@Override
	public Command parse(String[] commandString) throws UnknownWorldTypeException {
		if (commandString[0].equalsIgnoreCase(this.toString())) {
			return new SeedCommand(Long.parseLong(commandString[1]));
		}
		return null;
	}

	@Override
	public String helpText() {
		return "{BOLD}{PURPLE}" +  this.toString().toUpperCase() + " seed {RESET}		sets the currect seed for randomness";
	}

	@Override
	public String toString() {
		return "seed";
	}

}
