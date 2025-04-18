package latice.model;

import java.util.ArrayList;

public class GameBoard {
	public final static String BOARD_X = "----------------------------------------------------------------";
	public final static String BOARD_Y = "|      |      |      |      |      |      |      |      |      |";
	public final static String SUN = "\u2600";
	public final static String MOUN = "\uD83C\uDF19";
	
	private ArrayList<String> board;
	
	public GameBoard() {
		this.board = new ArrayList<String>();
		for (int e = 0; e < 9; e++) {
			this.board.add("|  ");
			for(int i = 0; i<8 ; i++) {
				this.board.add("  ");
				this.board.add("  |  ");
			}
			this.board.add("  ");
			this.board.add("  |");
			
		}
		
		
		this.board.set(1,SUN);
		this.board.set(9,SUN);
		this.board.set(17,SUN);
		this.board.set(22,SUN);
		this.board.set(34,SUN);
		this.board.set(43,SUN);
		this.board.set(51,SUN);
		this.board.set(85,MOUN);
		this.board.set(119,SUN);
		this.board.set(127,SUN);
		this.board.set(136,SUN);
		this.board.set(148,SUN);
		this.board.set(153,SUN);
		this.board.set(161,SUN);
		this.board.set(169,SUN);
	}
		
	public ArrayList<String> board(){
		return this.board;
	}
}
