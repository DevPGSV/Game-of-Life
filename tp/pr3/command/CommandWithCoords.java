package tp.pr3.command;

import tp.pr3.exceptions.InvalidCoordsException;
import tp.pr3.utils.Coords;



public abstract class CommandWithCoords extends Command{
	
	protected Coords coords;
	
	/**
	 * <p>Returns an instance of this command</p>
	 * 
	 * @return an instance of this command
	 */
	public abstract CommandWithCoords createInstance();
	
	@Override
	public Command parse(String[] commandString) throws InvalidCoordsException {
		if ((commandString.length != 3)) return null;
		Coords coords = null;
		try {
			coords = new Coords(Integer.parseInt(commandString[2]) - 1, Integer.parseInt(commandString[1]) - 1);
		} catch (NumberFormatException e) {
			throw new InvalidCoordsException("Invalid coordinates.");
		}
		
		if (commandString[0].equalsIgnoreCase(this.toString()) && coords != null) {
			CommandWithCoords cObject = this.createInstance();
			cObject.coords = coords;
			return cObject;
		}
		return null;
	}
	
	
}
