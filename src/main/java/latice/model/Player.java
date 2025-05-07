package latice.model;

import java.util.ArrayList;

public class Player {
	private ArrayList<Tile> pool;
	private Rack rack;
	private int points;
	private String name;
	private int move;
	
	public Player(ArrayList<Tile> pool, Rack rack, String name  /*on choisira "1" ou "2" */) {
		this.pool = pool;
		this.rack = rack;
		this.name = "joueur " + name;
		this.points = 0;
		this.move = 0;
		
	}
	
	public void buyExtraMove(){
		if (points <= 1 && move == 0) {
			points -= 1;
			move += 1;
		}
	}
	
	public void playATile() {
		//TODO
	}
	
	public void chagerRackAndPass() {
		this.rack = new Rack(this.pool);
		pass();
	}
	
	public void pass() {
		move = 0;
	}
	
	public void completeRack() {
		//TODO
	}
	
	public void setPoints(int points) {
		this.points += points;
	}
}
