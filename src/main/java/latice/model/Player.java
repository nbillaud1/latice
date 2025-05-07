package latice.model;

import latice.model.Pool;

public class Player {
	private Pool pool;
	private Rack rack;
	private int points;
	private String name;
	
	public Player(Pool pool, Rack rack, String name /*on choisira "1" ou "2" */) {
		this.pool = pool;
		this.rack = rack;
		this.name = name;
		this.points = 0;
		
	}
	
	public void buyExtraMove(){
		//TODO
	}
	
	public void playATile() {
		//TODO
	}
	
	public void chagerRackAndPass() {
		//TODO
	}
	
	public void pass() {
		//TODO
	}
	
	public void completeRack() {
		//TODO
	}
	
	public void setPoints(int points) {
		//TODO
	}
}
