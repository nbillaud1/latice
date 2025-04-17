package latice.model;

import java.util.ArrayList;

public class Pool {
	private ArrayList<Tile> tiles1;
	private ArrayList<Tile> tiles2;
	
	public Pool() {
		Game game = new Game();
		game.shuffle();
		this.tiles1 = game.deal().get(0);
		this.tiles2 = game.deal().get(1);
	}
	
	public ArrayList<ArrayList<Tile>> tiles() {
		ArrayList<ArrayList<Tile>> lTiles = new ArrayList<>();
		lTiles.add(tiles1);
		lTiles.add(tiles2);
		return lTiles;
	}
}