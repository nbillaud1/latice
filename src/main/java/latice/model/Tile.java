package latice.model;

import javafx.scene.image.Image;

public class Tile {
	private Shape shape;
	private Colors color;
	
	public Tile(Shape shape, Colors color) {
		this.shape = shape;
		this.color = color;
	}
	
	public Shape shape() {
		return this.shape;
	}
	
	public Colors color() {
		return this.color;
	}
	
	public String afficher() {
		return color.nom(shape.code());
	}
	
	public String urlImg() {
		String url = "/latice/image/";
		// ex img : bird_g = oiseau vert.
		switch (this.shape) {
			case FEATHER:
				url = url + "feather";
				break;
			case BIRD:
				url = url + "bird";
				break;
			case TURTLE:
				url = url + "turtle";
				break;
			case FLOWER:
				url = url + "flower";
				break;
			case GECKO:
				url = url + "gecko";
				break;
			case DOLPHIN:
				url = url + "dolphin";
				break;
		}
		
		switch (this.color) {
		case YELLOW:
			url = url + "_y";
			break;
		case NAVY:
			url = url + "_n";
			break;
		case MAGENTA:
			url = url + "_m";
			break;
		case RED:
			url = url + "_r";
			break;
		case GREEN:
			url = url + "_g";
			break;
		case TEAL:
			url = url + "_t";
			break;
		}
		return url+".png";
	}
	
	public static String url(Image img) {
		return img.impl_getUrl();
	}
	
	public String toString() {
		return "|  " + color.nom(shape.code()) + "  ";
	}
}
