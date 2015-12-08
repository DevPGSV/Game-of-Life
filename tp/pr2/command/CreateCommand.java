package tp.pr2.command;

import tp.pr2.logic.World;

public class CreateCommand extends Command{

	@Override
	public void execute(World world) {
		
	}

	@Override
	public Command parse(String[] commandString) {
		if ((commandString.length == -1) && (commandString[0].equalsIgnoreCase("create"))) return new CreateCommand(); //!
		return null;
	}

	@Override
	public String helpText() {
		return "create a new cell at position (r,c)";
	}

	@Override
	public String toString() {
		return "create r c";
	}
	
}
