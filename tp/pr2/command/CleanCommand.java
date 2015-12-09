package tp.pr2.command;

import tp.pr2.logic.World;

public class CleanCommand extends CommandWithoutCoords{

	@Override
	public void execute(World world) {
		world.cleanWorld();
		System.out.println("cleaning the game...");
	}
	
	@Override
	public String helpText() {
		return "{BOLD}" +  this.toString().toUpperCase() + " {RESET}		delete all the cells";
	}

	@Override
	public String toString() {
		return "clean";
	}

	@Override
	public CommandWithoutCoords createInstance() {
		return new CleanCommand();
	}
	
}
