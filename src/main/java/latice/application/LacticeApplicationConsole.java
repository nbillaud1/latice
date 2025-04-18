package latice.application;

import java.util.ArrayList;

import latice.model.*;

public class LacticeApplicationConsole {

	private final static String LIGNE = "------------------------------------------------------------";
	private final static String BOARD_X = "----------------------------------------------------------------";
	private final static String BOARD_Y = "|      |      |      |      |      |      |      |      |      |";
	private final static String SUN = "\u2600";
	private final static String MOUN = "\uD83C\uDF19";
	
	private static ArrayList<String> board = new ArrayList<String>();
	
	public static void main(String[] args) {
		System.out.println(LIGNE);
		System.out.println("  --  Bienvenue dans notre magnifique jeu de latice !  --  ");
		System.out.println("  --               Développé par Reia !                --  ");		
		System.out.println("  --  		   Développé par Louis !  	       --  ");
		System.out.println("  --  		   Développé par Nathan !  	       --  ");
		System.out.println("  --  		   Développé par Sami !  	       --  ");
		System.out.println("  --  		   Développé par Evan!  	       --  ");
		System.out.println(LIGNE);
		

		System.out.println("");
		System.out.println("");
		
		for (int e = 0; e < 9; e++) {
			board.add("|  ");
			for(int i = 0; i<8 ; i++) {
				board.add("  ");
				board.add("  |  ");
			}
			board.add("  ");
			board.add("  |");
			
		}
		
		
		board.set(1,SUN);
		board.set(9,SUN);
		board.set(17,SUN);
		board.set(22,SUN);
		board.set(34,SUN);
		board.set(43,SUN);
		board.set(51,SUN);
		board.set(85,MOUN);
		board.set(119,SUN);
		board.set(127,SUN);
		board.set(136,SUN);
		board.set(148,SUN);
		board.set(153,SUN);
		board.set(161,SUN);
		board.set(169,SUN);
		
		System.out.println(BOARD_X);
		for(int i = 0; i<171 ; i += 19) {
			System.out.println(BOARD_Y);
			System.out.println(board.get(i) + board.get(i+1) + board.get(i+2) + board.get(i+3) + board.get(i+4) + board.get(i+5) + board.get(i+6) + board.get(i+7) + board.get(i+8) + board.get(i+9) + board.get(i+10) + board.get(i+11) + board.get(i+12) + board.get(i+13) + board.get(i+14) + board.get(i+15) + board.get(i+16) + board.get(i+17) +board.get(18));
			System.out.println(BOARD_Y);
			System.out.println(BOARD_X);
		}
		
	}

}
