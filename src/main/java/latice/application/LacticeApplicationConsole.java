package latice.application;

import java.util.ArrayList;

import exception.CannotPutATileException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import latice.controller.Referer;
import latice.model.*;

public class LacticeApplicationConsole {

	private final static String LIGNE = "------------------------------------------------------------";
	private static GameBoard gameBoard = new GameBoard();
	private static Referer referer = new Referer();
	
	public static void main(String[] args) {
		System.out.println(LIGNE);
		System.out.println("  --  Bienvenue dans notre magnifique jeu de latice !  --  ");
		System.out.println("  --               Développé par Reia !                --  ");		
		System.out.println("  --  		   Développé par Louis !  	       --  ");
		System.out.println("  --  		   Développé par Nathan !  	       --  ");
		System.out.println("  --  		   Développé par Sami !  	       --  ");
		System.out.println("  --  		   Développé par Evan!  	       --  ");
		System.out.println(LIGNE);
		

		System.out.println();
		System.out.println();
		
		putSomeTiles();
	}

	private static void putSomeTiles() {
		Tile tileFeather = new Tile(Shape.FEATHER, Color.GREEN);
		Tile tileTurtle = new Tile(Shape.TURTLE, Color.GREEN);
		/*try {
			checkAndPutTile(0, 0, tileFeather); //test ailleurs qu'au milieu
		}
		catch(CannotPutATileException e) {
			System.out.println(e.getMessage());
		}*/
		
		/*gameBoard.addBoard(5, 5, tileFeather);
		try {
			checkAndPutTile(1, 1, tileTurtle); //test rien à côté
		}
		catch(CannotPutATileException e) {
			System.out.println(e.getMessage());
		}*/
		
		/*gameBoard.addBoard(5, 5, tileTurtle);
		try {
			checkAndPutTile(4, 4, tileFeather); //test case déjà remplie.
		}
		catch(CannotPutATileException e) {
			System.out.println(e.getMessage());
		}*/
		gameBoard.addBoard(5, 5, tileTurtle);
		try {
			checkAndPutTile(4, 3, tileFeather); //test ok.
		}
		catch(CannotPutATileException e) {
			System.out.println(e.getMessage());
		}
		gameBoard.printGameBoard();
	}
	
	private static void checkAndPutTile(int row, int col, Tile tileToAdd) throws CannotPutATileException{
		Boolean hasToPlayOnTheMoon = referer.firstTileNotPuttedOnTheMoon(gameBoard.board());
		if(!hasToPlayOnTheMoon || (col == 4 && row == 4)) {
	        if(referer.gridAlreadyFilled(gameBoard.board(), col, row)) {
	        	throw new CannotPutATileException("Il y'a déjà une tuile ici !");
	        }
	        else if(referer.checkAround(gameBoard, col, row, tileToAdd) <= 0) {
	        	System.out.println(referer.checkAround(gameBoard, col, row, tileToAdd));
	        	throw new CannotPutATileException("Vous ne pouvez pas poser de tuile ici !");
	        }
	        else {
	        	gameBoard.addBoard(row+1, col+1, tileToAdd);
	        }
		}
        else if(hasToPlayOnTheMoon){
        	throw new CannotPutATileException("Vous devez poser la première tuile sur la Lune");
        }
	}
}
		
		