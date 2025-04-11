package latice.model;

import java.util.ArrayList;

public class Pool {
	private ArrayList<Tile> tiles;
	
	public Pool (ArrayList<Tile> tiles) {
		this.tiles = tiles;
	}
	
	public ArrayList<Tile> tiles() {
		return this.tiles;
	}

}