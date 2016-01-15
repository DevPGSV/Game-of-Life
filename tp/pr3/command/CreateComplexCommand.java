package tp.pr3.command;

import tp.pr3.controller.Controller;
import tp.pr3.exceptions.InvalidCoordsException;
import tp.pr3.logic.ComplexCell;
import tp.pr3.logic.Values;
import tp.pr3.logic.World;

public class CreateComplexCommand extends CommandWithCoords{
	
	@Override
	public void execute(World world, Controller controller) throws InvalidCoordsException {
		if (world.createCell(this.coords, new ComplexCell(Values.MAX_EAT))) {
			System.out.println("New complex cell created at " + this.coords);
		} else {
			System.err.println("Couldn't create a cell at " + this.coords);
		}
	}
	
	@Override
	public CommandWithCoords createInstance() {
		return new CreateComplexCommand();
	}

	@Override
	public String helpText() {
		return "{BOLD}" +  this.toString().toUpperCase() + " C R{RESET}	create a new complex cell at position (c, r)";
	}

	@Override
	public String toString() {
		return "createcomplex";
	}

}
