package tp.pr3.exceptions;

@SuppressWarnings("serial")
public class SomethingWentWrongException extends Exception {
	
	public SomethingWentWrongException () {
		super();
    }

    public SomethingWentWrongException (String message) {
        super (message);
    }

    public SomethingWentWrongException (Throwable cause) {
        super (cause);
    }

    public SomethingWentWrongException (String message, Throwable cause) {
    	super (message + ((cause.getMessage() != null) ? "\n" + cause.getMessage() : ""), cause);
    }
}
