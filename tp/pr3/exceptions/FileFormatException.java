package tp.pr3.exceptions;

/**
 * Thrown when there is an error parsing a game a file
 */
@SuppressWarnings("serial")
public class FileFormatException extends Exception {
	
	public FileFormatException () {
		super();
    }

    public FileFormatException (String message) {
        super (message);
    }

    public FileFormatException (Throwable cause) {
        super (cause);
    }

    public FileFormatException (String message, Throwable cause) {
    	super (message + ((cause.getMessage() != null) ? "\n" + cause.getMessage() : ""), cause);
    }
}
