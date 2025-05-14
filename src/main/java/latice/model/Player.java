package latice.model;

import java.util.ArrayList;

public class Player {
	private ArrayList<Tile> pool;
	private Rack rack;
	private int points;
	private String name;
	private int move;
	private int tilePlayed;
	
	public Player(ArrayList<Tile> pool, Rack rack, String name  /*on choisira "1" ou "2" */) {
		this.pool = pool;
		this.rack = rack;
		this.name = name;
		this.points = 0; //Points du joueur
		this.move = 1; //Permet de savoir si le joueur peut encore jouer ou non
		this.tilePlayed = 0; //Permet de savoir le nombre de tuiles joués
		
	}
	
	public void buyExtraMove(){
		if (points <= 1 && move == 0) {
			points -= 1;
			move += 1;
		}
	}
	
	public void playATile() {
		tilePlayed += 1;
	}
	
	//Permet de stocker les tuiles restantes du rack dans 
	private void stackTiles() {
		for(Tile tile : rack.tiles()) {
			this.pool.add(tile);
		}
	}
	
	public void switchRack() {
		stackTiles();
		this.rack = new Rack(pool);
	}
	
	public void pass() {
		move = 0;
		tilePlayed = 0;
	}
	
	public void completeRack() {
		for (int nbTiles = 1; nbTiles <= (tilePlayed +1); nbTiles++) {
			rack.tiles().add(this.pool.get(0)); // piocher sur le dessus de la Pool.
			this.pool.remove(0);
		}
	}
	
	public void setPoints(int points) {
		this.points += points;
	}

	public Rack rack() {
		return rack;
	}
	
	public int move() {
		return move;
	}
	
	public void setMove() {
		this.move = 1;
	}
	public int points() {
		return points;
	}
}
