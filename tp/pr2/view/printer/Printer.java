package tp.pr2.view.printer;

public class Printer {
	private static Printer instance = null;
	private boolean useAnsiiCodes = true;
	
	private Printer() {
		if( System.getProperty("os.name").startsWith("Windows") ) {
			useAnsiiCodes = false;
		}
	}
	
	public static Printer getInstance() {
		if (Printer.instance == null) {
			Printer.instance = new Printer();
		}
		return Printer.instance;
	}
	
	public boolean isUsingAnsiiCodes() {
		return this.useAnsiiCodes;
	}
	
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
	
	public void print(Object o) {
		print(o.toString());
	}
}
