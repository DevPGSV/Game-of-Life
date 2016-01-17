package tp.pr3.command;

import tp.pr3.controller.Controller;
import tp.pr3.exceptions.InvalidCoordsException;
import tp.pr3.logic.world.World;

public class DeleteCommand extends CommandWithCoords{

	@Override
	public void execute(World world, Controller controller) throws InvalidCoordsException {
		if (!world.checkIfValidCoords(this.coords)) throw new InvalidCoordsException("The coordinates " + this.coords + " are not valid");
		controller.delete(this.coords);
	}
	
	@Override
	public CommandWithCoords createInstance() {
		return new DeleteCommand();
	}

	@Override
	public String helpText() {
		return "{BOLD}{PURPLE}" +  this.toString().toUpperCase() + " C R{RESET}		delete the cell at position (c, r)";
	}

	@Override
	public String toString() {
		return "delete";
	}
}
