package latice.model;

import java.util.ArrayList;

public class Player {
	private ArrayList<Tile> pool;
	private Rack rack;
	private int halfStone;
	private int sunStone;
	private String name;
	private int move;
	
	public Player(ArrayList<Tile> pool, Rack rack, String name) {
		this.pool = pool;
		this.rack = rack;
		this.name = name;
		this.halfStone = 0;
		this.sunStone = 0;
		this.move = 1; //Permet de savoir si le joueur peut encore jouer ou non		
	}
	
	public void buyExtraMoveWithHalfStones(){
		if (halfStone >= 2 && move == 0) {
			halfStone -= 2;
			move = 1;
		}
	}
	
	public void buyExtraMoveWithSunStones(){
		sunStone -= 1;
		move = 1;
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
	}
	
	public void completeRack(ArrayList<Integer> index) {
		for(int ind : index) {
			rack.tiles().set(ind, this.pool.get(0)); // piocher sur le dessus de la Pool.
			this.pool.remove(0);
		}	
	}
	
	public void addHalfStones() {
		this.halfStone += 1;
	}
	
	public void addSunStones(int sunStone) {
		this.sunStone += sunStone;
	}
	
	public void threeSunStones() {
		sunStone = 3;
	}

	public Rack rack() {
		return rack;
	}
	
	public int move() {
		return move;
	}
	
	public void Move(int nbr) {
		this.move = nbr;
	}
	
	public int halfStone() {
		return halfStone;
	}
	public int sunStone() {
		return sunStone;
	}
	
	public String name() {
		return this.name;
	}
}
