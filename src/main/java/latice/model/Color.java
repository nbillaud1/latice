package latice.model;

public enum Color {
	
	YELLOW("Jaune"),
	NAVY("Bleu Marine"),
	MAGENTA("Magenta"),
	RED("Rouge"),
	GREEN("Vert"),
	TEAL("Bleu Sarcelle");
	
	private String nom;

	private Color(String nom) {
		this.nom = nom;
	}

	public String nom() {
		return this.nom;
	}
}
