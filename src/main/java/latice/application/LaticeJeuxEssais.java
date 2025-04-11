package latice.application;

import latice.model.MainPool;
import latice.model.Tile;

public class LaticeJeuxEssais {

	public static void main(String[] args) {
		
		MainPool mainPool = new MainPool();
		
		for(Tile tile : mainPool.tiles()) {
			System.out.println("Forme + coleur : " + tile.shape().code() + " " + tile.color().nom());
		}
		
	}

}
