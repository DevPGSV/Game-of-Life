package tp.pr3.command;

import tp.pr3.logic.World;

public class DeleteCommand extends CommandWithCoords{

	@Override
	public void execute(World world) {
		if (world.deleteCell(this.coords)) {
			System.out.println("Cell deleted at " + this.coords);
		} else {
			System.err.println("Couldn't delete cell at " + this.coords);
		}
	}
	
	@Override
	public CommandWithCoords createInstance() {
		return new DeleteCommand();
	}

	@Override
	public String helpText() {
		return "{BOLD}" +  this.toString().toUpperCase() + " C R{RESET}		delete the cell at position (c, r)";
	}

	@Override
	public String toString() {
		return "delete";
	}
}
