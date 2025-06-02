package latice.model;

public enum Colors {
	
	YELLOW("\u001B[33m"),
	NAVY("\u001B[34m"),
	MAGENTA("\u001B[35m"),
	RED("\u001B[31m"),
	GREEN("\u001B[32m"),
	TEAL("\u001B[36m"),
	WHITE("\u001B[0m");
	
	private String name;

	private Colors(String name) {
		this.name = name;
	}

	public String name(String text) {
		return this.name + text + "\u001B[0m";
	}
}
