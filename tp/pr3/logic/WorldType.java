package tp.pr3.logic;

import tp.pr3.exceptions.InvalidCoordsException;

public interface WorldType {
	public String evolve() throws InvalidCoordsException;
}
