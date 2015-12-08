package tp.pr2.command;

import tp.pr2.logic.World;

public class InitCommand extends Command{

	@Override
	public void execute(World world) {
		world.initWorld();
	}

	@Override
	public Command parse(String[] commandString) {
		if ((commandString.length == 1) && (commandString[0].equalsIgnoreCase("init"))) return new InitCommand();
		return null;
	}

	@Override
	public String helpText() {
		return "restart the game";
	}

	@Override
	public String toString() {
		return "init";
	}
	
}
