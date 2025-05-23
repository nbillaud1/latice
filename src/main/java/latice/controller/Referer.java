package latice.controller;

import javafx.animation.AnimationTimer;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import latice.model.Color;
import latice.model.Player;
import latice.model.GameBoard;
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
	public String findTile(ImageView imageGrid) {
		Image img = imageGrid.getImage();
		String urlImage = Tile.url(img);
		return urlImage;
	}
	
	//Gere le système de points celon les tuiles à côté
	public void pointsManagement(int nbrOfTilesAround, Player player, Text idTxtLatice, Text idTxtTrefoil, Text idTxtDouble, int col, int row, Text idTxtPile, Text idMoves, String playerName, ArrayList<ArrayList<Tile>> grid) {
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
 		if (isSunTile(grid, col, row)) {
			player.addPoints(2);
		}
 		player.Move(0);
 		setPTurnAndAction(playerName, player, idTxtPile, idMoves);
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
	
	public boolean isSunTile(ArrayList<ArrayList<Tile>> grid, int col, int row) {
		return grid.get(row).get(col).toString().equals("|  [0m☀[0m  ");
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
