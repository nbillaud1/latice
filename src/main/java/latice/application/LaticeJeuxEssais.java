package latice.application;

import latice.model.MainPool;
import latice.model.Pool;
import latice.model.Tile;

public class LaticeJeuxEssais {

	public static void main(String[] args) {
		
		MainPool mainPool = new MainPool();
		
		for(Tile tile : mainPool.tiles()) {
			System.out.println("Forme + couleur : " + tile.shape().code() + " " + tile.color().nom());
		}
		
		Pool pool = new Pool();
		int i = 0;
		for(Tile tile : pool.tiles()) {
			i++;
			System.out.println(i+ " " + tile.shape().code() + " " + tile.color().nom());
		}
		
	}

}
