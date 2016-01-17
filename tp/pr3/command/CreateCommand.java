package tp.pr3.command;

import javax.swing.JOptionPane;
import tp.pr3.controller.Controller;
import tp.pr3.exceptions.ErrorOnCreateCellException;
import tp.pr3.exceptions.InvalidCoordsException;
import tp.pr3.exceptions.UnknownWorldTypeException;
import tp.pr3.logic.cell.Cell;
import tp.pr3.logic.cell.ComplexCell;
import tp.pr3.logic.cell.SimpleCell;
import tp.pr3.logic.world.World;
import tp.pr3.utils.Values;

public class CreateCommand extends CommandWithCoords{
	
	@Override
	public void execute(World world, Controller controller) throws UnknownWorldTypeException, ErrorOnCreateCellException, InvalidCoordsException {
		Cell cell;
		
		if (!world.checkIfValidCoords(this.coords)) throw new InvalidCoordsException("The coordinates " + this.coords + " are not valid");
		
		if (world.getWorldTypeAsString().equals("simple")) {
			cell = new SimpleCell(Values.MAX_LP, Values.MAX_MP);
		} else if (world.getWorldTypeAsString().equals("complex")) {
			Object[] options = {"Simple", "Complex"};
			int r = JOptionPane.showOptionDialog(null, "What type of cell would you like to create at  " + this.coords + "?", "Create Cell", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
	        if (r == 0) {
	        	cell = new SimpleCell(Values.MAX_LP, Values.MAX_MP);
	        } else if (r == 1) {
	        	cell = new ComplexCell(Values.MAX_EAT);
	        } else {
	        	throw new ErrorOnCreateCellException("Cell creating cancelled");
	        }
		} else {
			throw new UnknownWorldTypeException("Unknown world type: " + world.getWorldTypeAsString());
		}
		controller.createCell(this.coords, cell);
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
