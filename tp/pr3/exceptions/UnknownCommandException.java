package tp.pr3.exceptions;

/**
 * Thrown when the user writes an invalid command
 */
@SuppressWarnings("serial")
public class UnknownCommandException extends Exception {
	
	public UnknownCommandException () {
		super();
    }

    public UnknownCommandException (String message) {
        super (message);
    }

    public UnknownCommandException (Throwable cause) {
        super (cause);
    }

    public UnknownCommandException (String message, Throwable cause) {
    	super (message + ((cause.getMessage() != null) ? "\n" + cause.getMessage() : ""), cause);
    }
}
