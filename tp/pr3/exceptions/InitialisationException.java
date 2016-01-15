package tp.pr3.exceptions;

public class InitialisationException extends Exception {
	public InitialisationException () {
		super();
    }

    public InitialisationException (String message) {
        super (message);
    }

    public InitialisationException (Throwable cause) {
        super (cause);
    }

    public InitialisationException (String message, Throwable cause) {
        super (message, cause);
    }
}
