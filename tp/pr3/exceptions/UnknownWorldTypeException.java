package tp.pr3.exceptions;

/**
 * Thrown when an unknown world type is found.
 */
@SuppressWarnings("serial")
public class UnknownWorldTypeException extends Exception {
	
	public UnknownWorldTypeException () {
		super();
    }

    public UnknownWorldTypeException (String message) {
        super (message);
    }

    public UnknownWorldTypeException (Throwable cause) {
        super (cause);
    }

    public UnknownWorldTypeException (String message, Throwable cause) {
    	super (message + ((cause.getMessage() != null) ? "\n" + cause.getMessage() : ""), cause);
    }
}
