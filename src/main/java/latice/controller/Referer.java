package latice.controller;

import java.util.ArrayList;

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
	
	public Boolean checkIfTileIsHere(GridPane grid, int col, int row) {
		Boolean isHere = false;
		for (Node node : grid.getChildren()) {
	        if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
	            isHere = true;
	        }
	    }
		return isHere;
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
			if (checkIfTileIsHere(grid, col, row - 1)) {
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
			if (checkIfTileIsHere(grid, col, row + 1)) {
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
			if (checkIfTileIsHere(grid, col - 1, row)) {
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
			if (checkIfTileIsHere(grid, col + 1, row)) {
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
	
	public boolean isSunTile(int col, int row) {
		boolean isSun = false;
		String position = col + ":" + row;
		
		switch (position) {
	    case "0:0":
	    case "4:0":
	    case "8:0":
	    case "1:1":
	    case "7:1":
	    case "2:2":
	    case "6:2":
	    case "0:4":
	    case "8:4":
	    case "2:6":
	    case "6:6":
	    case "1:7":
	    case "7:7":
	    case "0:8":
	    case "8:8":
	        isSun = true;
	        break;
	    default:
	        isSun = false;
		}
	return isSun;
	}
	
	public boolean firstTileOnTheMoon(GridPane grid) {
			return grid.getChildren().isEmpty();
	}
	
	public boolean canPut(ArrayList<ArrayList<String>> grid, int col, int row) {
		
		return true;
	}
}
