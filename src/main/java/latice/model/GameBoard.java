package latice.model;

import java.util.ArrayList;
import java.util.HashMap;

public class GameBoard {
	public final static String BOARD_X = "----------------------------------------------------------------";
	public final static String SUN = "|   \u2600 ";
	public final static String MOON = "|   \uD83C\uDF19 ";
	public final static String EMPTY = "      ";
	
	private ArrayList<ArrayList<String>> board;
	
	public GameBoard() {
		this.board = new ArrayList<>();
		
		for(int i=0; i<9; i++) {
			ArrayList<String> line = new ArrayList<>();
			for(int y=0; y<9; y++) {
				line.add("|" + EMPTY);
			}
			line.add("|");
			this.board.add(line);
		}

		this.board.get(0).set(0,SUN);
		this.board.get(0).set(4,SUN);
		this.board.get(0).set(8,SUN);
		
		this.board.get(1).set(1,SUN);
		this.board.get(1).set(7,SUN);
		
		this.board.get(2).set(2,SUN);
		this.board.get(2).set(6,SUN);
		
		this.board.get(4).set(4,MOON);
		this.board.get(4).set(8,SUN);
		this.board.get(4).set(0,SUN);
		
		this.board.get(6).set(6,SUN);
		this.board.get(6).set(2,SUN);
		
		this.board.get(7).set(7,SUN);
		this.board.get(7).set(1,SUN);
		
		this.board.get(8).set(0,SUN);
		this.board.get(8).set(4,SUN);
		this.board.get(8).set(8,SUN);
	}
	
	public void printGameBoard() {	
		System.out.println(GameBoard.BOARD_X);
		for(ArrayList<String> line: this.board()) {
			for(String square : line) {
				System.out.print(square);
			}
			System.out.println();
			System.out.println(GameBoard.BOARD_X);
		}
	}
	
	public ArrayList<ArrayList<String>> board(){
		return this.board;
	}
	
	public void addBoard(int row, int col, Tile tile) {
		this.board().get(row-1).set(col-1, tile.toString());
	}
}
