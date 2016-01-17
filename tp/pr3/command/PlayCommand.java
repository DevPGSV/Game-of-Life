package tp.pr3.command;

import tp.pr3.controller.Controller;
import tp.pr3.exceptions.InitialisationException;
import tp.pr3.exceptions.UnknownWorldTypeException;
import tp.pr3.logic.world.ComplexWorld;
import tp.pr3.logic.world.SimpleWorld;
import tp.pr3.logic.world.World;

public class PlayCommand extends Command{
	
	private World world;
	
	public PlayCommand(){}
	
	private PlayCommand(World world) {
		this.world = world;
	}

	@Override
	public void execute(World world, Controller controller) {
		controller.setWorld(this.world);
	}

	@Override
	public Command parse(String[] commandString) throws UnknownWorldTypeException, InitialisationException {
		if (commandString[0].equalsIgnoreCase(this.toString())) {
			if (commandString[1].equalsIgnoreCase("simple")) {
				if (Integer.parseInt(commandString[4]) > Integer.parseInt(commandString[2]) * Integer.parseInt(commandString[3]))
					throw new InitialisationException("Can not create " + Integer.parseInt(commandString[4]) + " cells on a " +  Integer.parseInt(commandString[2]) + "x" + Integer.parseInt(commandString[3]) + " board");
				return new PlayCommand(
						new SimpleWorld(
								Integer.parseInt(commandString[3]), // rows
								Integer.parseInt(commandString[2]), // cols
								Integer.parseInt(commandString[4])) // numberSimpleCells
				);
			} else if (commandString[1].equalsIgnoreCase("complex")) {
				if (Integer.parseInt(commandString[5]) + Integer.parseInt(commandString[4]) > Integer.parseInt(commandString[2]) * Integer.parseInt(commandString[3]))
					throw new InitialisationException("Can not create " + (Integer.parseInt(commandString[5]) + Integer.parseInt(commandString[4])) + " cells on a " +  Integer.parseInt(commandString[2]) + "x" + Integer.parseInt(commandString[3]) + " board");
				return new PlayCommand(
						new ComplexWorld(
								Integer.parseInt(commandString[3]), // rows
								Integer.parseInt(commandString[2]), // cols
								Integer.parseInt(commandString[4]), // numberSimpleCells
								Integer.parseInt(commandString[5])) // numberComplexCells
				);
			} else {
				throw new UnknownWorldTypeException("Unknown world type: " + commandString[1]);
			}
		}
		return null;
	}

	@Override
	public String helpText() {
		return "{BOLD}{PURPLE}" +  this.toString().toUpperCase() + " simple  C R ns {RESET} creates a new CxR simple game with ns simple cells\n"
				+ "   {BOLD}{PURPLE}" +  this.toString().toUpperCase() + " complex C R ns nc {RESET} creates a new CxR complex game with ns simple cells and nc complex cells";
	}

	@Override
	public String toString() {
		return "play";
	}

}
