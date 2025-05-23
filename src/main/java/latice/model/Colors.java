package latice.model;

public enum Colors {
	
	YELLOW("\u001B[33m"),
	NAVY("\u001B[34m"),
	MAGENTA("\u001B[35m"),
	RED("\u001B[31m"),
	GREEN("\u001B[32m"),
	TEAL("\u001B[36m"),
	WHITE("\u001B[0m");
	
	private String nom;

	private Colors(String nom) {
		this.nom = nom;
	}

	public String nom(String text) {
		return this.nom + text + "\u001B[0m";
	}
}
