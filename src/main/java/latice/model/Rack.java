package latice.model;

<<<<<<< HEAD
import java.util.ArrayList;

public class Rack {
	private ArrayList<Tile> tiles;
	private Pool pool;
	
	public Rack() {
		this.tiles = new ArrayList<Tile>();
		this.tiles.add(this.pool.tiles()[0]);
		this.tiles.add(this.pool.tiles()[1]);
		this.tiles.add(this.pool.tiles()[2]);
		this.tiles.add(this.pool.tiles()[3]);
		this.tiles.add(this.pool.tiles()[4]);
	}
=======
public class Rack {
	private Tile[] tiles;

	public Rack(Pool pool) {
		 tiles = new Tile[5];
	}

	public Tile[] Tiles() {
		return tiles;
	}
	
>>>>>>> origin/master
}
