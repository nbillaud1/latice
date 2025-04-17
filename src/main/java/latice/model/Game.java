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
	
	public ArrayList<Tile> deal() {
		ArrayList<Tile> tilesPool = new ArrayList<Tile>();
		int tour = 0;
		for(Tile tile : this.mainPool.tiles()) {
			tilesPool.add(tile);
		}
		return tilesPool;
	}
	
	public void shuffle() {
		Collections.shuffle(this.mainPool.tiles());
	}
	
	/*public Player youngerStarts() {
		//TODO
	}*/
}
