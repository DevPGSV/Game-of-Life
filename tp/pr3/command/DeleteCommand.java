package tp.pr3.command;

import tp.pr3.controller.Controller;
import tp.pr3.logic.world.World;

public class DeleteCommand extends CommandWithCoords{

	@Override
	public void execute(World world, Controller controller) {
		controller.delete(this.coords);
	}
	
	@Override
	public CommandWithCoords createInstance() {
		return new DeleteCommand();
	}

	@Override
	public String helpText() {
		return "{BOLD}" +  this.toString().toUpperCase() + " C R{RESET}		delete the cell at position (c, r)";
	}

	@Override
	public String toString() {
		return "delete";
	}
}
