package tp.pr3.exceptions;

/**
 * Thrown when there is an error loading a file
 */
@SuppressWarnings("serial")
public class ErrorOnLoadException extends Exception {
	
	public ErrorOnLoadException () {
		super();
    }

    public ErrorOnLoadException (String message) {
        super (message);
    }

    public ErrorOnLoadException (Throwable cause) {
        super (cause);
    }

    public ErrorOnLoadException (String message, Throwable cause) {
    	super (message + ((cause.getMessage() != null) ? "\n" + cause.getMessage() : ""), cause);
    }
}
