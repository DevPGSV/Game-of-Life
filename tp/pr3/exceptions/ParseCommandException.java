package tp.pr3.exceptions;

/**
 * Thrown when there is an error parsing a command
 */
@SuppressWarnings("serial")
public class ParseCommandException extends Exception {
	
	public ParseCommandException () {
		super();
    }

    public ParseCommandException (String message) {
        super (message);
    }

    public ParseCommandException (Throwable cause) {
        super (cause);
    }

    public ParseCommandException (String message, Throwable cause) {
        super (message + ((cause.getMessage() != null) ? "\n" + cause.getMessage() : ""), cause);
    }
}
