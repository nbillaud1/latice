package latice.application;

import java.util.ArrayList;

import latice.model.*;

public class LacticeApplicationConsole {

	private final static String LIGNE = "------------------------------------------------------------";
	
	
	public static void main(String[] args) {
		/*System.out.println(LIGNE);
		System.out.println("  --  Bienvenue dans notre magnifique jeu de latice !  --  ");
		System.out.println("  --               Développé par Reia !                --  ");		
		System.out.println("  --  		   Développé par Louis !  	       --  ");
		System.out.println("  --  		   Développé par Nathan !  	       --  ");
		System.out.println("  --  		   Développé par Sami !  	       --  ");
		System.out.println("  --  		   Développé par Evan!  	       --  ");
		System.out.println(LIGNE);
		

		System.out.println("");
		System.out.println("");
		*/
		printGameBoard();
	}
	
	private static void printGameBoard() {
		
		GameBoard gameB = new GameBoard();
		
		System.out.println(GameBoard.BOARD_X);
		for(int i = 0; i<171 ; i += 19) {
			System.out.println(GameBoard.BOARD_Y);
			System.out.println(gameB.board().get(i) + gameB.board().get(i+1) + gameB.board().get(i+2) + gameB.board().get(i+3) + gameB.board().get(i+4) + gameB.board().get(i+5) + gameB.board().get(i+6) + gameB.board().get(i+7) + gameB.board().get(i+8) + gameB.board().get(i+9) + gameB.board().get(i+10) + gameB.board().get(i+11) + gameB.board().get(i+12) + gameB.board().get(i+13) + gameB.board().get(i+14) + gameB.board().get(i+15) + gameB.board().get(i+16) + gameB.board().get(i+17) + gameB.board().get(18));
			System.out.println(GameBoard.BOARD_Y);
			System.out.println(GameBoard.BOARD_X);
		}
	}
}
		
		