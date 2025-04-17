package latice.model;

import java.util.ArrayList;

public class Pool {
	private ArrayList<Tile> tiles;
	
	public Pool(ArrayList<Tile> tiles) {
		Game game = new Game();
		game.shuffle();
		this.tiles = game.deal();
	}
	
	public ArrayList<Tile> tiles() {
		return this.tiles;
	}
}