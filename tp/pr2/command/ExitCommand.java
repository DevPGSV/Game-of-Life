package tp.pr2.command;

import tp.pr2.logic.World;

public class ExitCommand extends Command{

	@Override
	public void execute(World world) {
		world.setSimulationFinished();
	}

	@Override
	public Command parse(String[] commandString) {
		if ((commandString.length == 1) && (commandString[0].equalsIgnoreCase("exit"))) return new ExitCommand();
		return null;
	}

	@Override
	public String helpText() {
		return "close the game";
	}

	@Override
	public String toString() {
		return "exit";
	}
	
}
