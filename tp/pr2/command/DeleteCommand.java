package tp.pr2.command;

import tp.pr2.logic.World;

public class DeleteCommand extends Command{

	@Override
	public void execute(World world) {
		
	}

	@Override
	public Command parse(String[] commandString) {
		if ((commandString.length == -1) && (commandString[0].equalsIgnoreCase("delete"))) return new DeleteCommand(); //!
		return null;
	}

	@Override
	public String helpText() {
		return "delete the cell at position (r,c)";
	}

	@Override
	public String toString() {
		return "delete r c";
	}
	
}
