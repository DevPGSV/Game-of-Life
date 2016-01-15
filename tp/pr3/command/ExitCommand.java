package tp.pr3.command;

import tp.pr3.controller.Controller;
import tp.pr3.logic.World;

public class ExitCommand extends CommandWithoutCoords{

	@Override
	public void execute(World world, Controller controller) {
		controller.setSimulationFinished();
	}

	@Override
	public String helpText() {
		return "{BOLD}" +  this.toString().toUpperCase() + " {RESET}		close the game";
	}

	@Override
	public String toString() {
		return "exit";
	}

	@Override
	public CommandWithoutCoords createInstance() {
		return new ExitCommand();
	}
	
}
