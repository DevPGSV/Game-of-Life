package tp.pr2.command;

import tp.pr2.logic.World;

public class ExitCommand extends CommandWithoutCoords{

	@Override
	public void execute(World world) {
		world.setSimulationFinished();
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
