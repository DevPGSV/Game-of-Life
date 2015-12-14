package tp.pr2.view;

public enum Ansii {
	RESET		("\u001B[0m"),
	BOLD		("\u001B[1m"),
	UNDERLINE	("\u001B[4m"),
	BLACK		("\u001B[30m"),
	RED			("\u001B[31m"),
	GREEN		("\u001B[32m"),
	YELLOW		("\u001B[33m"),
	BLUE		("\u001B[34m"),
	PURPLE		("\u001B[35m"),
	CYAN		("\u001B[36m"),
	WHITE		("\u001B[37m");
	
	private final String code;
	
	Ansii(String code) {
		this.code = code;
	}
	
	
	public String getAnsii() {
		return code;
	}
}
