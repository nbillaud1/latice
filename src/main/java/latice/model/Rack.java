package latice.model;

import java.util.ArrayList;

public class Rack {
	private static final int MAX_RACK_SIZE = 5;
	private ArrayList<Tile> tiles;
	private ArrayList<Tile> pool;
	
	public Rack(ArrayList<Tile> pool) {
		this.pool = pool;
		this.tiles = drawFiveTiles();
	}

	private ArrayList<Tile> drawFiveTiles() {
		ArrayList<Tile> tiles = new ArrayList<>();
		for(int nbTiles=1; nbTiles<=MAX_RACK_SIZE ; nbTiles++) {
			tiles.add(this.pool.get(0)); // piocher sur le dessus de la Pool.
			this.pool.remove(0);
		}
		return tiles;
	}
	
	public ArrayList<Tile> tiles(){
		return this.tiles;
	}
}
