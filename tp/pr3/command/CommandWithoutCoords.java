package tp.pr3.command;

public abstract class CommandWithoutCoords extends Command{
	
	/**
	 * <p>Returns an instance of this command</p>
	 * 
	 * @return an instance of this command
	 */
	public abstract CommandWithoutCoords createInstance();
	
	@Override
	public Command parse(String[] commandString) {
		if ((commandString.length != 1)) return null;
		
		if (commandString[0].equalsIgnoreCase(this.toString())) {
			CommandWithoutCoords cObject = this.createInstance();
			return cObject;
		}
		return null;
	}

}
