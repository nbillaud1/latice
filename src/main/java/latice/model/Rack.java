package latice.model;

import java.util.ArrayList;

public class Rack {
	private ArrayList<Tile> tiles1;
	private ArrayList<Tile> tiles2;
	private Pool pool;
	
	public Rack() {
		this.pool = new Pool();
		this.tiles1 = new ArrayList<Tile>();
		this.tiles2 = new ArrayList<Tile>();
		int nbCards = 0;
		for(Tile tile : this.pool.tiles().get(0)) {
			if (nbCards < 5) {
				tiles1.add(tile);
				tile = null;
			}
			else {
				break;
			}
			nbCards++;
		}
		nbCards = 0;
		for(Tile tile : this.pool.tiles().get(1)) {
			if (nbCards < 5) {
				tiles2.add(tile);
				tile = null;
			}
			else {
				break;
			}
			nbCards++;
		}
	}
	
	public ArrayList<ArrayList<Tile>> tiles(){
		ArrayList<ArrayList<Tile>> lTiles = new ArrayList<>();
		lTiles.add(tiles1);
		lTiles.add(tiles2);
		return lTiles;
	}
}
