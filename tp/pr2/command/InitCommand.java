package tp.pr2.command;

import tp.pr2.logic.World;

public class InitCommand extends CommandWithoutCoords{

	@Override
	public void execute(World world) {
		world.initWorld();
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
