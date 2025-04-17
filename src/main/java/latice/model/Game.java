package latice.model;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
	//private GameBoard gameBoard;
	//private ArrayList<Player> players;
	private MainPool mainPool;
	
	public Game() {
		this.mainPool = new MainPool();
	}
	
	public ArrayList<ArrayList<Tile>> deal() {
		ArrayList<Tile> tilesPool1 = new ArrayList<Tile>();
		ArrayList<Tile> tilesPool2 = new ArrayList<Tile>();		
		int tour = 0;
		for(Tile tile : this.mainPool.tiles()) {
			if (tour % 2 == 0) {
				tilesPool1.add(tile);
			}
			else{
				tilesPool2.add(tile);
			}
			tour++;
		}
		ArrayList<ArrayList<Tile>> pools = new ArrayList<>();
		pools.add(tilesPool1);
		pools.add(tilesPool2);
		return pools;
	}
	
	public void shuffle() {
		Collections.shuffle(this.mainPool.tiles());
	}
	
	/*public Player youngerStarts() {
		//TODO
	}*/
}
