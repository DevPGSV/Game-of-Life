package tp.pr2.command;

import tp.pr2.logic.World;
import tp.pr2.view.printer.Printer;

public class StepCommand extends Command{

	@Override
	public void execute(World world) {
		Printer.getInstance().print(world.evolve());
	}

	@Override
	public Command parse(String[] commandString) {
		if ((commandString.length == 1) && (commandString[0].equalsIgnoreCase("step"))) return new StepCommand();
		return null;
	}

	@Override
	public String helpText() {
		return "execute a simulation step";
	}

	@Override
	public String toString() {
		return "step";
	}
	
}
