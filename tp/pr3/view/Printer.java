package tp.pr3.view;

/**
 * <p>Printer singleton.</p>
 */
public class Printer {
	private static Printer instance = null;
	private boolean useAnsiiCodes = true;
	
	/**
	 * <p>Private Printer constructor</p>
	 */
	private Printer() {
		if( System.getProperty("os.name").startsWith("Windows") ) {
			useAnsiiCodes = false;
		}
	}
	
	/**
	 * <p> Gets an instance </p>
	 * 
	 * @return an instance
	 */
	public static Printer getInstance() {
		if (Printer.instance == null) {
			Printer.instance = new Printer();
		}
		return Printer.instance;
	}
	
	/**
	 * <p>Returns useAnsiiCodes</p>
	 * 
	 * @return useAnsiiCodes
	 */
	public boolean isUsingAnsiiCodes() {
		return this.useAnsiiCodes;
	}
	
	/**
	 * <p>Prints substituting color patterns with ansii color codes</p>
	 * 
	 * @param text text to print
	 */
	public void print(String text) {
		for (Ansii ansii : Ansii.values()) {
			if (useAnsiiCodes)
				text = text.replaceAll(
						java.util.regex.Pattern.quote("{" + ansii.toString() + "}"),
						ansii.getAnsii()
					);
			else
				text = text.replaceAll(
						java.util.regex.Pattern.quote("{" + ansii.toString() + "}"),
						""
					);
		}
		System.out.print(text);
	}
	
	/**
	 * <p>Prints any object</p>
	 * @param o the object
	 */
	public void print(Object o) {
		print(o.toString());
	}
}
