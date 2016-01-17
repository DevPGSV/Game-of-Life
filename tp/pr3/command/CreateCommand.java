package tp.pr3.command;

import tp.pr3.controller.Controller;
import tp.pr3.exceptions.UnknownWorldTypeException;
import tp.pr3.logic.cell.Cell;
import tp.pr3.logic.cell.ComplexCell;
import tp.pr3.logic.cell.SimpleCell;
import tp.pr3.logic.world.World;
import tp.pr3.utils.Values;

public class CreateCommand extends CommandWithCoords{
	
	@Override
	public void execute(World world, Controller controller) throws UnknownWorldTypeException {
		
		Cell cell;
		
		if (world.getWorldTypeAsString().equals("simple")) {
			cell = new SimpleCell(Values.MAX_LP, Values.MAX_MP);
		} else if (world.getWorldTypeAsString().equals("complex")) {
			cell = new ComplexCell(Values.MAX_EAT);
		} else {
			throw new UnknownWorldTypeException("Unknown world type: " + world.getWorldTypeAsString());
		}
		
		controller.createCell(this.coords, cell);
		
		/*
		if (world.createCell(this.coords, )) {
			System.out.println("New complex cell created at " + this.coords);
		} else {
			System.err.println("Couldn't create a cell at " + this.coords);
		}
		*/
	}
	
	@Override
	public CommandWithCoords createInstance() {
		return new CreateCommand();
	}

	@Override
	public String helpText() {
		return "{BOLD}" +  this.toString().toUpperCase() + " C R{RESET}	create a new cell at position (c, r)";
	}

	@Override
	public String toString() {
		return "create";
	}

}
