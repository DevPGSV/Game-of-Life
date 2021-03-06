package tp.pr3.exceptions;

/**
 * Thrown when the user doesn't select a file (saving/loading)
 */
@SuppressWarnings("serial")
public class NoFileSelectedException extends Exception {
	
	public NoFileSelectedException () {
		super();
    }

    public NoFileSelectedException (String message) {
        super (message);
    }

    public NoFileSelectedException (Throwable cause) {
        super (cause);
    }

    public NoFileSelectedException (String message, Throwable cause) {
    	super (message + ((cause.getMessage() != null) ? "\n" + cause.getMessage() : ""), cause);
    }
}
