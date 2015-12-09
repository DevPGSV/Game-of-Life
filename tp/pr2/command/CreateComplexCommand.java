package tp.pr2.command;

import tp.pr2.logic.ComplexCell;
import tp.pr2.logic.Values;
import tp.pr2.logic.World;

public class CreateComplexCommand extends CommandWithCoords{
	
	@Override
	public void execute(World world) {
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
		return "{BOLD}" +  this.toString().toUpperCase() + " R C{RESET}	create a new complex cell at position (r,c)";
	}

	@Override
	public String toString() {
		return "createcomplex";
	}

}
