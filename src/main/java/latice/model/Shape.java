package latice.model;

public enum Shape {
	
	FEATHER("\\uD83E\\uDDB6"),
	BIRD("\\uD83D\\uDC26"),
	TURTLE("\\uD83D\\uDC22"),
	FLOWER("\\uD83C\\uDF3A"),
	GECKO("\\uD83E\\uDD8E"),
	DOLPHIN("\\uD83D\\uDC2C");
	
	private String code;
	
	private Shape(String code) {
		this.code = code;
	}

	
	public String code() {
		return this.code;
	}
}
