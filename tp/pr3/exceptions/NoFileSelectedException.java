package tp.pr3.exceptions;

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
