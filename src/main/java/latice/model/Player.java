package latice.model;

import java.util.ArrayList;

public class Player {
	private ArrayList<Tile> pool;
	private Rack rack;
	private int points;
	private String name;
	private int move;
	private int tilePlays;
	
	public Player(ArrayList<Tile> pool, Rack rack, String name  /*on choisira "1" ou "2" */) {
		this.pool = pool;
		this.rack = rack;
		this.name = "joueur " + name;
		this.points = 0; //Points du joueur
		this.move = 0; //Permet de savoir si le joueur peut encore jouer ou non
		this.tilePlays = 0; //Permet de savoir le nombre de tuiles joués
		
	}
	
	public void buyExtraMove(){
		if (points <= 1 && move == 0) {
			points -= 1;
			move += 1;
		}
	}
	
	public void playATile() {
		//TODO
		tilePlays += 1;
	}
	
	public void chagerRackAndPass() {
		rack = new Rack(this.pool);
		pass();
	}
	
	public void pass() {
		move = 0;
		tilePlays = 0;
	}
	
	public void completeRack() {
		for (int nbTiles = 1; nbTiles <= (tilePlays +1); nbTiles++) {
			rack.tiles().add(this.pool.get(0)); // piocher sur le dessus de la Pool.
			this.pool.remove(0);
		}
	}
	
	public void setPoints(int points) {
		this.points += points;
	}
}
