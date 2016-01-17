package tp.pr3.command;

import tp.pr3.controller.Controller;
import tp.pr3.logic.world.World;

public class InitCommand extends CommandWithoutCoords{

	@Override
	public void execute(World world, Controller controller) {
		controller.init();
	}

	@Override
	public String helpText() {
		return "{BOLD}" +  this.toString().toUpperCase() + " {RESET}		restart the game";
	}

	@Override
	public String toString() {
		return "init";
	}

	@Override
	public CommandWithoutCoords createInstance() {
		return new InitCommand();
	}
	
}
