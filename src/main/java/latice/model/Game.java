package latice.model;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
	//private GameBoard gameBoard;
	private Player player1;
	private Player player2;
	private MainPool mainPool;
	
	public Game() {
		this.mainPool = new MainPool();
		//this.player1 = new Player(pool,rack,"1");
		//this.player2 = new Player(pool,rack,"2");
	}
	
	public MainPool mainPool() {
		return this.mainPool;
	}
	
	/*public Player whoStarts() {
		//TODO
	}*/
}
