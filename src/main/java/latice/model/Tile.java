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
		/*y'a un souci dans cet affichage car il faut mettre un reset de couleur : \u001B[0m*/
	}
}
