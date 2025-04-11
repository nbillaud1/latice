package latice.model;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
	/*private Player[] players;
	//private GameBoard gameBoard;*/
	private MainPool mainPool;
	
	public Game(/*Player[] players*/) {
		this.mainPool = new MainPool();
	}
	
	public Pool deal() {
		ArrayList<Tile> tilesPool = new ArrayList<Tile>();
		int tour = 0;
		for(int i=0 ; i<42 ; i++) {
			tilesPool.add(this.mainPool.tiles()[i]);
		}
		Pool pool = new Pool(tilesPool);
		return pool;
	}
	
	public void shuffle() {
		Collections.shuffle(this.mainPool.tiles());
	}
	
	/*public Player youngerStarts() {
		//TODO
	}*/
}
