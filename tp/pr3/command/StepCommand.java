package tp.pr3.command;

import tp.pr3.controller.Controller;
import tp.pr3.logic.world.World;

public class StepCommand extends CommandWithoutCoords{

	@Override
	public void execute(World world, Controller controller) {
		controller.step();
	}

	@Override
	public String helpText() {
		return "{BOLD}{PURPLE}" +  this.toString().toUpperCase() + " {RESET}		execute a simulation step";
	}

	@Override
	public String toString() {
		return "step";
	}

	@Override
	public CommandWithoutCoords createInstance() {
		return new StepCommand();
	}
	
}
