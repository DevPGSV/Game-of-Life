package tp.pr3.view;

public enum Ansii {
	RESET			("\u001B[0m"),
	BOLD			("\u001B[1m"),
	UNDERLINE		("\u001B[4m"),
	LIGHT			("\u001B[1m"),
	DARK			("\u001B[2m"),
	BLACK			("\u001B[30m"),
	RED				("\u001B[31m"),
	GREEN			("\u001B[32m"),
	YELLOW			("\u001B[33m"),
	BLUE			("\u001B[34m"),
	PURPLE			("\u001B[35m"),
	CYAN			("\u001B[36m"),
	WHITE			("\u001B[37m");
	
	private final String code;
	public static String resetCode = RESET.code;
	
	Ansii(String code) {
		this.code = code;
	}
	
	public String getAnsii() {
		if (this == Ansii.RESET) {
			return resetCode;
		}
		return code;
	}
	
	public void setDefaultResetCode(String str) {
		Ansii.resetCode = str;
	}
}
