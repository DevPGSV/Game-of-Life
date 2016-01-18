package tp.pr3.exceptions;

/**
 * Thrown when there is an error creating the cell
 */
@SuppressWarnings("serial")
public class ErrorOnCreateCellException extends Exception {
	
	public ErrorOnCreateCellException () {
		super();
    }

    public ErrorOnCreateCellException (String message) {
        super (message);
    }

    public ErrorOnCreateCellException (Throwable cause) {
        super (cause);
    }

    public ErrorOnCreateCellException (String message, Throwable cause) {
    	super (message + ((cause.getMessage() != null) ? "\n" + cause.getMessage() : ""), cause);
    }
}
