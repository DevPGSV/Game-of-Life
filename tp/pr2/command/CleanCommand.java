package tp.pr2.command;

import tp.pr2.logic.World;

public class CleanCommand extends Command{

	@Override
	public void execute(World world) {
		world.cleanWorld();
		System.out.println("cleaning the game...");
	}

	@Override
	public Command parse(String[] commandString) {
		if ((commandString.length == 1) && (commandString[0].equalsIgnoreCase("clean"))) return new CleanCommand();
		return null;
	}

	@Override
	public String helpText() {
		return "delete all the cells";
	}

	@Override
	public String toString() {
		return "clean";
	}
	
}
