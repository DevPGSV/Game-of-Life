package tp.pr3.exceptions;

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
