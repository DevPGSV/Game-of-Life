package tp.pr3.command;

import tp.pr3.logic.World;
import tp.pr3.view.Printer;

public class StepCommand extends CommandWithoutCoords{

	@Override
	public void execute(World world) {
		Printer.getInstance().print(world.evolve());
	}

	@Override
	public String helpText() {
		return "{BOLD}" +  this.toString().toUpperCase() + " {RESET}		execute a simulation step";
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
