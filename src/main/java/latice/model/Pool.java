package latice.model;

import java.util.ArrayList;

public class Pool {
	private ArrayList<Tile> tilesPlayer1;
	private ArrayList<Tile> tilesPlayer2;
	
	public Pool() {
		MainPool mainPool = new MainPool();
		mainPool.shuffle();
		ArrayList<ArrayList<Tile>> pools = mainPool.deal();
		this.tilesPlayer1 = pools.get(0);
		this.tilesPlayer2 = pools.get(1);
	}
	
	public ArrayList<ArrayList<Tile>> tiles() {
		ArrayList<ArrayList<Tile>> lTiles = new ArrayList<>();
		lTiles.add(tilesPlayer1);
		lTiles.add(tilesPlayer2);
		return lTiles;
	}
}