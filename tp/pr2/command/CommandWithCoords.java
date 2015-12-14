package tp.pr2.command;

import tp.pr2.utils.Coords;



public abstract class CommandWithCoords extends Command{
	
	protected Coords coords;
	
	/**
	 * <p>Returns an instance of this command</p>
	 * 
	 * @return an instance of this command
	 */
	public abstract CommandWithCoords createInstance();
	
	@Override
	public Command parse(String[] commandString) {
		if ((commandString.length != 3)) return null;
		Coords coords = null;
		try {
			coords = new Coords(Integer.parseInt(commandString[2]) - 1, Integer.parseInt(commandString[1]) - 1);
		} catch (NumberFormatException e) {
			coords = null;
		}
		
		if (commandString[0].equalsIgnoreCase(this.toString()) && coords != null) {
			CommandWithCoords cObject = this.createInstance();
			cObject.coords = coords;
			return cObject;
		}
		return null;
	}
	
	
}
