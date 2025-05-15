package latice.controller;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import latice.model.Color;
import latice.model.Shape;
import latice.model.Tile;

public class Referer {
	
	
	//Test pour poser une tuile
	public String checkTile(ImageView imageGrid) {
		Image img = imageGrid.getImage();
		String urlImage = Tile.url(img);
		return urlImage;
	}
	
	public Shape checkShape(String url) {
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
	
	public Color checkColor(String url) {
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
	
	public Boolean checkIfTileIsHer(GridPane grid, int col, int row) {
		Boolean isHer = false;
		for (Node node : grid.getChildren()) {
	        if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
	            isHer = true;
	        }
	    }
		return isHer;
	}
	
	//TODO voir si c'est possible de refactor car beaucoup de répétitions ;)
	public int checkAround(GridPane grid, int col, int row, ImageView imageToPut) {
		Boolean putIsNotPossible = false;
		int nbrTiles = 0;
		String urlImageToPut = checkTile(imageToPut);
		Color colorImageToPut = checkColor(urlImageToPut);
		Shape shapeImageToPut = checkShape(urlImageToPut);
		//Regarde la tuile en haut
		if (row > 0) {
			if (checkIfTileIsHer(grid, col, row - 1)) {
				for (Node node : grid.getChildren()) {
			        if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == (row - 1)) {
			        	ImageView imageNextTo = (ImageView) node;
			        	String urlImageNextTo = checkTile(imageNextTo);
			        	Color colorImageNextTo = checkColor(urlImageNextTo);
			        	Shape shapeImageNextTo = checkShape(urlImageNextTo);
			        	if (colorImageToPut.equals(colorImageNextTo) || shapeImageToPut.equals(shapeImageNextTo)) {
			        		nbrTiles ++;
			        	}
			        	else {
			        		putIsNotPossible = true;
			        	}
			        }
			    }
			}
		}
		//Regarde la tuile du bas
		if (row < 8) {
			if (checkIfTileIsHer(grid, col, row + 1)) {
				for (Node node : grid.getChildren()) {
			        if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == (row + 1)) {
			        	ImageView imageNextTo = (ImageView) node;
			        	String urlImageNextTo = checkTile(imageNextTo);
			        	Color colorImageNextTo = checkColor(urlImageNextTo);
			        	Shape shapeImageNextTo = checkShape(urlImageNextTo);
			        	if (colorImageToPut.equals(colorImageNextTo) || shapeImageToPut.equals(shapeImageNextTo)) {
			        		nbrTiles ++;
			        	}
			        	else {
			        		putIsNotPossible = true;
			        	}
			        }
			    }
			}
		}
		//Regarde la tuile de gauche
		if (col > 0) {
			if (checkIfTileIsHer(grid, col - 1, row)) {
				for (Node node : grid.getChildren()) {
			        if (GridPane.getColumnIndex(node) == (col - 1) && GridPane.getRowIndex(node) == row) {
			        	ImageView imageNextTo = (ImageView) node;
			        	String urlImageNextTo = checkTile(imageNextTo);
			        	Color colorImageNextTo = checkColor(urlImageNextTo);
			        	Shape shapeImageNextTo = checkShape(urlImageNextTo);
			        	if (colorImageToPut.equals(colorImageNextTo) || shapeImageToPut.equals(shapeImageNextTo)) {
			        		nbrTiles ++;
			        	}
			        	else {
			        		putIsNotPossible = true;
			        	}
			        }
			    }
			}
		}
		//Regarde la tuile à droite
		if (col < 8) {
			if (checkIfTileIsHer(grid, col + 1, row)) {
				for (Node node : grid.getChildren()) {
			        if (GridPane.getColumnIndex(node) == (col + 1) && GridPane.getRowIndex(node) == row) {
			        	ImageView imageNextTo = (ImageView) node;
			        	String urlImageNextTo = checkTile(imageNextTo);
			        	Color colorImageNextTo = checkColor(urlImageNextTo);
			        	Shape shapeImageNextTo = checkShape(urlImageNextTo);
			        	if (colorImageToPut.equals(colorImageNextTo) || shapeImageToPut.equals(shapeImageNextTo)) {
			        		nbrTiles ++;
			        	}
			        	else {
			        		putIsNotPossible = true;
			        	}
			        }
			    }
			}
		}
		
		if (putIsNotPossible) {
			nbrTiles = -1;
		}
		return nbrTiles;
	}
	
	public boolean firstTileOnTheMoon(GridPane grid) {
			return grid.getChildren().isEmpty();
	}
}
