package latice.model;

import java.util.ArrayList;

public class Pool {
	private Pool tiles;
	public Pool(ArrayList<Tile> tiles) {
		this.tiles = Game.deal(new MainPool());
	}
	
	public ArrayList<Tile> tiles() {
		return this.tiles;
	}
}