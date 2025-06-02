package latice.model;

//TODO : un bouton pour couper le son, un bon fond d'écran, un style sur les boutons.

import java.util.ArrayList;
import java.util.HashMap;

public class GameBoard {
	public final static String INTERLINE = "----------------------------------------------------------------";
	
	private ArrayList<ArrayList<Tile>> board;
	
	public GameBoard() {
		this.board = new ArrayList<>();
		
		for(int i=0; i<9; i++) {
			ArrayList<Tile> line = new ArrayList<>();
			for(int y=0; y<9; y++) {
				line.add(new Tile(Shape.EMPTY, Colors.WHITE));
			}
			this.board.add(line);
		}

		this.board.get(0).set(0, new Tile(Shape.SUN, Colors.WHITE));
		this.board.get(0).set(4, new Tile(Shape.SUN, Colors.WHITE));
		this.board.get(0).set(8,  new Tile(Shape.SUN, Colors.WHITE));
		
		this.board.get(1).set(1, new Tile(Shape.SUN, Colors.WHITE));
		this.board.get(1).set(7, new Tile(Shape.SUN, Colors.WHITE));
		
		this.board.get(2).set(2, new Tile(Shape.SUN, Colors.WHITE));
		this.board.get(2).set(6, new Tile(Shape.SUN, Colors.WHITE));
		
		this.board.get(4).set(4, new Tile(Shape.MOON, Colors.WHITE));
		this.board.get(4).set(8, new Tile(Shape.SUN, Colors.WHITE));
		this.board.get(4).set(0, new Tile(Shape.SUN, Colors.WHITE));
		
		this.board.get(6).set(6, new Tile(Shape.SUN, Colors.WHITE));
		this.board.get(6).set(2, new Tile(Shape.SUN, Colors.WHITE));
		
		this.board.get(7).set(7, new Tile(Shape.SUN, Colors.WHITE));
		this.board.get(7).set(1, new Tile(Shape.SUN, Colors.WHITE));
		
		this.board.get(8).set(0, new Tile(Shape.SUN, Colors.WHITE));
		this.board.get(8).set(4, new Tile(Shape.SUN, Colors.WHITE));
		this.board.get(8).set(8, new Tile(Shape.SUN, Colors.WHITE));
	}
	
	public void printGameBoard() {	
		System.out.println(GameBoard.INTERLINE);
		for(ArrayList<Tile> line: this.board()) {
			for(Tile tile : line) {
				System.out.print("|  " + tile.afficher() + "  ");
			}
			System.out.println("|");
			System.out.println(GameBoard.INTERLINE);
		}
	}
	
	public ArrayList<ArrayList<Tile>> board(){
		return this.board;
	}
	
	public void addBoard(int row, int col, Tile tile) {
		this.board().get(row-1).set(col-1, tile);
	}
}
