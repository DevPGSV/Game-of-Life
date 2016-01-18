package tp.pr3.exceptions;

/**
 * Thrown when there is an error saving a file
 */
@SuppressWarnings("serial")
public class ErrorOnSaveException extends Exception {
	
	public ErrorOnSaveException () {
		super();
    }

    public ErrorOnSaveException (String message) {
        super (message);
    }

    public ErrorOnSaveException (Throwable cause) {
        super (cause);
    }

    public ErrorOnSaveException (String message, Throwable cause) {
    	super (message + ((cause.getMessage() != null) ? "\n" + cause.getMessage() : ""), cause);
    }
}
