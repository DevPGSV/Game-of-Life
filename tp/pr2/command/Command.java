package tp.pr2.command;

import tp.pr2.logic.World;

public abstract class Command {
	public abstract void execute(World world);
	public abstract Command parse(String[] commandString);
	public abstract String helpText();
	public abstract String toString();
}
