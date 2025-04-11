package latice.model;

public class Rack {
	private Tile[] tiles;

	public Rack(Pool pool) {
		 tiles = new Tile[5];
	}

	public Tile[] Tiles() {
		return tiles;
	}
	
}
