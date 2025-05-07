package latice.model;

import latice.model.Pool;

public class Player {
	private Pool pool;
	private Rack rack;
	private int points;
	private String name;
	private int move;
	
	public Player(Pool pool, Rack rack, String name /*on choisira "1" ou "2" */) {
		this.pool = pool;
		this.rack = rack;
		this.name = name;
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
		//TODO
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
