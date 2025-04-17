package latice.model;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
	private GameBoard gameBoard
	
	public static Pool deal(MainPool mainPool) {
		ArrayList<Tile> tilesPool = new ArrayList<Tile>();
		int tour = 0;
		for(int i=0 ; i<42 ; i++) {
			tilesPool.add(this.mainPool.tiles()[i]);
		}
		Pool pool = new Pool(tilesPool);
		return pool;
	}
	
	public static void shuffle(MainPool mainPool) {
		Collections.shuffle(mainPool.tiles());
	}
	
	/*public Player youngerStarts() {
		//TODO
	}*/
}
