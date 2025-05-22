package latice.controller;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import latice.model.Color;
import latice.model.Player;
import latice.model.Shape;
import latice.model.Tile;

public class Referer {
	
	//Permet d'afficher le joueur qui dois jouer et son compteur d'action
	public void setPTurnAndAction(String playerName, Player player, Text idTxtPile, Text idMoves){
        idMoves.setText("Actions restantes : " + player.move());
		idTxtPile.setText("Au tour de " + playerName + " (" + player.points() + " points)");
	}
	
	//Permet d'afficher un message d'erreur
	public void displayErrorMessage(String message, Text idErrTile) {
		idErrTile.setVisible(true);
     	idErrTile.setText(message);
	}
	
	//Test pour poser une tuile
	public String checkTile(ImageView imageGrid) {
		Image img = imageGrid.getImage();
		String urlImage = Tile.url(img);
		return urlImage;
	}
	
	//Gere le système de points celon les tuiles à côté
	public void pointsManagement(int nbrOfTilesAround, Player player, Text idTxtLatice, Text idTxtTrefoil, Text idTxtDouble, int col, int row, Text idTxtPile, Text idMoves, String playerName, Boolean isP2) {
		AnimationTimer laticeAnimation = animateText(idTxtLatice);
    	AnimationTimer trefoilAnimation = animateText(idTxtTrefoil);
        AnimationTimer doubleAnimation = animateText(idTxtDouble);
		
		if (nbrOfTilesAround == 2) {
     		player.addPoints(1);
     		doubleAnimation.start();
     	}
 		else if (nbrOfTilesAround == 3) {
 			player.addPoints(2);
 			trefoilAnimation.start();
 		}
 		else if (nbrOfTilesAround == 4) {
 			player.addPoints(4);
 			laticeAnimation.start();
 		}
 		player.Move(0);
 		if (isSunTile(col, row)) {
			player.addPoints(2);
		}
 		setPTurnAndAction(playerName, player, idTxtPile, idMoves);
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
	
	private AnimationTimer animateText (Text text) {
        AnimationTimer anim = new AnimationTimer(){
            @Override
            public void handle(long now) {
                if (text.getFont().getSize() < 96) {
                	text.setOpacity(1);
                    text.setFont(new Font(text.getFont().getName(), text.getFont().getSize()+1));
                }
                else if(text.getOpacity() > 0){
                	text.setOpacity(text.getOpacity() - 0.01);
                }
                else{
                	text.setFont(new Font(text.getFont().getName(), 1));
                    stop();
                }
            }
        };
		return anim;
    }
}
