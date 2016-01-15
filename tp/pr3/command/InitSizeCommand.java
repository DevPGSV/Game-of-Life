package tp.pr3.command;

import tp.pr3.controller.Controller;
import tp.pr3.logic.World;

public class InitSizeCommand extends CommandWithCoords{

	@Override
	public CommandWithCoords createInstance() {
		return new InitSizeCommand();
	}

	@Override
	public void execute(World world, Controller controller) {
		world.createNewDimensionedSurface(this.coords.getRow() + 1, this.coords.getColumn() + 1);
	}

	@Override
	public String helpText() {
		return "{BOLD}" +  this.toString().toUpperCase() + " C R{RESET}		restart the game with size CxR";
	}

	@Override
	public String toString() {
		return "init";
	}
	
}
