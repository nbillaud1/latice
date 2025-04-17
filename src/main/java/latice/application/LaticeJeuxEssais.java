package latice.application;

import latice.model.MainPool;
import latice.model.Pool;
import latice.model.Tile;

public class LaticeJeuxEssais {
	
	static void affichMainPool() {
		MainPool mainPool = new MainPool();
		
		for(Tile tile : mainPool.tiles()) {
			System.out.println("Forme + couleur : " + tile.shape().code() + " " + tile.color().nom());
		}
	}
	
	static void affichPoolAndShuffle() {
		Pool pool = new Pool();
		int i = 0;
		for(Tile tile : pool.tiles()) {
			i++;
			System.out.println(i+ " " + tile.shape().code() + " " + tile.color().nom());
		}
	}
	
	public static void main(String[] args) {
		affichMainPool();
		
		affichPoolAndShuffle();
	}

}

