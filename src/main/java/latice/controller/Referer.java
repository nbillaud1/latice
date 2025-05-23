package latice.controller;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import latice.model.Color;
import latice.model.GameBoard;
import latice.model.Shape;
import latice.model.Tile;

public class Referer {

	//Test pour poser une tuile
	public String findTile(ImageView imageGrid) {
		Image img = imageGrid.getImage();
		String urlImage = Tile.url(img);
		return urlImage;
	}
	
	public Shape findShape(String url) {
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
	
	public Color findColor(String url) {
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
	
	//TODO voir si c'est possible de refactor car beaucoup de répétitions ;)
	public int checkAround(GameBoard gameBoard, int col, int row, Tile tile) {
		Boolean putIsNotPossible = false;
		int nbrTilesAround = 0;
		Color colorTileToPut = tile.color();
		Shape shapeTileToPut = tile.shape();
		//Regarde la tuile en haut
		if (row > 0) {
			if (gridAlreadyFilled(gameBoard.board(), col, row - 1)) {
	        	Tile tileNextTo = gameBoard.board().get(row - 1).get(col);
	        	Color colorTileNextTo = tileNextTo.color();
	        	Shape shapeTileNextTo = tileNextTo.shape();
	        	if (colorTileToPut.equals(colorTileNextTo) || shapeTileToPut.equals(shapeTileNextTo)) {
	        		nbrTilesAround ++;
	        	}
	        	else {
	        		putIsNotPossible = true;
	        	}
			}
		}
		//Regarde la tuile du bas
		if (row < 8) {
			if (gridAlreadyFilled(gameBoard.board(), col, row + 1)) {
	        	Tile tileNextTo = gameBoard.board().get(row + 1).get(col);
	        	Color colorTileNextTo = tileNextTo.color();
	        	Shape shapeTileNextTo = tileNextTo.shape();
	        	if (colorTileToPut.equals(colorTileNextTo) || shapeTileToPut.equals(shapeTileNextTo)) {
	        		nbrTilesAround ++;
	        	}
	        	else {
	        		putIsNotPossible = true;
	        	}
			}
		}
		//Regarde la tuile de gauche
		if (col > 0) {
			if (gridAlreadyFilled(gameBoard.board(), col - 1, row)) {
				Tile tileNextTo = gameBoard.board().get(row).get(col - 1);
	        	Color colorTileNextTo = tileNextTo.color();
	        	Shape shapeTileNextTo = tileNextTo.shape();
	        	if (colorTileToPut.equals(colorTileNextTo) || shapeTileToPut.equals(shapeTileNextTo)) {
	        		nbrTilesAround ++;
	        	}
	        	else {
	        		putIsNotPossible = true;
	        	}
			}
		}
		//Regarde la tuile à droite
		if (col < 8) {
			if (gridAlreadyFilled(gameBoard.board(), col + 1, row)) {
				Tile tileNextTo = gameBoard.board().get(row).get(col + 1);
	        	Color colorTileNextTo = tileNextTo.color();
	        	Shape shapeTileNextTo = tileNextTo.shape();
	        	if (colorTileToPut.equals(colorTileNextTo) || shapeTileToPut.equals(shapeTileNextTo)) {
	        		nbrTilesAround ++;
	        	}
	        	else {
	        		putIsNotPossible = true;
	        	}
			}
		}
		if (putIsNotPossible) {
			nbrTilesAround = -1;
		}
		return nbrTilesAround;
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
	
	public boolean firstTileNotPuttedOnTheMoon(ArrayList<ArrayList<Tile>> grid) {
		return "|  [0m🌙[0m  ".equals(grid.get(4).get(4).toString());
	}
	
	public Boolean gridAlreadyFilled(ArrayList<ArrayList<Tile>> grid, int col, int row) {
		Boolean isAlreadyFilled = true;
		if (grid.get(row).get(col).toString().equals("|  [0m☀[0m  ")){
			isAlreadyFilled = false;
		}
		if (grid.get(row).get(col).toString().equals("|  [0m  [0m  ")){
			isAlreadyFilled = false;
		}
		if (grid.get(row).get(col).toString().equals("|  [0m🌙[0m  ")){
			isAlreadyFilled = false;
		}
		return isAlreadyFilled;
	}
}
