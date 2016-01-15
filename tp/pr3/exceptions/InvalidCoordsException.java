package tp.pr3.exceptions;

public class InvalidCoordsException extends Exception {
	public InvalidCoordsException () {
		super();
    }

    public InvalidCoordsException (String message) {
        super (message);
    }

    public InvalidCoordsException (Throwable cause) {
        super (cause);
    }

    public InvalidCoordsException (String message, Throwable cause) {
        super (message, cause);
    }
}
