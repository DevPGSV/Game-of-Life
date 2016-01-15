package tp.pr3.command;

import tp.pr3.logic.SimpleCell;
import tp.pr3.logic.Values;
import tp.pr3.logic.World;

public class CreateSimpleCommand extends CommandWithCoords{

	@Override
	public void execute(World world) {
		if (world.createCell(this.coords, new SimpleCell(Values.MAX_LP, Values.MAX_MP))) {
			System.out.println("New simple cell created at " + this.coords);
		} else {
			System.err.println("Couldn't create a cell at " + this.coords);
		}
	}
	
	@Override
	public CommandWithCoords createInstance() {
		return new CreateSimpleCommand();
	}

	@Override
	public String helpText() {
		return "{BOLD}" +  this.toString().toUpperCase() + " C R{RESET}	create a new simple cell at position (c, r)";
	}

	@Override
	public String toString() {
		return "createsimple";
	}
	
}
