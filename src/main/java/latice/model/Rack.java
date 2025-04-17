package latice.model;

import java.util.ArrayList;

public class Rack {
	private ArrayList<Tile> tiles;
	private Pool pool;
	
	public Rack() {
		this.pool = new Pool();
		this.tiles = new ArrayList<Tile>();
		/*this.tiles.add(this.pool.tiles()[0]);
		this.tiles.add(this.pool.tiles()[1]);
		this.tiles.add(this.pool.tiles()[2]);
		this.tiles.add(this.pool.tiles()[3]);
		this.tiles.add(this.pool.tiles()[4]);*/
		//TODO
		this.pool.tiles().remove(0);
		this.pool.tiles().remove(1);
		this.pool.tiles().remove(2);
		this.pool.tiles().remove(3);
		this.pool.tiles().remove(4);
	}
}
