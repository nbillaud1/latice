package latice.application;

import java.util.ArrayList;

import latice.model.MainPool;
import latice.model.Pool;
import latice.model.Rack;
import latice.model.Tile;

public class LaticeJeuxEssais {

	private final static String LIGNE = "------------------------------";
	/*corriger les racks !!!*/
	static void printMainPool() {
		System.out.println(LIGNE);
		System.out.println("AFFICHAGE MAIN POOL");
		System.out.println(LIGNE);
		
		MainPool mainPool = new MainPool();
		int tilesCounter = 0;
		for(Tile tile : mainPool.tiles()) {
			tilesCounter++;
			System.out.println(tilesCounter + " Forme + couleur : " + tile.afficher());
		}
	}
	
	static void printPoolsRacksAndPoolsAgain() {
		System.out.println(LIGNE);
		System.out.println("AFFICHAGE POOLS");
		System.out.println(LIGNE);
		System.out.println("pool du joueur1");
		System.out.println(LIGNE);
		
		Pool pool = new Pool();
		ArrayList<Tile> poolPlayer1 = pool.tiles().get(0);
		
		int tilesCounter = 0;
		for(Tile tile : poolPlayer1) {
			tilesCounter++;
			System.out.println(tilesCounter+ " " + tile.afficher());
		}
		
		System.out.println("\n");
		System.out.println(LIGNE);
		System.out.println("pool du joueur 2");
		System.out.println(LIGNE);
		
		ArrayList<Tile> poolPlayer2 = pool.tiles().get(1);
		
		tilesCounter = 0;
		for(Tile tile : poolPlayer2) {
			tilesCounter++;
			System.out.println(tilesCounter+ " " + tile.afficher());
		}
		
		System.out.println(LIGNE);
		System.out.println("AFFICHAGE RACKS");
		System.out.println(LIGNE);
		System.out.println("rack du joueur 1");
		System.out.println(LIGNE);
		
		Rack rackPlayer1 = new Rack(poolPlayer1);
		tilesCounter = 0;
		for(Tile tile : rackPlayer1.tiles()) {
			tilesCounter++;
			System.out.println(tilesCounter + tile.afficher());
		}
		
		System.out.println("\n");
		System.out.println(LIGNE);
		System.out.println("rack du joueur 2");
		System.out.println(LIGNE);
		
		Rack rackPlayer2 = new Rack(poolPlayer2);
		tilesCounter = 0;
		for(Tile tile : rackPlayer2.tiles()) {
			tilesCounter++;
			System.out.println(tilesCounter + " " + tile.afficher());
		}
		
		System.out.println("\n");
		System.out.println(LIGNE);
		System.out.println("AFFICHAGE POOLS APRES RACKS");
		System.out.println(LIGNE);
		
		tilesCounter = 0;
		for(Tile tile : poolPlayer1) {
			tilesCounter++;
			System.out.println(tilesCounter+ " " + tile.afficher());
		}
		
		System.out.println("\n");
		System.out.println(LIGNE);
		System.out.println("pool du joueur 2");
		System.out.println(LIGNE);
		
		tilesCounter = 0;
		for(Tile tile : poolPlayer2) {
			tilesCounter++;
			System.out.println(tilesCounter+ " " + tile.afficher());
		}
		
	}

	
	public static void main(String[] args) {
		printMainPool();
		printPoolsRacksAndPoolsAgain();
	}
}