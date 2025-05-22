package latice.application;

import java.util.ArrayList;

import latice.model.*;

public class LacticeApplicationConsole {

	private final static String LIGNE = "------------------------------------------------------------";
	
	
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
		
		GameBoard gameBoard = new GameBoard();
		
		Tile tileFeather = new Tile(Shape.FEATHER, Color.GREEN);
		Tile tileTurtle = new Tile(Shape.TURTLE, Color.GREEN);
		gameBoard.addBoard(2, 4, tileFeather);
		gameBoard.addBoard(1, 1, tileTurtle);
		gameBoard.printGameBoard();
	}
	
	
}
		
		