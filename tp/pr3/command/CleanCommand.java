package tp.pr3.command;

import tp.pr3.controller.Controller;
import tp.pr3.logic.world.World;

public class CleanCommand extends CommandWithoutCoords{

	@Override
	public void execute(World world, Controller controller) {
		controller.clean();
	}
	
	@Override
	public String helpText() {
		return "{BOLD}" +  this.toString().toUpperCase() + " {RESET}		delete all the cells";
	}

	@Override
	public String toString() {
		return "clean";
	}

	@Override
	public CommandWithoutCoords createInstance() {
		return new CleanCommand();
	}
	
}
