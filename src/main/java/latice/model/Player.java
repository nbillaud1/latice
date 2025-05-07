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
	
	public buyExtraMove(){
		//TODO
	}
	
	public playATile() {
		//TODO
	}
	
	public chagerRackAndPass() {
		//TODO
	}
	
	public pass() {
		//TODO
	}
	
	public completeRack() {
		//TODO
	}
	
	public setPoints(int points) {
		//TODO
	}
}
