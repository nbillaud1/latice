package latice.application;

import latice.model.MainPool;
import latice.model.Pool;
import latice.model.Rack;
import latice.model.Tile;

public class LaticeJeuxEssais {

	private final static String LIGNE = "------------------------------";
	
	public static void main(String[] args) {
		System.out.println(LIGNE);
		System.out.println("TEST MAIN POOL");
		System.out.println(LIGNE);
		
		MainPool mainPool = new MainPool();
		
		for(Tile tile : mainPool.tiles()) {
			System.out.println("Forme + couleur : " + tile.shape().code() + " " + tile.color().nom());
		}
		
		System.out.println(LIGNE);
		System.out.println("TEST POOL");
		System.out.println(LIGNE);
		
		Pool pool1 = new Pool();
		int i = 0;
		for(Tile tile : pool1.tiles().get(0)) {
			i++;
			System.out.println(i+ " " + tile.shape().code() + " " + tile.color().nom());
		}
		
		System.out.println("\n");
		
		Pool pool2 = new Pool();
		i = 0;
		for(Tile tile : pool2.tiles().get(0)) {
			i++;
			System.out.println(i+ " " + tile.shape().code() + " " + tile.color().nom());
		}
		
		System.out.println(LIGNE);
		System.out.println("TEST RACK");
		System.out.println(LIGNE);
		
		Rack rack1 = new Rack();
		int y = 0;
		for(Tile tile : rack1.tiles().get(0)) {
			y++;
			System.out.println(y + " " + tile.shape().code() + " " + tile.color().nom());
		}
		
		System.out.println("\n");
		
		Rack rack2 = new Rack();
		y = 0;
		for(Tile tile : rack2.tiles().get(1)) {
			y++;
			System.out.println(y + " " + tile.shape().code() + " " + tile.color().nom());
		}
		
	}

}