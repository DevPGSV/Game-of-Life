package tp.pr3.command;

import tp.pr3.controller.Controller;
import tp.pr3.exceptions.InvalidCoordsException;
import tp.pr3.logic.SimpleCell;
import tp.pr3.logic.Values;
import tp.pr3.logic.World;

public class CreateSimpleCommand extends CommandWithCoords{

	@Override
	public void execute(World world, Controller controller) throws InvalidCoordsException {
		if((coords.getRow() < 0) || (coords.getRow() >= world.getRows()) || (coords.getColumn() < 0) || (coords.getColumn() <= world.getCols())) {
			throw new InvalidCoordsException();
		}
		controller.createCell(this.coords, new SimpleCell(Values.MAX_LP, Values.MAX_MP));
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
