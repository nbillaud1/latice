package latice.model;

public enum Color {
	
	YELLOW("Jaune"),
	NAVY("Bleu Marine"),
	MAGENTA("Magenta"),
	RED("Rouge"),
	GREEN("Vert"),
	TEAL("Bleu Sarcelle");
	
	private String code;

	private Color(String code) {
		this.code = code;
	}

	public String code() {
		return this.code;
	}
}
