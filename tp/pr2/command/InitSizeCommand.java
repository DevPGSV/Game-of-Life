package tp.pr2.command;

import tp.pr2.logic.World;

public class InitSizeCommand extends CommandWithCoords{

	@Override
	public CommandWithCoords createInstance() {
		return new InitSizeCommand();
	}

	@Override
	public void execute(World world) {
		world.createNewDimensionedSurface(this.coords.getRow() + 1, this.coords.getColumn() + 1);
	}

	@Override
	public String helpText() {
		return "{BOLD}" +  this.toString().toUpperCase() + " C R{RESET}		restart the game with size CxR";
	}

	@Override
	public String toString() {
		return "init";
	}
	
}
