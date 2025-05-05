package latice.model;

public class Tile {
	private Shape shape;
	private Color color;
	
	public Tile(Shape shape, Color color) {
		this.shape = shape;
		this.color = color;
	}
	
	public Shape shape() {
		return this.shape;
	}
	
	public Color color() {
		return this.color;
	}
	
	public String afficher() {
		return color.nom(shape.code());
	}
}
