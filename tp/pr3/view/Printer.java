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
	 * Replaces {colors} with ansii codes
	 * @param text original text
	 * @return text with colors replaced
	 */
	public String patternReplace(String text) {
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
		return text;
	}
	
	/**
	 * <p>Prints to stdout substituting color patterns with ansii color codes</p>
	 * 
	 * @param text text to print
	 */
	public void print(String text) {
		System.out.print(patternReplace(text));
	}
	
	/**
	 * <p>Prints to stderr substituting color patterns with ansii color codes</p>
	 * 
	 * @param text text to print
	 */
	public void printErr(String text) {
		text = "{LIGHT}{RED}" + text.replaceAll(
				java.util.regex.Pattern.quote("{RESET}"),
				"{LIGHT}{RED}"
			) + "{RESET}\n";
		System.err.print(patternReplace(text));
	}
	
	/**
	 * <p>Prints any object</p>
	 * @param o the object
	 */
	public void print(Object o) {
		print(o.toString());
	}
}
