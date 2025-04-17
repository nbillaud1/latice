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
		for(int i=0 ; i<42 ; i++) {
			tilesPool.add(this.mainPool.tiles()[i]);
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
