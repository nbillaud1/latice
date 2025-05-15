package latice.controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import latice.model.Color;
import latice.model.Shape;
import latice.model.Tile;

public class Referer {
	
	
	//Test pour poser une tuile
	private String checkTile(ImageView imageGrid) {
		Image img = imageGrid.getImage();
		String urlImage = Tile.url(img);
		return urlImage;
	}
	
	private Shape checkShape(String url) {
		String shape = url.substring(137,139);
		if (shape.equals("fl")){
			return Shape.FLOWER;
		}
		else if (shape.equals("ge")) {
			return Shape.GECKO;
		}
		else if (shape.equals("bi")) {
			return Shape.BIRD;
		}
		else if (shape.equals("tu")) {
			return Shape.TURTLE;
		}
		else if (shape.equals("fe")) {
			return Shape.FEATHER;
		}
		else {
			return Shape.DOLPHIN;
		}
	}
	
	private Color checkColor(String url) {
		String color = url.substring(url.length()-5, url.length()-4);
		if (color.equals("y")){
			return Color.YELLOW;
		}
		else if (color.equals("r")) {
			return Color.RED;
		}
		else if (color.equals("t")) {
			return Color.TEAL;
		}
		else if (color.equals("n")) {
			return Color.NAVY;
		}
		else if (color.equals("g")) {
			return Color.GREEN;
		}
		else {
			return Color.MAGENTA;
		}
		
	}
}
